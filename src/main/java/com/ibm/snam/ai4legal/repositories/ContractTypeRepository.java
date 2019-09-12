package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.ContractType;

public interface ContractTypeRepository extends CrudRepository<ContractType, String>{
	
}
