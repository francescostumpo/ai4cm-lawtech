package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.Filter;

public interface FilterRepository extends CrudRepository<Filter, Integer>{
	
	public Filter findByFilterTypeInAndEntityTypeIn(String filterType, String entityType);
	
}
