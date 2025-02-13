package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class CommonSpacePage {
	private WebDriver driver;
	private ElementUtil el;
	
	public CommonSpacePage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}
	
	By logoImage = By.className("img-responsive");
	By footerElements = By.xpath("//footer//a");
	
	
	public boolean isLogoDisplayed() {
		return el.isElementDisplayed(logoImage);
	}
	
	public List<String> getFooterList() {
		List<WebElement> footersElementList = el.waitforVisibilityofElements(footerElements, AppConstants.DEFAULT_TIME_OUT);
		List<String> footerList = new ArrayList<String>();
		for(WebElement w: footersElementList ) {
			String s = w.getText();
			footerList.add(s);
		}
		return footerList;
	}
	
	public boolean getFooterLink(String footerLink) {
		return getFooterList().contains(footerLink);
	}

}
