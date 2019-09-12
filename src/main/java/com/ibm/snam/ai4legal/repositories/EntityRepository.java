package com.ibm.snam.ai4legal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.Entity;

public interface EntityRepository extends CrudRepository<Entity, Integer>{
	
	public List<Entity> getById(int documentId); 
	public List<Entity> findByidElement(int elementId); 

}
