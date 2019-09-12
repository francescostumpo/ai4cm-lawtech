package com.ibm.snam.ai4legal.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_LANGUAGES")
public class Language{
	
	@Id 
	@Column(name = "ID_LANGUAGE", nullable = false)
    private int id;

	@Column(name="CD_LANGUAGE")
	private String name = null; 

	@Column(name="DS_LANGUAGE")
	private String description = null; 

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
