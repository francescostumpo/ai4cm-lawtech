package com.ibm.snam.ai4legal.common;

public class Config {

	
	/* 
	 * Configuration URL for Watson Discovery Service's microservice 
	 */
	private static String WDS_CONNECTION_URL = "https://snam-lawtech.eu-gb.mybluemix.net/"; 
	private static String WDS_UPLOAD_DOC_LOCAL = "http://127.0.0.1:8080/document/nda/upload"; 
	private static String WDS_UPLOAD_DOC = "https://snam-ai-lawtech-wds.eu-gb.mybluemix.net/document/nda/upload";
	
	/* 
	 * Getters for configurations
	 */
	public static String getWdsConnectionUrl() {
		return WDS_CONNECTION_URL; 
	} 
	
	public static String getWdsUploadDocLocal() {
		return WDS_UPLOAD_DOC_LOCAL; 
	}
	
	public static String getWdsUploadDoc(){
		return WDS_UPLOAD_DOC;
	}
	
	
}
