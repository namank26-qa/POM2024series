package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	
	//Declaring the private variables for Properties and BrowserOptions for different browsers
	private Properties prop;
	private ChromeOptions cop;
	private FirefoxOptions fop;
	
	public OptionsManager(Properties prop) { //passing properties in Constructor, properties to be called everytime
		this.prop = prop;
		
	}
	
	/**
	 * method return chrome browser options
	 * @return
	 */
	public ChromeOptions getChromeOptions() {
		cop = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) { //getting the property from config.propeeties file
			cop.addArguments("--headless"); 
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			cop.addArguments("--incognito");
		}
		return cop;
	}
	
	/**
	 * method return firefox browser options
	 * @return
	 */
	public FirefoxOptions getFirefoxOptions() {
		fop = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fop.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fop.addArguments("--incognito");
		}
		return fop;
	}
	
}
