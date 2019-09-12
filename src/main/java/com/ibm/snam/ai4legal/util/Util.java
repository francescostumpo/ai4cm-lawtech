package com.ibm.snam.ai4legal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class Util {
	
	public static boolean convertFromByteToFile(byte [] content, String pathWhereToSave){
		try {
			File file = new File(pathWhereToSave);
			OutputStream os = new FileOutputStream(file);
			os.write(content);
			System.out.println("File successfully save");
			os.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
