package com.ibm.snam.ai4legal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AI4CM_USER_PREFERENCE")
public class UserPreference {
	
	@Id
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "ID_LANGUAGE")
	private int idLanguage;
	
	@Column(name = "TEXT_FONT")
	private String textFont;
	
	@Column(name = "TEXT_SIZE")
	private String textSize;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getTextFont() {
		return textFont;
	}

	public void setTextFont(String textFont) {
		this.textFont = textFont;
	}

	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	
	@Override
	public String toString() {
		return "UserPreference [userId=" + userId + ", idLanguage=" + idLanguage + ", textFont=" + textFont
				+ ", textSize=" + textSize + "]";
	}

}
