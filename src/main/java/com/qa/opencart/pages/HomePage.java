package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utility.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil el;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	// Home Page Locators
	By searchTextbox = By.name("search");
	By searchButton = By.xpath("//div[@id='search']//child::button");
	By logoutLink = By.linkText("Logout");
	By headers = By.xpath("//div[@id='content']/h2");

	// Action methods

	public String getPageTitle() {
		String pageTitle = el.returnPageTitle("My Account", 10);
		System.out.println("Login Page Title ===>" + pageTitle);
		return pageTitle;
	}

	public String getHomePageURL() {
		String pageURL = el.returnPageFractionURLcontains("route=account/account", 10);
		System.out.println("Login Page URL ===>" + pageURL);
		return pageURL;
	}

	public boolean isLogoutLinkpresent() {
		return el.isElementDisplayed(logoutLink);
	}

	public void performLogout() {
		if (isLogoutLinkpresent()) {
			el.performClick(logoutLink);
		}
	}

	/**
	 * Method to print the number of headers and values of headers present in Home Page.
	 * @return
	 */
	public List<String> getHeaders() {
		List<WebElement> headersList = el.waitforVisibilityofElements(headers, 10);
		List<String> headersValues = new ArrayList<String>();
		System.out.println("===Number of headers displayed in Home Page:" + headersList.size() + "===");
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValues.add(text);
		}
		return headersValues;
	}

	/**
	 * 
	 * @param searchKey
	 * @return
	 */
	public SearchPage performSearch(String searchKey) {
		WebElement w = el.waitforVisbilityofElement(searchTextbox, 10);
		el.enterText(searchTextbox,searchKey);
		el.performClick(searchButton);
		return new SearchPage(driver);

	}

}
