package com.keebraa.jbirth.helpers;

import java.util.UUID;

public class StringHelper {
	
	public static String createNewId(String prefix) {
		return prefix+"-"+UUID.randomUUID().toString().replace("-", "");
	}
	
	public static boolean isEmpty(String id) {
	    if(id == null)
	        return true;
	    return id.trim().equals("");
	}
}
