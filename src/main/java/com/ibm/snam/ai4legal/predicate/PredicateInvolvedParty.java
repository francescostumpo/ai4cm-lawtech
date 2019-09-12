package com.ibm.snam.ai4legal.predicate;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.ContractInvolvedParty;
import com.ibm.snam.ai4legal.repositories.ContractInvolvedPartyRepository;
import com.ibm.snam.ai4legal.util.Constants;

public class PredicateInvolvedParty implements Predicate<Contract>{
	
	private String filter;
	private ContractInvolvedPartyRepository contractInvolvedPartyRepository;
	
	public PredicateInvolvedParty(String filter, ContractInvolvedPartyRepository contractInvolvedPartyRepository) {
		this.filter = filter;
		this.contractInvolvedPartyRepository = contractInvolvedPartyRepository;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean test(Contract contract) {
		if(filter.equals(Constants.ALL)) return true;
		System.out.println(contractInvolvedPartyRepository == null);
		List<ContractInvolvedParty> contractInvolvedParty = contractInvolvedPartyRepository.findByIdNameInvolvedParty(filter);
		for (ContractInvolvedParty contractInvolvedParty2 : contractInvolvedParty) {
			if(contractInvolvedParty2.getId().getIdContract() == contract.getId()){
				return true;
			}
		}
		return false;
	}

}
