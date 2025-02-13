package com.qa.opencart.constants;

public abstract class AppError {
	
	private AppError() {
		
	}
	
	public static String TITLE_NOT_FOUND_ERROR = "*====Page Title not matching====*";
	public static String URL_NOT_FOUND_ERROR = "*====Page URL not matching====*";
	public static String ELEMENT_NOT_FOUND_ERROR = "*====Element not found====*";
	
	public static String INCORRECT_MESSAGE_FOUND_ERROR = "*====Incorrect mesage displayed.====*";

}
