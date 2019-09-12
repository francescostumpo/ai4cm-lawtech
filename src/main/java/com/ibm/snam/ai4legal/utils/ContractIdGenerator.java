package com.ibm.snam.ai4legal.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class ContractIdGenerator {

	public static String generateNewId() {
		
	    String dateNow = DateHelper.getCurrentDateAndTime(); 
		String sha256hex = Hashing.sha256().hashString(dateNow, StandardCharsets.UTF_8).toString(); 
		return sha256hex; 
	}
}
