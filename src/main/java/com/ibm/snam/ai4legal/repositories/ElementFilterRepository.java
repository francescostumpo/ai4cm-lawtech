package com.ibm.snam.ai4legal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibm.snam.ai4legal.model.ElementFilter;
import com.ibm.snam.ai4legal.model.ElementFilterId;

public interface ElementFilterRepository extends JpaRepository<ElementFilter, ElementFilterId>{
	
	public List<ElementFilter> findByIdIdElement(int idElement);
	
}
