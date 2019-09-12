package ibm.com.snam.ai4legal.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.Element;
import com.ibm.snam.ai4legal.model.ElementFilter;
import com.ibm.snam.ai4legal.model.ElementFilterId;
import com.ibm.snam.ai4legal.model.Entity;
import com.ibm.snam.ai4legal.model.Filter;
import com.ibm.snam.ai4legal.repositories.ContractGoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.ContractInvolvedPartyRepository;
import com.ibm.snam.ai4legal.repositories.ContractTypeRepository;
import com.ibm.snam.ai4legal.repositories.ElementFilterRepository;
import com.ibm.snam.ai4legal.repositories.EntityRepository;
import com.ibm.snam.ai4legal.repositories.FilterRepository;
import com.ibm.snam.ai4legal.repositories.GoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.InvolvedPartyRepository;
import com.ibm.snam.ai4legal.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ibm.com.snam.ai4legal.dbutilities.ContratTypeUtility;
import ibm.com.snam.ai4legal.dbutilities.GoverningLawUtility;
import ibm.com.snam.ai4legal.dbutilities.InvolvedPartyUtility;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UploadContractOnDBThread implements Runnable {

	private JSONArray entities;
	private List<Element> elementsInContract;
	private ContractTypeRepository contractTypeRepository;
	private EntityRepository entityRepository;
	private ElementFilterRepository elementFilterRepository;
	private String id_contract;
	private FilterRepository filterRepository;
	private InvolvedPartyRepository involvedPartyRepository;
	private GoverningLawRepository governingLawRepository;
	private ContractInvolvedPartyRepository contractInvolvedPartyRepository;
	private ContractGoverningLawRepository contractGoverningLawRepository;
	private CountDownLatch latch;
	private int beginIndex;
	private int endIndex;

	Logger logger = LoggerFactory.getLogger(UploadContractOnDBThread.class);

	private boolean terminated = false;

	public UploadContractOnDBThread(int beginIndex, int endIndex, JSONArray entities, List<Element> elementsInContract,
			ContractTypeRepository contractTypeRepository, EntityRepository entityRepository,
			ElementFilterRepository elementFilterRepository, String id_contract, FilterRepository filterRepository,
			InvolvedPartyRepository involvedPartyRepository, GoverningLawRepository governingLawRepository,
			ContractInvolvedPartyRepository contractInvolvedPartyRepository,
			ContractGoverningLawRepository contractGoverningLawRepository, CountDownLatch latch) {
		super();
		this.entities = entities;
		this.elementsInContract = elementsInContract;
		this.contractTypeRepository = contractTypeRepository;
		this.entityRepository = entityRepository;
		this.elementFilterRepository = elementFilterRepository;
		this.id_contract = id_contract;
		this.filterRepository = filterRepository;
		this.involvedPartyRepository = involvedPartyRepository;
		this.governingLawRepository = governingLawRepository;
		this.contractInvolvedPartyRepository = contractInvolvedPartyRepository;
		this.contractGoverningLawRepository = contractGoverningLawRepository;
		this.latch = latch;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	private Contract contract;

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("Thread " + Thread.currentThread() + " partito ... " + beginIndex + " - " + endIndex);
		terminated = false;
		try {
			for (int i = beginIndex; i < endIndex; i++) {
				// Prendo l'i-esima entity
				JSONObject entity = (JSONObject) entities.get(i);
				logger.info("Thread " + Thread.currentThread().getId() + " analyzing entity: " + entity);
				int count = entity.getInt("count");
				String textOfEntity = entity.getString("text");
				String typeOfEntity = entity.getString("type");

				// Adesso devo cercare nei vari elements se è presente la entity
				for (int j = 0; j < elementsInContract.size() && count > 0; j++) {
					if (typeOfEntity.equals(Constants.CONTRACT_TYPE)) {
						ContratTypeUtility.setContratTypeForContract(contract, textOfEntity, contractTypeRepository);
						break;
					}
					int search_index = 0;
					Element element_db = elementsInContract.get(j);
					String element = element_db.getContent();
					int id_element = element_db.getId();
					// System.out.println(" --> analyzing element: " + element);
					while (count > 0) {
						if (typeOfEntity.equals(Constants.DEFINITION)) {
							// Qui devo fare una ricerca della entity
							// all'interno
							// del testo aggiungendo le virgolette
							textOfEntity = "\"" + entity.getString("text") + "\"";
						}
						int begin_index = element.indexOf(textOfEntity, search_index);
						if (begin_index == -1) {
							// Non ho trovato la entity nella sottostringa che
							// va da
							// search_index fino alla fine della stringa. Passo
							// all'
							// element successivo successiva
							break;
						} else {
							count--;
							String firstLettersOfType = entity.getString("type").substring(0, 4);
							saveEntityInDB(id_element, textOfEntity, begin_index, typeOfEntity);
							if (firstLettersOfType.equals(Constants.CAT)) {
								saveFilterElementInDB("C", typeOfEntity.substring(4), id_element);
							} else if (firstLettersOfType.equals(Constants.NAT)) {
								saveFilterElementInDB("N", typeOfEntity.substring(4), id_element);
							} else if (firstLettersOfType.equals(Constants.ATT)) {
								saveFilterElementInDB("A", typeOfEntity.substring(4), id_element);
								if (typeOfEntity.equals(Constants.EFFECTIVE_DATE)) {
									contract.setEffectiveDate(textOfEntity);
								}
							} else if (firstLettersOfType.equals(Constants.PAR)) {
								saveFilterElementInDB("P", typeOfEntity.substring(4), id_element);
							} else {
								// Devo controllare se rappresenta una entity
								// particolare
								if (typeOfEntity.equals(Constants.GOVERNING_LAW)) {
									GoverningLawUtility.uploadGoverningLaw(id_contract, textOfEntity,
											governingLawRepository, contractGoverningLawRepository);
								} else if (typeOfEntity.equals(Constants.INVOLVED_PARTY)) {
									InvolvedPartyUtility.uploadInvolvedParty(id_contract, textOfEntity,
											involvedPartyRepository, contractInvolvedPartyRepository);
								}
							}
							// Aggiorno l'indice di ricerca
							search_index += begin_index + textOfEntity.length() + 1;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			terminated = false;
		}
		terminated = true;
		latch.countDown();
		logger.info("Thread " + Thread.currentThread() + " terminato. Left: " + latch.getCount());
	}

	public boolean isTerminatedCorrectly() {
		return terminated;
	}

	private void saveEntityInDB(int id_element, String entityValue, int begin_index, String type) {
		logger.info("-- saveEntityInDB -- INIT");
		Entity entity_db = new Entity();
		
		if(type.equals(Constants.DEFINITION)){
			//Tolgo le virgolette
			entity_db.setEntityValue(entityValue.substring(1,entityValue.length()-1));
		}
		else{
			entity_db.setEntityValue(entityValue);
		}
		entity_db.setIdElement(id_element);
		entity_db.setBeginText(begin_index);
		entity_db.setEndText(begin_index + entityValue.length());
		entity_db.setEntityType(type);
		logger.info("Salvataggio a DB della entity : " + entity_db);
		entityRepository.save(entity_db);
		logger.info("-- saveEntityInDB -- END");
	}

	private void saveFilterElementInDB(String filterType, String entityType, int idElement) {
		// TODO Auto-generated method stub
		logger.info(" saveFilterElementInDB -- INIT --");
		Filter categoryFilter = filterRepository.findByFilterTypeInAndEntityTypeIn(filterType, entityType);
		ElementFilterId id = new ElementFilterId();
		id.setIdElement(idElement);
		id.setIdFilter(categoryFilter.getId());
		ElementFilter elementFilter = new ElementFilter();
		elementFilter.setId(id);
		logger.info("Saving elementFilter on DB : " + elementFilter);
		elementFilterRepository.save(elementFilter);
		logger.info(" saveFilterElementInDB -- END --");

	}

}
