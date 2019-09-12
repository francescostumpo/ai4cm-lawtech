package com.ibm.snam.ai4legal.repositories;


import org.springframework.data.repository.CrudRepository;
import com.ibm.snam.ai4legal.model.InvolvedParty;

public interface InvolvedPartyRepository extends CrudRepository<InvolvedParty, String>{
	
}
