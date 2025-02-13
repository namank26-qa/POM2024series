package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.CommonUtility;
import com.qa.opencart.utility.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil el;
	private CommonUtility cl;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
		cl = new CommonUtility(driver);
	}

	/**
	 * Locators
	 */
	By productName = By.tagName("h1");
	By productImages = By.xpath("//div[@id='content']//ul[@class='thumbnails']/li");
	By productDetailsData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	By productPriceDetailsData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	By reviewLink = By.partialLinkText("Reviews");
	By reviewerName_text = By.id("input-name");
	By reviewText = By.id("input-review");
	By rating = By.xpath("//div[@class='col-sm-12']//input[@name='rating']");
	By continueButton = By.id("button-review");
	By warningBanner = By.cssSelector(".alert.alert-danger.alert-dismissible");
	By successBanner = By.cssSelector(".alert.alert-success.alert-dismissible");

	By productQuantity = By.id("input-quantity");
	By addToCart = By.id("button-cart");

	By cartButton = By.xpath("//div[@id='cart']/button");
	By viewCart = By.linkText("View Cart");
	By checkOut = By.linkText("Checkout");
	

	/**
	 * Action method
	 * 
	 * @return
	 */
	public String getProductName() {
		String name = el.captureText(productName);
		System.out.println("===Product Name: " + name + "===");
		return name;
	}

	public int countProductImages() {
		int imagecount = el.waitforElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
		String name = getProductName();
		System.out.println("Image(s) for the product is/are: " + imagecount);
		return imagecount;
	}

	public Map<String, String> getProductDetailsData() {
		Map<String, String> hmap = new HashMap<String, String>();
		List<WebElement> productDetailsDataList = el.waitforVisibilityofElements(productDetailsData,
				AppConstants.DEFAULT_TIME_OUT);
		for (WebElement e : productDetailsDataList) {
			String text = e.getText();
			System.out.println("===Product Full Data: " + text + " ===");
			String[] halfText = text.split(":");
			hmap.put(halfText[0].trim(), halfText[1].trim());
		}

		return hmap;

	}

	public Map<String, String> getProductPriceData() {
		Map<String, String> hmap = new HashMap<String, String>();
		List<WebElement> productPriceDetailsDataList = el.waitforVisibilityofElements(productPriceDetailsData,
				AppConstants.DEFAULT_TIME_OUT);
		String productPrice = productPriceDetailsDataList.get(0).getText();
		String[] estTax = productPriceDetailsDataList.get(1).getText().split(":");
		hmap.put("Product Price", productPrice);
		hmap.put(estTax[0].trim(), estTax[1].trim());
		return hmap;
	}

	public void writeReview(String reviewerName, int reviewLength) {
		el.performClick(reviewLink);
		el.waitforElementPresence(reviewerName_text, AppConstants.DEFAULT_TIME_OUT).clear();
		el.enterText(reviewerName_text, reviewerName);
		String review = cl.generateRandomString(reviewLength);
		el.waitforElementPresence(reviewText, AppConstants.DEFAULT_TIME_OUT).sendKeys(review);
	}

	public void selectRating(int value) {
		List<WebElement> we = el.waitforElementsPresence(rating, AppConstants.DEFAULT_TIME_OUT);
		for (WebElement e : we) {
			int val = Integer.parseInt(e.getDomAttribute("value"));
			if (val == value) {
				e.click();
			}
		}
	}

	public String warningReviewMessage() {
		el.performClick(continueButton);
		return el.waitforVisbilityofElement(warningBanner, AppConstants.DEFAULT_TIME_OUT).getText();
	}

	public String successReviewMessage() {
		el.performClick(continueButton);
		return el.waitforVisbilityofElement(successBanner, AppConstants.DEFAULT_TIME_OUT).getText();
	}

	public void addtoCart(String prodQty) {
		el.waitforElementPresence(productQuantity, AppConstants.DEFAULT_TIME_OUT).clear();
		el.enterText(productQuantity, prodQty);
		el.performClick(addToCart);
	}

	public String addToCartSuccess() {
		return el.waitforVisbilityofElement(successBanner, AppConstants.DEFAULT_TIME_OUT).getText();
	}

	public String productNameinSuccessBanner(String prodName) {

		return driver
				.findElement(By.xpath(
						"//div[@class='alert alert-success alert-dismissible']/a[contains(text(),'" + prodName + "')]"))
				.getText();
	}

	public ShoppingCartPage viewShoppingCart() {
		el.waitforVisbilityofElement(cartButton, AppConstants.DEFAULT_TIME_OUT).click();
		el.waitforVisbilityofElement(viewCart, AppConstants.DEFAULT_TIME_OUT).click();
		return new ShoppingCartPage(driver);
	}
	
	public CheckoutPage doCheckout() {
		el.waitforVisbilityofElement(cartButton, AppConstants.DEFAULT_TIME_OUT).click();
		el.waitforVisbilityofElement(checkOut, AppConstants.DEFAULT_TIME_OUT).click();
		return new CheckoutPage(driver);
	}

}
