package com.qa.opencart.constants;

public class AppConstants {

	private AppConstants() {
		
	}
	
	public final static int DEFAULT_TIME_OUT = 5;
	public final static int SHORT_TIME_OUT = 10;
	public final static int MEDIUM_TIME_OUT = 15;
	public final static int MAX_TIME_OUT = 20;
	
	public static String LOGIN_PAGE_TITLE = "Account Login";
	public static String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	
	public static String REGISTRATION_PAGE_TITLE = "Register Account";
	public static String REGISTRATION_PAGE_FRACTION_URL = "route=account/register";
	
	public static String ACCOUNT_CONFIRMATION_PAGE_TITLE = "Your Account Has Been Created!";
	public static String ACCOUNT_CONFIRMATION_PAGE_FRACTION_URL = "route=account/success";
	
	public static String HOME_PAGE_TITLE = "My Account";
	public static String HOME_PAGE_FRACTION_URL = "route=account/account";
	
	public static String CONFIG_FILE_RESOURCE_PATH = "./src/test/resources/config/config.properties";
	
	public static String QA_CONFIG_FILE_RESOURCE_PATH = "./src/test/resources/config/qa.config.properties";
	public static String STAGE_CONFIG_FILE_RESOURCE_PATH = "./src/test/resources/config/stage.config.properties";
	public static String UAT_CONFIG_FILE_RESOURCE_PATH = "./src/test/resources/config/uat.config.properties";
	public static String DEV_CONFIG_FILE_RESOURCE_PATH = "./src/test/resources/config/dev.config.properties";
	
	
}
