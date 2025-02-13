package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.opencart.utility.ElementUtil;

public class CheckoutPage {

	private WebDriver driver;
	private ElementUtil el;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	By checkoutOptions = By.cssSelector(".radio>label");
	By continueCheckoutOptions = By.id("button-account");

	By addressUseRadio = By.cssSelector("radio");
	By firstnameText = By.id("input-payment-firstname");
	By countryDropdown = By.id("input-payment-country");
	By stateDropdown = By.id("input-payment-zone");
	By continueafterBillingDetailsbutton = By.id("button-payment-address");
	

	public void fillForm(String fname, String lname,String company, String add1, String add2, String city, String postcode) {
		Actions act = new Actions(driver);
		act.sendKeys(fname).sendKeys(Keys.TAB).sendKeys(lname).sendKeys(Keys.TAB).sendKeys(company).sendKeys(Keys.TAB).sendKeys(add1)
				.sendKeys(Keys.TAB).sendKeys(add2).sendKeys(Keys.TAB).sendKeys(city).sendKeys(Keys.TAB).sendKeys(postcode).build()
				.perform();
		
		

	}

}
