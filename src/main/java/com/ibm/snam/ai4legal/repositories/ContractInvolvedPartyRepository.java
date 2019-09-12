package com.ibm.snam.ai4legal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.ContractInvolvedParty;
import com.ibm.snam.ai4legal.model.ContractInvolvedPartyId;

public interface ContractInvolvedPartyRepository extends CrudRepository<ContractInvolvedParty, ContractInvolvedPartyId>{
	
	public List<ContractInvolvedParty> findByIdNameInvolvedParty(String name);
	public List<ContractInvolvedParty> findByIdIdContract(int id);
}
