package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	public Contact findByIdContractInAndEmailIn(int idContract, String email);
	public Contact findByIdContractInAndFaxIn(int idContract, String fax);
	public Contact findByIdContractInAndPhoneIn(int idContract, String phone);
	public Contact findByIdContractInAndAddressIn(int idContract, String address);
	public Contact findByIdContractInAndPersonName(int idContract, String personName);
	
}
