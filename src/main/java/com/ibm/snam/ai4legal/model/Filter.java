package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AI4CM_FILTERS")
public class Filter {

	@Id
	@Column(name = "ID_FILTER")
	private int id;
	
	@Column(name = "FILTER_TYPE")
	private String filterType;
	
	@Column(name = "ENTITY_TYPE")
	private String entityType;
	
	@Column(name = "NAME_ENG")
	private String nameEng;
	
	@Column(name = "NAME_ITA")
	private String nameIta;
	
	@Column(name = "DESCRIPTION")
	private String description;

	
	public void setId(int id) {
		this.id = id;
	} 
	
	public int getId() {
		return id;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameIta() {
		return nameIta;
	}

	public void setNameIta(String nameIta) {
		this.nameIta = nameIta;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Filter [id=" + id + ", filterType=" + filterType + ", entityType=" + entityType + ", nameEng=" + nameEng
				+ ", nameIta=" + nameIta + ", description=" + description + "]";
	}
	
	
}
