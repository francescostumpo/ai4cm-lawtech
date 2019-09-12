package ibm.com.snam.ai4legal.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.snam.ai4legal.model.Element;
import com.ibm.snam.ai4legal.model.ElementFilter;
import com.ibm.snam.ai4legal.model.Entity;
import com.ibm.snam.ai4legal.model.Filter;
import com.ibm.snam.ai4legal.repositories.ElementFilterRepository;
import com.ibm.snam.ai4legal.repositories.EntityRepository;
import com.ibm.snam.ai4legal.repositories.FilterRepository;
import com.ibm.snam.ai4legal.util.Constants;
import com.ibm.snam.ai4legal.util.DictionaryFoTranslation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RetrieveContractFromDBThread implements Runnable {

	private int beginIndex;
	private int endIndex;
	private List<Element> elementList;
	private ElementFilterRepository elementFilterRepository;
	private FilterRepository filterRepository;
	private Map<String, Integer> categoriesInDocument;
	private Map<String, Integer> naturesInDocument;
	private Map<String, Integer> attributesInDocument;
	private Map<String, Integer> partiesInDocument;
	private EntityRepository entityRepository;
	private JSONArray datesInDocumentJsonArray;
	private JSONArray contactsInDocumentJsonarray;
	private JSONArray definitionsInDocumentJsonarray;
	private JSONArray elementJsonArray;
	private HttpSession session;
	
	private Logger logger = LoggerFactory.getLogger(RetrieveContractFromDBThread.class);
	private Semaphore sem;
	private CountDownLatch latch;

	public RetrieveContractFromDBThread(Semaphore semaphore, CountDownLatch latch) {
		this.sem = semaphore;
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("Thread " + Thread.currentThread().getId() + " started with range : " + beginIndex + " - " + endIndex);
		try {
			for (int i = beginIndex; i < endIndex; i++) {
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
				List<ElementFilter> elementFilterList = elementFilterRepository
						.findByIdIdElement(elementList.get(i).getId());
				// Prende le categories su cui ciclare
				for (int k = 0; k < elementFilterList.size(); k++) {
					int idFilter = elementFilterList.get(k).getId().getIdFilter();
					Filter filterToAdd = filterRepository.findById(idFilter).get();
					// Vedo se la lingua selezionata è inglese o italiano
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
						sem.acquire();
						if (categoriesInDocument.containsKey(entityType)) {
							int numberPresent = categoriesInDocument.get(entityType);
							categoriesInDocument.put(entityType, numberPresent + 1);
						} else {
							categoriesInDocument.put(entityType, 1);
						}
						sem.release();
					} else if (filterType.equals("N")) {
						sem.acquire();
						if (naturesInDocument.containsKey(entityType)) {
							int numberPresent = naturesInDocument.get(entityType);
							naturesInDocument.put(entityType, numberPresent + 1);
						} else {
							naturesInDocument.put(entityType, 1);
						}
						sem.release();
					} else if (filterType.equals("A")) {
						sem.acquire();
						if (attributesInDocument.containsKey(entityType)) {
							int numberPresent = attributesInDocument.get(entityType);
							attributesInDocument.put(entityType, numberPresent + 1);
						} else {
							attributesInDocument.put(entityType, 1);
						}
						sem.release();
					} else if (filterType.equals("P")) {
						sem.acquire();
						if (partiesInDocument.containsKey(entityType)) {
							int numberPresent = partiesInDocument.get(entityType);
							partiesInDocument.put(entityType, numberPresent + 1);
						} else {
							partiesInDocument.put(entityType, 1);
						}
						sem.release();
					}
				}
				filtersJson.add(sb.toString());
				List<Entity> entitiesInElement = entityRepository.findByidElement(elementList.get(i).getId());
				// Ordino le entity per begin_text in modo da analizzarle in
				// maniera ordinata rispetto alla loro posizione nella frase.
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Thread " + Thread.currentThread().getId() + " terminated.");
		latch.countDown();
	}

	private int differenceInLength;

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

	private JSONObject createJSonFromEntity(Entity entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("entityValue", entity.getEntityValue());
		String language = ""+session.getAttribute(Constants.LANGUAGE);
		String type = DictionaryFoTranslation.getDictionaryForTranslation().getTransalaction(entity.getEntityType(), language);
		if (type == null) 
			json.put("entityType", entity.getEntityType());
		else
			json.put("entityType", type);
		json.put("beginText", entity.getBeginText());
		json.put("endText", entity.getEndText());
		return json;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<Element> getElementList() {
		return elementList;
	}

	public void setElementList(List<Element> elementList) {
		this.elementList = elementList;
	}

	public ElementFilterRepository getElementFilterRepository() {
		return elementFilterRepository;
	}

	public void setElementFilterRepository(ElementFilterRepository elementFilterRepository) {
		this.elementFilterRepository = elementFilterRepository;
	}

	public FilterRepository getFilterRepository() {
		return filterRepository;
	}

	public void setFilterRepository(FilterRepository filterRepository) {
		this.filterRepository = filterRepository;
	}



	public Map<String, Integer> getCategoriesInDocument() {
		return categoriesInDocument;
	}

	public void setCategoriesInDocument(Map<String, Integer> categoriesInDocument) {
		this.categoriesInDocument = categoriesInDocument;
	}

	public Map<String, Integer> getNaturesInDocument() {
		return naturesInDocument;
	}

	public void setNaturesInDocument(Map<String, Integer> naturesInDocument) {
		this.naturesInDocument = naturesInDocument;
	}

	public Map<String, Integer> getAttributesInDocument() {
		return attributesInDocument;
	}

	public void setAttributesInDocument(Map<String, Integer> attributesInDocument) {
		this.attributesInDocument = attributesInDocument;
	}

	public Map<String, Integer> getPartiesInDocument() {
		return partiesInDocument;
	}

	public void setPartiesInDocument(Map<String, Integer> partiesInDocument) {
		this.partiesInDocument = partiesInDocument;
	}

	public EntityRepository getEntityRepository() {
		return entityRepository;
	}

	public void setEntityRepository(EntityRepository entityRepository) {
		this.entityRepository = entityRepository;
	}

	public JSONArray getDatesInDocumentJsonArray() {
		return datesInDocumentJsonArray;
	}

	public void setDatesInDocumentJsonArray(JSONArray datesInDocumentJsonArray) {
		this.datesInDocumentJsonArray = datesInDocumentJsonArray;
	}

	public JSONArray getContactsInDocumentJsonarray() {
		return contactsInDocumentJsonarray;
	}

	public void setContactsInDocumentJsonarray(JSONArray contactsInDocumentJsonarray) {
		this.contactsInDocumentJsonarray = contactsInDocumentJsonarray;
	}

	public JSONArray getDefinitionsInDocumentJsonarray() {
		return definitionsInDocumentJsonarray;
	}

	public void setDefinitionsInDocumentJsonarray(JSONArray definitionsInDocumentJsonarray) {
		this.definitionsInDocumentJsonarray = definitionsInDocumentJsonarray;
	}

	public JSONArray getElementJsonArray() {
		return elementJsonArray;
	}

	public void setElementJsonArray(JSONArray elementJsonArray) {
		this.elementJsonArray = elementJsonArray;
	}

	public void setSession(HttpSession session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
}
