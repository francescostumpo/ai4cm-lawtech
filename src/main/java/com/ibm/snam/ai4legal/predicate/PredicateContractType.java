package com.ibm.snam.ai4legal.predicate;

import java.util.function.Predicate;

import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.util.Constants;

public class PredicateContractType implements Predicate<Contract>{

	private String filter;
	
	public PredicateContractType(String filter) {
		this.filter = filter;
	}

	@Override
	public boolean test(Contract contract) {
		if(filter.equals(Constants.ALL)) return true;
		if(contract.getContractType() == null) return false; 
		return contract.getContractType().equals(filter);
	}
	
	
}
