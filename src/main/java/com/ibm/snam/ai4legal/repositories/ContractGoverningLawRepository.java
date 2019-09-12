package com.ibm.snam.ai4legal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.ContractGoverningLaw;
import com.ibm.snam.ai4legal.model.ContractGoverningLawId;

public interface ContractGoverningLawRepository extends CrudRepository<ContractGoverningLaw, ContractGoverningLawId>{
	public List<ContractGoverningLaw> findByIdNameGoverningLaw(String name);
	public List<ContractGoverningLaw> findByIdIdContract(int idContract);
}
