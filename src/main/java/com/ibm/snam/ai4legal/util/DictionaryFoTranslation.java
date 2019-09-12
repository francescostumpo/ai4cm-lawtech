package com.ibm.snam.ai4legal.util;

import java.util.HashMap;
import java.util.Map;

public class DictionaryFoTranslation {
	
	private Map<String, String> english_dictionary;
	private Map<String, String> italian_dictionary;

	private static DictionaryFoTranslation instance;
	
	public DictionaryFoTranslation(){
		english_dictionary = new HashMap<>();
		english_dictionary.put(Constants.TERMINATION, "Termination");
		english_dictionary.put(Constants.DURATION, "Duration");
		english_dictionary.put(Constants.EFFECTIVE_DATE, "Effetctive Date");
		english_dictionary.put(Constants.DEADLINE, "Deadline");
		english_dictionary.put(Constants.CON_ADDRES, "Address");
		english_dictionary.put(Constants.CON_EMAIL, "Email");
		english_dictionary.put(Constants.CON_PERSON, "Name");
		english_dictionary.put(Constants.CON_PHONE, "Phone");
		english_dictionary.put(Constants.CON_FAX, "Fax");
		
		italian_dictionary = new HashMap<>();
		italian_dictionary.put(Constants.TERMINATION, "Terminazione");
		italian_dictionary.put(Constants.DURATION, "Durata");
		italian_dictionary.put(Constants.EFFECTIVE_DATE, "Data entrata in vigore");
		italian_dictionary.put(Constants.DEADLINE, "Scadenza");
		italian_dictionary.put(Constants.CON_ADDRES, "Indirizzo");
		italian_dictionary.put(Constants.CON_EMAIL, "Email");
		italian_dictionary.put(Constants.CON_PERSON, "Nome");
		italian_dictionary.put(Constants.CON_PHONE, "Telefono");
		italian_dictionary.put(Constants.CON_FAX, "Fax");
	}
	
	public static DictionaryFoTranslation getDictionaryForTranslation(){
		if(instance == null) {
			instance = new DictionaryFoTranslation();
		}
		return instance;
	}
	
	public String getTransalaction(String key, String language){
		Map<String, String> dictionary = null;
		if(language.equals("it")){
			dictionary = italian_dictionary;
		}
		else{
			dictionary = english_dictionary;
		}
		if(dictionary.containsKey(key)){
			return dictionary.get(key);
		}
		else{
			return null;
		}
	}
	
}
