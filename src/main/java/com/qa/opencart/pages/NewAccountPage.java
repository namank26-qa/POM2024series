package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class NewAccountPage {

	private WebDriver driver;
	private ElementUtil el;
	public NewAccountPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}
	
	By accountConfirmationLabel = By.tagName("h1");
	By ContinueButton = By.linkText("Continue");
	
	public String getPageTitle() {
		 String pageTitle = el.returnPageTitle(AppConstants.ACCOUNT_CONFIRMATION_PAGE_TITLE, 10);
		 System.out.println("Account Creation Confirmation Page Title ===>" + pageTitle);
		return pageTitle;
	}
	
	public String getAccountConfirmationPageURL() {
		 String pageURL = el.returnPageFractionURLcontains(AppConstants.ACCOUNT_CONFIRMATION_PAGE_FRACTION_URL, 10);
		 System.out.println("Registration Page URL ===>" + pageURL);
		 return pageURL;
	}
	
	public String accountCreationSuccess() {
		return el.waitforVisbilityofElement(accountConfirmationLabel, AppConstants.DEFAULT_TIME_OUT).getText();
	}
	
	public HomePage proceedtoHomePage() {
		el.waitforVisbilityofElement(ContinueButton, AppConstants.DEFAULT_TIME_OUT).click();
		return new HomePage(driver);
	}
}
