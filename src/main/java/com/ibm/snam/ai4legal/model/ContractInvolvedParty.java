package com.ibm.snam.ai4legal.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AI4CM_CONTRACT_INVOLVED_PARTY")
public class ContractInvolvedParty {
	
	@EmbeddedId
	private ContractInvolvedPartyId id;

	public ContractInvolvedPartyId getId() {
		return id;
	}

	public void setId(ContractInvolvedPartyId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContractInvolvedParty [id=" + id + "]";
	}
	
}
