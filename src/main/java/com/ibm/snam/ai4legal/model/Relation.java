package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_RELATIONS")
public class Relation {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_RELATIONS", nullable = false)
    private int id;

	@Column(name="ID_ELEMENT")
	private int idElement; 

	@Column(name="RELATION_TYPE")
	private String relationType = null; 

	@Column(name="RELATION_SENTENCE")
	private String relationSentence = null; 

	@Column(name="ID_ENTITY1")
	private int idEntity1; 

	@Column(name="ID_ENTITY2")
	private int idEntity2; 

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
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getRelationSentence() {
		return relationSentence;
	}
	public void setRelationSentence(String relationSentence) {
		this.relationSentence = relationSentence;
	}
	public int getIdEntity1() {
		return idEntity1;
	}
	public void setIdEntity1(int idEntity1) {
		this.idEntity1 = idEntity1;
	}
	public int getIdEntity2() {
		return idEntity2;
	}
	public void setIdEntity2(int idEntity2) {
		this.idEntity2 = idEntity2;
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
		return "Relation [id=" + id + ", idElement=" + idElement + ", relationType=" + relationType
				+ ", relationSentence=" + relationSentence + ", idEntity1=" + idEntity1 + ", idEntity2=" + idEntity2
				+ ", beginText=" + beginText + ", endText=" + endText + "]";
	}
	
	
}
