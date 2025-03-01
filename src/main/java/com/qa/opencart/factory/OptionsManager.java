package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// Declaring the private variables for Properties and BrowserOptions for
	// different browsers
	private Properties prop;
	private ChromeOptions cop;
	private FirefoxOptions fop;
	private static final Logger log = LogManager.getLogger(OptionsManager.class);
	

	public OptionsManager(Properties prop) { // passing properties in Constructor, properties to be called everytime
		this.prop = prop;
	}

	/**
	 * method return chrome browser options
	 * 
	 * @return
	 */
	public ChromeOptions getChromeOptions() {
		cop = new ChromeOptions();
		boolean remoteExecutionflag = Boolean.parseBoolean(prop.getProperty("remote"));
		
		if (Boolean.parseBoolean(prop.getProperty("headless"))) { // getting the property from config.propeeties file
			log.info("====Running in headless mode====");
			cop.addArguments("--headless");
		}
		
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("====Running in incognito browser====");
			cop.addArguments("--incognito");
		}
		System.out.println("COP return");
		
		
		if (remoteExecutionflag) {
			cop.setCapability("browserName", "chrome");
		}
		return cop;
	}

	/**
	 * method return firefox browser options
	 * 
	 * @return
	 */
	public FirefoxOptions getFirefoxOptions() {
		fop = new FirefoxOptions();
		boolean remoteExecutionflag = Boolean.parseBoolean(prop.getProperty("remote"));
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fop.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fop.addArguments("--incognito");
		}
		if (remoteExecutionflag) {
			fop.setCapability("browserName", "firefox");
		}
		return fop;
	}

}
