package com.ibm.snam.ai4legal.common;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	// General string costants
	public static final String
		FOLDER_PDF_TEXT = "C:/$user/myApps/AI4CM/PDF",
		FOLDER_PDF_IMAGE = "C:/$user/myApps/AI4CM/PDFIMAGE",
		FOLDER_TEXT = "C:/$user/myApps/AI4CM/TEXT",
		FOLDER_HTML = "C:/$user/myApps/AI4CM/HTML",
		FOLDER_SUBMITTED = "C:/$user/myApps/AI4CM/SUBMITTED",
		LOG4J_FILE = "./log4j.xml",
		CONFIGURATION_FILE = "./application.properties",
		FAKE_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1",
		TEMP_DIRECTORY = "./temp",
		CONNECTOR_CONTRACTS = "CM",
		REPLY_YES = "Y",
		REPLY_NO = "N",
		DOMAIN_YES = "Y",
		DOMAIN_NO = "N",
		HTML_FILE_EXT = ".html",
		ALPHANUMERIC_PATTERN = "^[A-Za-z0-9]+$",
		ALPHANUMERIC_UPPERCASE_PATTERN = "^[A-Z0-9]+$",
		ALPHANUMERIC_GENERIC_PATTERN = "^[A-Za-z0-9.%'\\- ]+$",
		ALPHANUMERIC_SEARCH_PATTERN = "^[A-Za-z0-9.,%'\\- ]+$",
		PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16})",
		MODEL_ENRICHED_DEFAULT = "enriched_default",
		MODEL_ENRICHED_CONTRACTS = "enriched_contracts",
		DEFAULT_FORMAT_ORA = "HH:mm",
		DEFAULT_FORMAT_BUSINESS_UNIT = "dd-MM-yyyy HH:mm:ss",
		DEFAULT_FORMAT_DATE_WDN = "yyyy-MM-dd",
		DEFAULT_FORMAT_DATE = "yyyyMMdd",
		DEFAULT_FORMAT_DATE_LABEL = "MMMyy",
		DEFAULT_FORMAT_DATE_FOR_FILES = "yyyyMMdd_HHmmss_SSS",
		DEFAULT_FORMAT_DATE_HOUR_WITH_SLASH = "dd/MM/yyyy HH:mm",
		DEFAULT_FORMAT_DATE_WITH_SLASH = "dd/MM/yyyy",
		DEFAULT_FORMAT_DATE_WORLD_CHECK = "yyyy/MM/dd",
		USER_SYSTEM = "SYSTEM"; 
	    
	// The maximum size of file allowed in Watson Discovery 
	public static final long MAX_ALLOWED_FILE_SIZE = 1048576; 

	// Numeric constants
	public static final int
		WDS_RETURN_COUNT = 50,
		WDS_MAX_LENGTH = 800,
		MAX_PAGES_TO_SEARCH = 1000;

	// JPA queries and parameters
	public static final String
		QUERY_GET_LANGUAGES = "languages.getAll",
		QUERY_GET_MESSAGES = "messages.getAll",
		PARAMETER_ID_LANGUAGE = "idLanguage";
	
	// Blacklist
	public static final 
		List<String> BLACKLIST = Arrays.asList(new String[] {"Yah�","VChW�","Veh�","Veh","Vahw�","Vahw","VGhw","Vay�","Vb�"});

}
