package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.chaintest.domain.Log;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;
import com.qa.opencart.utility.JavaScriptUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil el;
	
	// private JavaScriptUtil jsutil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
		// jsutil = new JavaScriptUtil(driver);
	}

	// Locators
	By FirstNameText = By.id("input-firstname");
	By NewsletterRadio = By.cssSelector(".radio-inline");
	By PrivacyPolicyCheckbox = By.name("agree");
	By ContinueButton = By.xpath("//div[@id='content']//input[@value='Continue']");
	
	public String getPageTitle() {
		 String pageTitle = el.returnPageTitle(AppConstants.REGISTRATION_PAGE_TITLE, 10);
		 System.out.println("Registration Page Title ===>" + pageTitle);
		return pageTitle;
	}
	
	public String getRegistrationPageURL() {
		 String pageURL = el.returnPageFractionURLcontains(AppConstants.REGISTRATION_PAGE_FRACTION_URL, 10);
		 System.out.println("Registration Page URL ===>" + pageURL);
		 return pageURL;
	}

	public NewAccountPage fillRegistrationForm(String firstName, String lastName, String email, String telephone,
			String password) {
		Actions act = new Actions(driver);
		act.sendKeys(firstName).sendKeys(Keys.TAB).sendKeys(lastName).sendKeys(Keys.TAB).sendKeys(email)
				.sendKeys(Keys.TAB).sendKeys(telephone).sendKeys(Keys.TAB).sendKeys(password).sendKeys(Keys.TAB)
				.sendKeys(password).build().perform();
		el.performClick(NewsletterRadio);
		el.performClick(PrivacyPolicyCheckbox);
		el.performClick(ContinueButton);
		return new NewAccountPage(driver);
	}

}
