package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="AI4CM_ENTITIES")
public class Entity{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ENTITY", nullable = false)
    private int id;

	@Column(name="ID_ELEMENT")
	private int idElement; 

	@Column(name="ENTITY_TYPE")
	private String entityType = null; 

	@Column(name="ENTITY_VALUE")
	private String entityValue = null; 

	@Column(name="BEGIN_TEXT")
	private int beginText; 

	@Column(name="END_TEXT")
	private int endText; 
	
	public void setId(int id) {
		this.id = id;
	} 
	
	public Integer getId() {
		return id;
	}
	
	public int getIdElement() {
		return idElement;
	}
	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getEntityValue() {
		return entityValue;
	}
	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}
	public int getBeginText() {
		return beginText;
	}
	public void setBeginText(int beginText) {
		this.beginText = beginText;
	}
	public int getEndText() {
		return endText;
	}
	public void setEndText(int endText) {
		this.endText = endText;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", idElement=" + idElement + ", entityType=" + entityType + ", entityValue="
				+ entityValue + ", beginText=" + beginText + ", endText=" + endText + "]";
	}
	
}
