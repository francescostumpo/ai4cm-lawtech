package com.ibm.snam.ai4legal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	
	public static String getCurrentDateAndTime() {
	    
		Calendar cal = Calendar.getInstance(); 
		
	    Date date = cal.getTime();

	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	    String formattedDate = dateFormat.format(date); 
	    
	    return formattedDate; 
	}

}
