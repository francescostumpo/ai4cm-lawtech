package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_INVOLVED_PARTY")
public class InvolvedParty{
	
	@Id 
	@Column(name="NAME")
	private String name;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Column(name="ADDRESS")
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "InvolvedParty [ name=" + name + ", registrationNumber="
				+ registrationNumber + ", address=" + address + "]";
	}	
}
