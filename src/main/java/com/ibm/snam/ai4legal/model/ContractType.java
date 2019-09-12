package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="AI4CM_CONTRACT_TYPE")
public class ContractType {
	
	@Id
	@Column(name = "TYPE")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ContractType [ type=" + type + "]";
	}
	
}
