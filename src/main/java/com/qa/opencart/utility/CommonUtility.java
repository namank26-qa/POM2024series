package com.qa.opencart.utility;

import java.security.SecureRandom;

import org.openqa.selenium.WebDriver;

public class CommonUtility {

	private WebDriver driver;

	public CommonUtility(WebDriver driver) {
		this.driver = driver;
	}

	public String generateRandomString(int reqdLength) {

		String alphanumericCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String reqdString = "";
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < reqdLength; i++) {
			int index = random.nextInt(alphanumericCharacters.length());
			reqdString = reqdString + alphanumericCharacters.charAt(index);
		}
		return reqdString;

	}

}
