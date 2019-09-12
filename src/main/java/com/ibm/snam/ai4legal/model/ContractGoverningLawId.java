package com.ibm.snam.ai4legal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContractGoverningLawId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="NAME_GOVERNING_LAW")
	private String nameGoverningLaw;
	
	@Column(name = "ID_CONTRACT")
	private int idContract;

	public String getNameGoverningLaw() {
		return nameGoverningLaw;
	}

	public void setIdGoverningLaw(String nameGoverningLaw) {
		this.nameGoverningLaw = nameGoverningLaw;
	}

	public int getIdContract() {
		return idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	@Override
	public String toString() {
		return "ContractGoverningLawId [nameGoverningLaw=" + nameGoverningLaw + ", idContract=" + idContract + "]";
	}

}
