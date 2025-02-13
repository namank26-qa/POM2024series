package com.qa.opencart.pages;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil el;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	// Locators
	By searchResultKeyword = By.xpath("//div[@id='content']/h1");
	By searchResults = By.cssSelector("div.product-thumb");
	By searchResultsName = By.xpath("//div[@class='product-thumb']//h4/a");
	

	// Actions
	public String getPageTitle() {
		String pageTitle = el.returnPageTitlecontains("macbook", 10);
		System.out.println("Search Page Title ===>" + pageTitle);
		return pageTitle;
	}

	public String getSearchPageURL() {
		String pageURL = el.returnPageFractionURLcontains("search&search", 10);
		System.out.println("Login Page URL ===>" + pageURL);
		return pageURL;
	}

	public String getProductSearchKeyword() {
		String searchResultkeyword = el.waitforVisbilityofElement(searchResultKeyword, 10).getText();
		return searchResultkeyword;

	}

	public int getsearchResultCount() {
		
		int count = el.waitforElementsPresence(searchResults, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("Product Search Results count: " + count);
		return count;
	}

	public List<String> getSearchResultName() {
		List<WebElement> searchResultList = el.waitforVisibilityofElements(searchResultsName, 10);
		List<String> searchResultvalue = new ArrayList<String>();
		for (WebElement e : searchResultList) {
			String text = e.getText();
			searchResultvalue.add(text);
		}
		return searchResultvalue;
	}

	public ProductInfoPage selectProduct(String productname) {
		
		el.performClick(By.linkText(productname));
		return new ProductInfoPage(driver);
	}

}
