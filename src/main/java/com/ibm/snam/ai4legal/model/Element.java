package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_ELEMENTS")
public class Element{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ELEMENT", nullable = false)
	private int id;
	
	@Column(name="ID_CONTRACT")
	private int idContract;
	
	@Column(name="ID_PARENT_ELEMENT")
	private int idParentElement;

	@Column(name="SEQUENCE")
	private int sequence;

	@Column(name="CONTENT")
	private String content;

	
	public void setId(int id) {
		this.id = id;
	} 
	
	public Integer getId() {
		return id;
	}
	
	public int getIdContract() {
		return idContract;
	}
	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIdParentElement() {
		return idParentElement;
	}
	public void setIdParentElement(int idParentElement) {
		this.idParentElement = idParentElement;
	}
	@Override
	public String toString() {
		return "Element [id=" + id + ", idContract=" + idContract + ", idParentElement=" + idParentElement +", sequence=" + sequence + ", content=" + content
				+ "]";
	}
	
}
