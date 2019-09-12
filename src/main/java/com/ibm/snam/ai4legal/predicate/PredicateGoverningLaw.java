package com.ibm.snam.ai4legal.predicate;


import java.util.List;
import java.util.function.Predicate;
import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.ContractGoverningLaw;
import com.ibm.snam.ai4legal.repositories.ContractGoverningLawRepository;
import com.ibm.snam.ai4legal.util.Constants;

public class PredicateGoverningLaw implements Predicate<Contract>{
	
	private String filter;
	private ContractGoverningLawRepository contractGoverningLawRepository;
	
	public PredicateGoverningLaw(String filter, ContractGoverningLawRepository contractGoverningLawRepository){
		this.filter = filter;
		this.contractGoverningLawRepository = contractGoverningLawRepository;
	}
	
	@Override
	public boolean test(Contract contract) {
		// TODO Auto-generated method stub
		if(filter.equals(Constants.ALL)) return true;
		List<ContractGoverningLaw> contractGoverningLawList = contractGoverningLawRepository.findByIdNameGoverningLaw(filter);
		for(ContractGoverningLaw contractGoverningLaw : contractGoverningLawList){
			if(contractGoverningLaw.getId().getIdContract() == contract.getId()){
				return true;
			}
		}
		return false;
	}

}
