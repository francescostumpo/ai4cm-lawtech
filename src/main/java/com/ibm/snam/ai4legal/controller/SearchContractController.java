package com.ibm.snam.ai4legal.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.ContractGoverningLaw;
import com.ibm.snam.ai4legal.model.ContractInvolvedParty;
import com.ibm.snam.ai4legal.model.ContractType;
import com.ibm.snam.ai4legal.model.GoverningLaw;
import com.ibm.snam.ai4legal.model.InvolvedParty;
import com.ibm.snam.ai4legal.predicate.PredicateContractType;
import com.ibm.snam.ai4legal.predicate.PredicateGoverningLaw;
import com.ibm.snam.ai4legal.predicate.PredicateInvolvedParty;
import com.ibm.snam.ai4legal.repositories.ContractGoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.ContractInvolvedPartyRepository;
import com.ibm.snam.ai4legal.repositories.ContractRepository;
import com.ibm.snam.ai4legal.repositories.ContractTypeRepository;
import com.ibm.snam.ai4legal.repositories.GoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.InvolvedPartyRepository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@CrossOrigin(origins = "*")
public class SearchContractController {
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	ContractTypeRepository contractTypeRepository;
	
	@Autowired
	InvolvedPartyRepository involvedPartyRepository;
	
	@Autowired
	ContractInvolvedPartyRepository contractInvolvedPartyRepository;
	
	@Autowired
	ContractGoverningLawRepository contractGoverningLawRepository;
	
	@Autowired
	GoverningLawRepository governingLawRepository;
	
	Logger logger = LoggerFactory.getLogger(SearchContractController.class);
	
	@CrossOrigin
	@PostMapping("/searchContract")
	public JSONArray searchContract(@RequestBody JSONObject searchForm){
		logger.info("searchContract -- INIT --");
		logger.info("search form received: " + searchForm);
		String type = searchForm.getString("contractType");
		//String inForce = searchForm.getString("inForce");
		String governingLaw = searchForm.getString("governingLaw");
		String party = searchForm.getString("party");
		
		Predicate<Contract> predicateContractInForce = contract -> {
			return true;
		};
		Predicate<Contract> predicateGoverningLaw = new PredicateGoverningLaw(governingLaw, contractGoverningLawRepository);
		Predicate<Contract> predicateTypeContract = new PredicateContractType(type);
		Predicate<Contract> predicateContractParty = new PredicateInvolvedParty(party,contractInvolvedPartyRepository);
	
		List<Contract> allContracts = new LinkedList<>();
		Iterable<Contract> allContractsIterable = contractRepository.findAll();
		allContractsIterable.forEach(allContracts::add);
		Stream<Contract> contractInForce = allContracts.parallelStream().filter(predicateContractInForce);
		Stream<Contract> contractOfOneType = contractInForce.parallel().filter(predicateTypeContract);
		Stream<Contract> contractWithParty = contractOfOneType.parallel().filter(predicateContractParty);
		Stream<Contract> contractGoverningLaw = contractWithParty.parallel().filter(predicateGoverningLaw);
		JSONArray contractJsonArray = new JSONArray();
		contractGoverningLaw.forEach(contract -> {
			JSONObject contractJSon = new JSONObject();
			contractJSon.put("idContract", "" + contract.getId());
			
			List<ContractGoverningLaw> contractGoverningLawList = contractGoverningLawRepository.findByIdIdContract(contract.getId());
			String governingLaws = "";
			for(ContractGoverningLaw contractGoverningLaw2 : contractGoverningLawList){
				GoverningLaw governingLaw2 = governingLawRepository.findById(contractGoverningLaw2.getId().getNameGoverningLaw()).get();
				governingLaws += governingLaw2.getType() + ", ";
			}
			contractJSon.put("governingLaw", governingLaws);
			contractJSon.put("effectiveDate", contract.getEffectiveDate());
			contractJSon.put("contractType", contract.getContractType());
			contractJSon.put("creationDate", contract.getCreationDate());
			contractJSon.put("creationUser", contract.getCreationUser());
			
			//Set parties
			List<ContractInvolvedParty> contractInvolvedParty = contractInvolvedPartyRepository.findByIdIdContract(contract.getId());
			String parties = "";
			for (ContractInvolvedParty contractInv : contractInvolvedParty) {
				parties += contractInv.getId().getNameInvolvedParty() + ", ";				
			}
			contractJSon.put("parties", parties);
			contractJSon.put("name", contract.getName());
			contractJsonArray.add(contractJSon);
		});
		JSONObject response = new JSONObject();
		response.put("contracts:" , contractJsonArray);
		logger.info("searchContract -- END --");
		return contractJsonArray;
	}
	
	@GetMapping(value = "/getAllInvolvedParties")
	public List<InvolvedParty> getAllInvolvedParties(){
		logger.info("getAllInvolvedParties -- INIT --");
		List<InvolvedParty> allInvolvedParty = new LinkedList<>();
		Iterable<InvolvedParty> inv = involvedPartyRepository.findAll();
		inv.forEach(allInvolvedParty::add);
		logger.info("All involved parties:" + allInvolvedParty);
		logger.info("getAllInvolvedParties -- INIT --");
		return allInvolvedParty;
	}
	
	@GetMapping(value = "/getAllContractType")
	public List<ContractType> getAllContractType(){
		logger.info("getAllContractType -- INIT --");
		List<ContractType> allContractType = new LinkedList<>();
		Iterable<ContractType> inv = contractTypeRepository.findAll();
		inv.forEach(allContractType::add);
		logger.info("All contract type: " + allContractType);
		logger.info("getAllContractType -- END --");
		return allContractType;
	}
	
	@GetMapping(value = "/getAllGoverningLaw")
	public List<GoverningLaw> getAllGoverningLaw(){
		logger.info("getAllGoverningLaw -- INIT --");
		List<GoverningLaw> allGoverningLaw = new LinkedList<>();
		Iterable<GoverningLaw> gov = governingLawRepository.findAll();
		gov.forEach(allGoverningLaw::add);
		logger.info("All governing law: " + allGoverningLaw);
		logger.info("getAllGoverningLaw -- END --");
		return allGoverningLaw;
	}
}
