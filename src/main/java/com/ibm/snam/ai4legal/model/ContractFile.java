package com.ibm.snam.ai4legal.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="AI4CM_CONTRACT_FILE")
public class ContractFile {
	
	@Id
	@Column(name = "ID_CONTRACT", nullable = false)
	private int id;
	
	@Column(name = "FILE_CONTENT")
	@Lob
	private byte[] file_content;
	
	public void setId(int id) {
		this.id = id;
	} 	
	
	public int getId() {
		return id;
	}

	public byte[] getFile_content() {
		return file_content;
	}

	public void setFile_content(byte[] file_content) {
		this.file_content = file_content;
	}

	@Override
	public String toString() {
		return "ContractFile [id=" + id + ", file_content=" + Arrays.toString(file_content) + "]";
	}
		
}
