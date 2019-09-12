package com.ibm.snam.ai4legal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ElementFilterId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5524478523994293339L;

	@Column(name = "ID_ELEMENT")
	private int idElement;
	@Column(name = "ID_FILTER")
	private int idFilter;
	
	public int getIdElement() {
		return idElement;
	}
	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}
	public int getIdFilter() {
		return idFilter;
	}
	public void setIdFilter(int idFilter) {
		this.idFilter = idFilter;
	}
	@Override
	public String toString() {
		return "ElementFilterId [idElement=" + idElement + ", idFilter=" + idFilter + "]";
	}
	
}
