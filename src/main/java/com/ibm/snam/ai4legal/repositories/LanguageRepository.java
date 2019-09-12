package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer>{
	
	public Language findByName(String name);
	
}
