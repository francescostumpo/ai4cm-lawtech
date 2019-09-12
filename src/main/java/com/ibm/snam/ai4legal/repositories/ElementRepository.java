package com.ibm.snam.ai4legal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.Element;

public interface ElementRepository extends JpaRepository<Element, Integer>{

	public List<Element> findByIdContract(int idContract); 
}
