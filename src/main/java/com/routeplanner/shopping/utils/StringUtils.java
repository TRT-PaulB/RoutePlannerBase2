package com.routeplanner.shopping.utils;

public class StringUtils {

	public static boolean isBlank(String val) {
		return val == null || val.isBlank() ? true : false;
	}
	
	public static boolean isNotBlank(String val) {
		return !isBlank(val);
	}
}
