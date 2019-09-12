package com.ibm.snam.ai4legal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContractInvolvedPartyId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2719135201333215772L;
	
	@Column(name="ID_CONTRACT")
	private int idContract;
	
	@Column(name="NAME_INVOLVED_PARTY")
	private String nameInvolvedParty;

	public int getIdContract() {
		return idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	public String getNameInvolvedParty() {
		return nameInvolvedParty;
	}

	public void setNameInvolvedParty(String nameInvolvedParty) {
		this.nameInvolvedParty = nameInvolvedParty;
	}

	@Override
	public String toString() {
		return "ContractInvolvedPartyId [idContract=" + idContract + ", idInvolvedParty=" + nameInvolvedParty + "]";
	}
	
}
