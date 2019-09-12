package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_CONTRACT")
public class Contract {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CONTRACT", nullable = false)
	private int id;
	
	@Column(name="ID_LANGUAGE")
	private int idLanguage;

	@Column(name="NAME_CONTRACT_TYPE")
	private String contractType;

	@Column(name="CONTRACT_TERMS")
	private String contractTerms;
	
	@Column(name="HTML_CONTENT")
	private String htmlContent;
	
	@Column(name="EFFECTIVE_DATE")
	private String effectiveDate;

	@Column(name="NAME")
	private String name;
	
	@Column(name="CREATION_DATE")
	private String creationDate;
	
	@Column(name="CREATION_USER")
	private String creationUser;
		
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	} 
	
	public Integer getId() {
		return id;
	}
	
	public String getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public int getIdLanguage() {
		return idLanguage;
	}
	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getContractTerms() {
		return contractTerms;
	}
	public void setContractTerms(String contractTerms) {
		this.contractTerms = contractTerms;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String string) {
		this.htmlContent = string;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String  idContractType) {
		this.contractType = idContractType;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", idLanguage=" + idLanguage + ", contractType=" + contractType
				+ ", contractTerms=" + contractTerms + ", htmlContent=" + htmlContent + ", effectiveDate="
				+ effectiveDate + ", name=" + name + ", creationDate=" + creationDate + ", creationUser=" + creationUser
				+ "]";
	}

}
