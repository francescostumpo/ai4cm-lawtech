package com.ibm.snam.ai4legal.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.ContractFile;
import com.ibm.snam.ai4legal.model.ContractInvolvedParty;
import com.ibm.snam.ai4legal.model.Element;
import com.ibm.snam.ai4legal.model.ElementFilter;
import com.ibm.snam.ai4legal.model.Entity;
import com.ibm.snam.ai4legal.model.Filter;
import com.ibm.snam.ai4legal.repositories.ContactRepository;
import com.ibm.snam.ai4legal.repositories.ContractFileRepository;
import com.ibm.snam.ai4legal.repositories.ContractGoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.ContractInvolvedPartyRepository;
import com.ibm.snam.ai4legal.repositories.ContractRepository;
import com.ibm.snam.ai4legal.repositories.ContractTypeRepository;
import com.ibm.snam.ai4legal.repositories.ElementFilterRepository;
import com.ibm.snam.ai4legal.repositories.ElementRepository;
import com.ibm.snam.ai4legal.repositories.EntityRepository;
import com.ibm.snam.ai4legal.repositories.FilterRepository;
import com.ibm.snam.ai4legal.repositories.GoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.InvolvedPartyRepository;
import com.ibm.snam.ai4legal.util.Constants;
import com.ibm.snam.ai4legal.util.DictionaryFoTranslation;
import com.ibm.snam.ai4legal.util.WatsonDiscoveryService;

import ibm.com.snam.ai4legal.thread.RetrieveContractFromDBThread;
import ibm.com.snam.ai4legal.thread.UploadContractOnDBThread;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gestisce l'upload del contratto sul DB (anche del file fisico) sotto forma di
 * element e inserisce le varie entità ottenute da WS nel DB. Inoltre fornisce
 * l'operazione di retrieve sotto del contratto sotto forma di JSon contenente
 * informazioni relative a: - categorie - nature - attributi - parti - contatti
 * - date - definizioni presenti nel contratto ricercato.
 * 
 * @author PietroGerace
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class ContractController {

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ElementRepository elementRepository;

	@Autowired
	EntityRepository entityRepository;

	@Autowired
	InvolvedPartyRepository involvedPartyRepository;

	@Autowired
	FilterRepository filterRepository;

	@Autowired
	ElementFilterRepository elementFilterRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ContractFileRepository contractFileRepository;

	@Autowired
	ContractGoverningLawRepository contractGoverningLawRepository;

	@Autowired
	ContractTypeRepository contractTypeRepository;

	@Autowired
	ContractInvolvedPartyRepository contractInvolvedPartyRepository;

	@Autowired
	GoverningLawRepository governingLawRepository;

	@Autowired
	Environment env;
	
	private Contract contract;
	
	Logger logger = LoggerFactory.getLogger(ContractController.class);
	
	//@Transactional(rollbackFor = Exception.class)
	@PostMapping("/uploadContractOnDB")
	public ResponseEntity<JSONObject> uploadContractOnDB(@RequestParam("contract") MultipartFile document,
			@RequestParam("language") int idLanguage, HttpServletRequest request) throws Exception{
		// Chiamo il servizio di Watson per l'enrichment del testo
		try{
			JSONObject responseAsJsonObject = WatsonDiscoveryService.getService().callWatsonDiscoveryService(document,
					idLanguage);
			//Salva le entità e gli elementi tornati da Watson Discovery nel DB2
			JSONArray entities = responseAsJsonObject.getJSONArray("entities");
			if(entities.size() == 0){
				logger.info("Error calling Watson discovery. Entities size is 0");
				throw new Exception("Error calling Watson discovery. Entities size is 0");
			}
			logger.info("response from Discovery: " + responseAsJsonObject);
			/*
			Contract oldContract = contractRepository.findByName(document.getOriginalFilename());
			if(oldContract != null){
				logger.info("Contract " + oldContract.getName() +" already exists in the DB");
			}
			*/
			//Genera un nuovo contratto e lo aggiunge nel DB2
			contract = new Contract();
			contract.setIdLanguage(idLanguage);
			contract.setName(document.getOriginalFilename());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			contract.setCreationDate(dateFormat.format(date));
			HttpSession session = request.getSession();
			contract.setCreationUser((String)session.getAttribute(Constants.EMAIL));
			contractRepository.save(contract);
	
			JSONArray elements = responseAsJsonObject.getJSONArray("elements");
			String id_contract = "" + contract.getId();
	
			saveFileOnDB(document, Integer.parseInt(id_contract));
			
			List<Element> elementsInContract = new LinkedList<>();
			for (int j = 0; j < elements.size(); j++) {
				Element element_db = saveElementInDB((String) elements.get(j), j, Integer.parseInt(id_contract));
				elementsInContract.add(element_db);		
			}
			// --------------- Parallelizzazione ----------------------
			int numberOfThread = Integer.parseInt(env.getProperty("numberOfThreadForUpload"));
			ExecutorService workerThreadPool = Executors.newFixedThreadPool(numberOfThread);
			CountDownLatch latch = new CountDownLatch(numberOfThread);
			int lenghtOfEntitiesForEachThread = entities.size()/numberOfThread;
			int beginIndex = 0;
			int endIndex = -1;
			UploadContractOnDBThread [] pull = new UploadContractOnDBThread[numberOfThread];
			for(int i = 0; i < numberOfThread ; i ++){
				beginIndex = i*lenghtOfEntitiesForEachThread;
				if( beginIndex + lenghtOfEntitiesForEachThread > entities.size()){
					int residual = entities.size() - beginIndex;
					endIndex =  beginIndex + residual;
				}
				else{
					endIndex = beginIndex + lenghtOfEntitiesForEachThread;
					int residual = entities.size() - endIndex;
					if(i == numberOfThread -1 && residual > 0){
						endIndex += residual;
					}
				}
				UploadContractOnDBThread uploadContractThread = 
						new UploadContractOnDBThread(beginIndex, endIndex,
														entities, 
														elementsInContract, 
														contractTypeRepository, 
														entityRepository, 
														elementFilterRepository, 
														id_contract, 
														filterRepository, 
														involvedPartyRepository, 
														governingLawRepository, 
														contractInvolvedPartyRepository, 
														contractGoverningLawRepository,
														latch);
				uploadContractThread.setContract(contract);
				workerThreadPool.submit(uploadContractThread);
				pull[i] = uploadContractThread;
			}
			latch.await();
			for (int i = 0; i < pull.length; i++) {
				if(!pull[i].isTerminatedCorrectly()){
					logger.info("-- uploadContractOnDB -- END Problem in some thread during the upload of contract");
					//Generando l'eccezione faccio partire il rollback
					contractRepository.delete(contract);
					JSONObject response = new JSONObject();
					response.put("message", "Problem in some thread during the upload of contract");
					response.put("contractId", id_contract);
					return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			contractRepository.save(contract);
			JSONObject response = new JSONObject();
			response.put("message", "Contract uploaded successfully");
			response.put("contractId", id_contract);
			logger.info("-- uploadContractOnDB -- END");
			
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);

		}catch(Exception e){
			e.printStackTrace();
			logger.info("-- uploadContractOnDB -- END with some problems");
			//Generando l'eccezione faccio partire il rollback
			contractRepository.delete(contract);
			JSONObject response = new JSONObject();
			response.put("message", "Problem during the upload of contract");
			response.put("contractId", -1);
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/contract/retrieve")
	public JSONObject retrieveDocument(@RequestParam("documentId") String documentId, HttpServletRequest request) throws Exception{
		logger.info("-- retrieveDocument -- INIT");

		JSONObject document = new JSONObject();
		document.put("documentId", documentId);

		// Prende gli elementi e li aggiunge al JSON di risposta
		List<Element> elementList = elementRepository.findByIdContract(Integer.parseInt(documentId));

		JSONArray categoriesInDocumentJsonArray = new JSONArray();
		JSONArray naturesInDocumentJsonArray = new JSONArray();
		JSONArray attributesInDocumentJsonArray = new JSONArray();
		JSONArray partiesInDocumentJsonArray = new JSONArray();
		JSONArray datesInDocumentJsonArray = new JSONArray();
		JSONArray contactsInDocumentJsonarray = new JSONArray();
		JSONArray definitionsInDocumentJsonarray = new JSONArray();
		Map<String, Integer> categoriesInDocument = new HashMap<>();
		Map<String, Integer> naturesInDocument = new HashMap<>();
		Map<String, Integer> attributesInDocument = new HashMap<>();
		Map<String, Integer> partiesInDocument = new HashMap<>();

		JSONArray elementJsonArray = new JSONArray();
		int numberOfThread = Integer.parseInt(env.getProperty("numberOfThreadForRetrieve"));
		ExecutorService workerThreadPool = Executors.newFixedThreadPool(numberOfThread);
		CountDownLatch latch = new CountDownLatch(numberOfThread);
		int lenghtOfEntitiesForEachThread = elementList.size()/numberOfThread;
		int beginIndex = 0;
		int endIndex = -1;
		Semaphore semaphore = new Semaphore(1);
		for(int i = 0; i < numberOfThread ; i ++){
			beginIndex = i*lenghtOfEntitiesForEachThread;
			if( beginIndex + lenghtOfEntitiesForEachThread > elementList.size()){
				int residual = elementList.size() - beginIndex;
				endIndex =  beginIndex + residual;
			}
			else{
				endIndex = beginIndex + lenghtOfEntitiesForEachThread;
				int residual = elementList.size() - endIndex;
				if(i == numberOfThread -1 && residual > 0){
					endIndex += residual;
				}
			}
			RetrieveContractFromDBThread thread = new RetrieveContractFromDBThread(semaphore,latch);
			thread.setBeginIndex(beginIndex);
			thread.setEndIndex(endIndex);
			thread.setElementList(elementList);
			thread.setElementFilterRepository(elementFilterRepository);
			thread.setFilterRepository(filterRepository);
			HttpSession session = request.getSession();
			thread.setSession(session);
			thread.setCategoriesInDocument(categoriesInDocument);
			thread.setNaturesInDocument(naturesInDocument);
			thread.setAttributesInDocument(attributesInDocument);
			thread.setPartiesInDocument(partiesInDocument);
			thread.setEntityRepository(entityRepository);
			thread.setDatesInDocumentJsonArray(datesInDocumentJsonArray);
			thread.setContactsInDocumentJsonarray(contactsInDocumentJsonarray);
			thread.setDefinitionsInDocumentJsonarray(definitionsInDocumentJsonarray);
			thread.setElementJsonArray(elementJsonArray);
			workerThreadPool.submit(thread);
		}
		latch.await();
		/*
		for (int i = 0; i < elementList.size(); i++) {
			JSONObject elToAdd = new JSONObject();
			Element element = elementList.get(i);
			String sentence = element.getContent();
			elToAdd.put("text", sentence);
			elToAdd.put("sequence", elementList.get(i).getSequence());
			differenceInLength = 0;
			JSONArray filtersJson = new JSONArray();
			StringBuilder sb = new StringBuilder();
			JSONArray entitiesJson = new JSONArray();
			// Aggiunge le categories al JSONArray degli elementi
			List<ElementFilter> elementFilterList = elementFilterRepository.findByIdIdElement(elementList.get(i).getId());
			// Prende le categories su cui ciclare
			for (int k = 0; k < elementFilterList.size(); k++) {
				int idFilter = elementFilterList.get(k).getId().getIdFilter();
				Filter filterToAdd = filterRepository.findById(idFilter).get();
				// Vedo se la lingua selezionata è inglese o italiano
				HttpSession session = request.getSession();
				session.setAttribute(Constants.LANGUAGE, "en");
				String entityType = null;
				if (session.getAttribute(Constants.LANGUAGE).equals("it")) {
					entityType = filterToAdd.getNameIta();
				} else {
					// inglese
					entityType = filterToAdd.getNameEng();
				}
				String filterType = filterToAdd.getFilterType();
				sb.append(entityType);
				if (k < elementFilterList.size() - 1) sb.append(",");

				// Controllo se il filtro è una catogoria, una natura, un
				// attributo o una parte
				if (filterType.equals("C")) {
					if (categoriesInDocument.containsKey(entityType)) {
						int numberPresent = categoriesInDocument.get(entityType);
						categoriesInDocument.put(entityType, numberPresent + 1);
					} else {
						categoriesInDocument.put(entityType, 1);
					}
				} else if (filterType.equals("N")) {
					if (naturesInDocument.containsKey(entityType)) {
						int numberPresent = naturesInDocument.get(entityType);
						naturesInDocument.put(entityType, numberPresent + 1);
					} else {
						naturesInDocument.put(entityType, 1);
					}
				} else if (filterType.equals("A")) {
					if (attributesInDocument.containsKey(entityType)) {
						int numberPresent = attributesInDocument.get(entityType);
						attributesInDocument.put(entityType, numberPresent + 1);
					} else {
						attributesInDocument.put(entityType, 1);
					}
				} else if (filterType.equals("P")) {
					if (partiesInDocument.containsKey(entityType)) {
						int numberPresent = partiesInDocument.get(entityType);
						partiesInDocument.put(entityType, numberPresent + 1);
					} else {
						partiesInDocument.put(entityType, 1);
					}
				}
			}
			filtersJson.add(sb.toString());
			List<Entity> entitiesInElement = entityRepository.findByidElement(elementList.get(i).getId());
			// Ordino le entity per begin_text in modo da analizzarle in maniera
			// ordinata rispetto alla loro posizione nella frase.
			entitiesInElement.sort((Entity e1, Entity e2) -> {
				if (e1.getBeginText() == e2.getBeginText())
					return 0;
				return e1.getBeginText() > e2.getBeginText() ? 1 : -1;
			});
			for (int j = 0; j < entitiesInElement.size(); j++) {
				Entity entity = entitiesInElement.get(j);
				JSONObject entToAddInElement = createJSonFromEntity(entity);
				entitiesJson.add(entToAddInElement);
				if (isContactEntity(entity)) {
					JSONObject contactJSon = createJSonFromEntity(entity);
					contactJSon.put("elementSequence", elementList.get(i).getSequence());
					contactsInDocumentJsonarray.add(contactJSon);
					String sentenceWithSpan = createSpanForEntityInElement(elToAdd, sentence, entity, "contact");
					sentence = sentenceWithSpan;
				} else if (isDateEntity(entity)) {
					JSONObject dateJSon = createJSonFromEntity(entity);
					dateJSon.put("elementSequence", elementList.get(i).getSequence());
					datesInDocumentJsonArray.add(dateJSon);
					String sentenceWithSpan = createSpanForEntityInElement(elToAdd, sentence, entity, "date");
					sentence = sentenceWithSpan;
				} else if (entity.getEntityType().equals(Constants.DEFINITION)) {
					JSONObject definitionsJson = createJSonFromEntity(entity);
					definitionsJson.put("elementSequence", elementList.get(i).getSequence());
					definitionsInDocumentJsonarray.add(definitionsJson);
					String sentenceWithSpan = createSpanForEntityInElement(elToAdd, sentence, entity, "definition");
					sentence = sentenceWithSpan;
				}
			}
			elToAdd.put("filters", filtersJson);
			elToAdd.put("entities", entitiesJson);

			elementJsonArray.add(elToAdd);
		}
		*/
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for(int i = 0; i < elementJsonArray.size(); i++){
			jsonValues.add(elementJsonArray.getJSONObject(i));
		}
		jsonValues.sort((el1, el2) -> {
			return el1.getInt("sequence") - el2.getInt("sequence");
		});
		JSONArray sortedJSONArray = new JSONArray();
		for (JSONObject element : jsonValues) {
			sortedJSONArray.add(element);
		}
		document.put("elements", sortedJSONArray);

		fillJsonArray(categoriesInDocument, categoriesInDocumentJsonArray);
		fillJsonArray(naturesInDocument, naturesInDocumentJsonArray);
		fillJsonArray(attributesInDocument, attributesInDocumentJsonArray);
		fillJsonArray(partiesInDocument, partiesInDocumentJsonArray);
		
		List<ContractInvolvedParty> contractInvolvedPartyList = contractInvolvedPartyRepository.findByIdIdContract(Integer.parseInt(documentId));
		document.put("categories", categoriesInDocumentJsonArray);
		document.put("natures", naturesInDocumentJsonArray);
		document.put("attributes", attributesInDocumentJsonArray);
		document.put("parties", partiesInDocumentJsonArray);
		document.put("contacts", contactsInDocumentJsonarray);
		document.put("dates", datesInDocumentJsonArray);
		document.put("definitions", definitionsInDocumentJsonarray);
		document.put("involvedParties", contractInvolvedPartyList);		
		
		logger.info("-- retrieveDocument -- END");

		return document;
	}

	private void saveFileOnDB(MultipartFile document, int idContract) {
		/*
		 * Salvo il file fisico sul DB
		 */
		logger.info("Salvataggio file fisico su DB...");
		ContractFile contractFile = new ContractFile();
		try {
			contractFile.setFile_content(document.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contractFile.setId(idContract);

		contractFileRepository.save(contractFile);
		logger.info("File fisico salvato su DB");
	}


	private int differenceInLength = 0;

	private String createSpanForEntityInElement(JSONObject elToAdd, String sentence, Entity entity,
			String classOfSpan) {
		// TODO Auto-generated method stub
		logger.info("sentence: " + sentence);
		String spanEntity = "<span class='" + classOfSpan + "_" + entity.getId() + "'>" + entity.getEntityValue()
				+ "</span>";
		String beforeEntity = sentence.substring(0, entity.getBeginText() + differenceInLength);
		String afterEntity = sentence.substring(entity.getEndText() + differenceInLength);
		String result = beforeEntity + spanEntity + afterEntity;
		differenceInLength += result.length() - sentence.length();
		elToAdd.put("text", result);
		logger.info("result: " + result);
		return result;
	}

	private boolean isContactEntity(Entity entity) {
		return entity.getEntityType().equals(Constants.CON_ADDRES)
				|| entity.getEntityType().equals(Constants.CON_PERSON)
				|| entity.getEntityType().equals(Constants.CON_EMAIL)
				|| entity.getEntityType().equals(Constants.CON_FAX)
				|| entity.getEntityType().equals(Constants.CON_PHONE);
	}

	private boolean isDateEntity(Entity entity) {
		return entity.getEntityType().equals(Constants.DEADLINE)
				|| entity.getEntityType().equals(Constants.EFFECTIVE_DATE)
				|| entity.getEntityType().equals(Constants.DURATION);
	}

	private void fillJsonArray(Map<String, Integer> map, JSONArray jsonArray) {
		Set<String> partiesFound = map.keySet();
		for (String party : partiesFound) {
			JSONObject json = new JSONObject();
			json.put("name", party);
			json.put("count", map.get(party));
			jsonArray.add(json);
		}
	}
	/*
	private JSONObject createJSonFromEntity(Entity entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("entityValue", entity.getEntityValue());
		String type = DictionaryFoTranslation.getDictionaryForTranslation().getTransalaction(entity.getEntityType());
		if(type == null) json.put("entityType", entity.getEntityType());
		else json.put("entityType", type);
		json.put("beginText", entity.getBeginText());
		json.put("endText", entity.getEndText());
		return json;
	}
	*/
	private Element saveElementInDB(String element, int sequenceNumber, int contractId) {
		// TODO Auto-generated method stub
		logger.info("	element: " + element);
		// Prima cosa inserisco l'element nel DB
		Element element_db = new Element();
		element_db.setIdContract(contractId);
		element_db.setSequence(sequenceNumber);
		element_db.setContent(" " + element);
		logger.info("Salvataggio nel DB dell'Element: " + element_db);
		elementRepository.save(element_db);
		return element_db;
		
	}


}
