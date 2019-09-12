package com.ibm.snam.ai4legal.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AI4CM_ELEMENT_FILTER")
public class ElementFilter {

	@EmbeddedId
	private ElementFilterId id;

	public ElementFilterId getId() {
		return id;
	}

	public void setId(ElementFilterId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ElementFilter [id=" + id + "]";
	}
	
}
