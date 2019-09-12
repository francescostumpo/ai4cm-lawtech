package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibm.snam.ai4legal.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer>{
	
	public Contract findByName(String name);
	
}
