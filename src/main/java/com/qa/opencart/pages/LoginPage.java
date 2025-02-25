package com.qa.opencart.pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil el;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}
	
	//Locators
	private By EmailAddress = By.id("input-email");
	private By Password = By.id("input-password");
	private By ForgotPassword = By.linkText("Forgotten Password");
	private By LoginButton = By.xpath("//input[@value='Login']");
	private By RegisterAccountContinueButton  = By.linkText("Continue");
	
	//Actions
	public String getPageTitle() {
		 String pageTitle = el.returnPageTitle(AppConstants.LOGIN_PAGE_TITLE, 10);
		 System.out.println("Login Page Title ===>" + pageTitle);
		 
		return pageTitle;
	}
	
	public String getLoginPageURL() {
		 String pageURL = el.returnPageFractionURLcontains(AppConstants.LOGIN_PAGE_FRACTION_URL, 10);
		 System.out.println("Login Page URL ===>" + pageURL);
		 return pageURL;
	}
	
	public boolean forgotPWDLinkcheck() {
		return el.isElementDisplayed(ForgotPassword);
	}
	
	public HomePage performLogin(String username, String password) {
		el.waitforVisbilityofElement(EmailAddress, 10).sendKeys(username);
		el.enterText(Password, password);
		el.performClick(LoginButton);
		
		return new HomePage(driver);
	}
	
	public RegistrationPage continuetoRegistration() {
		el.waitforVisbilityofElement(RegisterAccountContinueButton, AppConstants.DEFAULT_TIME_OUT).click();
		return new RegistrationPage(driver);
	}
	

}
