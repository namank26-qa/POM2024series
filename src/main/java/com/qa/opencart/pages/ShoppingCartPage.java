package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.CommonUtility;
import com.qa.opencart.utility.ElementUtil;

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil el;
	private CommonUtility cu;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
		cu = new CommonUtility(driver);
	}
	
	
	//Locators
	By productCount = By.xpath("//div[@class='table-responsive']/table/tbody/tr");
	//a[text()='MacBook Pro']/ancestor::tr//td//input[contains(@name,'quantity')]
	//a[text()='MacBook Pro']/ancestor::tr//td//button[@type='submit']
	//a[text()='MacBook Pro']/ancestor::tr//td//button[@data-original-title='Remove']
	
	By couponCodeHeading = By.xpath("//h4/a[contains(text(),'Coupon')]");
	By couponCodeTextBox = By.id("input-coupon");
	By applycouponCodeButton = By.id("button-coupon");
	By estimateShippingHeading = By.xpath("//h4/a[contains(text(),'Taxes')]");
	By countryDropdowwn = By.id("input-country");
	By stateDropdown = By.id("input-zone");
	By postalCodeTextbox = By.id("input-postcode");
	By getQuoteButton = By.id("button-quote");
	By chooseShippingRateRadio = By.cssSelector(".radio>label");
	By shippingButton = By.id("button-shipping");
	By giftCertificateHeading = By.xpath("//h4/a[contains(text(),'Gift')]");
	By giftCertificateCodeText = By.id("input-voucher");
	By applyCertificateButton = By.id("button-voucher");
	By checkoutButton = By.linkText("Checkout");
	
	//Action Methods
	public int countProductCountinCart() {
			return el.waitforVisibilityofElements(productCount, AppConstants.DEFAULT_TIME_OUT).size();
	}
	
	public CheckoutPage doCheckout(String couponCode, String country, String state, String postcode) {
		el.waitforElementPresence(couponCodeHeading, AppConstants.DEFAULT_TIME_OUT).click();
		el.enterText(couponCodeTextBox, couponCode);
		el.performClick(applycouponCodeButton);
		el.waitforElementPresence(estimateShippingHeading, AppConstants.DEFAULT_TIME_OUT).click();
		el.selectDropdownbyText(countryDropdowwn, country);
		el.selectDropdownbyText(stateDropdown, state);
		el.enterText(postalCodeTextbox, postcode);
		el.performClick(getQuoteButton);
		el.performClick(chooseShippingRateRadio);
		el.performClick(shippingButton);
		el.waitforElementPresence(giftCertificateHeading, AppConstants.DEFAULT_TIME_OUT).click();
		String giftcertificatecode = cu.generateRandomString(8);
		el.enterText(giftCertificateCodeText, giftcertificatecode);
		el.performClick(applyCertificateButton);
		el.performClick(checkoutButton);	
		return new CheckoutPage(driver);
	}
	
	
	
	

}
