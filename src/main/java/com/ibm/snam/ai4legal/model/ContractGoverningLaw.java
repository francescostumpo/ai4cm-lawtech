package com.ibm.snam.ai4legal.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AI4CM_CONTRACT_GOVERNING_LAW")
public class ContractGoverningLaw {
	
	@EmbeddedId
	private ContractGoverningLawId id;

	public ContractGoverningLawId getId() {
		return id;
	}

	public void setId(ContractGoverningLawId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContractGoverningLaw [id=" + id + "]";
	}
	
}
