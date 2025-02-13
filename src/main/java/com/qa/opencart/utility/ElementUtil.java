package com.qa.opencart.utility;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsu;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		this.jsu = new JavaScriptUtil(driver);
	}

	// method to locate elements
	public WebElement locateElement(By locator) {
		WebElement e = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			 jsu.flashElement(e);
		}
		
		return e; //
	}

	// value null check
	public void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new RuntimeException("===Value should not be null===");
		}
	}

	public By getLocator(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType.toUpperCase().trim()) {
		case "ID": {
			locator = By.id(locatorValue);
			break;
		}
		case "NAME": {
			locator = By.name(locatorValue);
			break;
		}
		case "CLASSNAME": {
			locator = By.className(locatorValue);
			break;
		}
		case "CSS": {
			locator = By.cssSelector(locatorValue);
			break;
		}
		case "XPATH": {
			locator = By.xpath(locatorValue);
			break;
		}
		case "LINKTEXT": {
			locator = By.linkText(locatorValue);
			break;
		}
		case "PARTIALLINKTEXT": {
			locator = By.partialLinkText(locatorValue);
			break;
		}
		case "TAGNAME": {
			locator = By.tagName(locatorValue);
			break;
		}
		default:
			System.out.println("===Invalid locator, please use the correct locator type.===");
		}

		return locator;
	}

	// method to enter text field
	public void enterText(By locator, CharSequence... text) {
		nullCheck(text);
		locateElement(locator).clear();
		locateElement(locator).sendKeys(text);
	}

	public void enterText(String locatorType, String locatorValue, CharSequence... text) {
		nullCheck(text);
		locateElement(getLocator(locatorType, locatorValue)).clear();
		locateElement(getLocator(locatorType, locatorValue)).sendKeys(text);
	}

	// method to perform click
	public void performClick(By locator) {
		
		locateElement(locator).click();
	}

	public void performClick(String locator, String locatorValue) {
		locateElement(getLocator(locator, locatorValue)).click();
	}

	// method to capture text label
	public String captureText(By locator) {
		return locateElement(locator).getText();
	}

	public String captureText(String locatorType, String locatorValue) {
		return locateElement(getLocator(locatorType, locatorValue)).getText();
	}

	// method to get the attribute
	public String captureAttribute(By locator, String attributeName) {
		nullCheck(attributeName);
		return locateElement(locator).getDomAttribute(attributeName);
	}

	// method to check element is displayed or not
	public boolean isElementDisplayed(By locator) {
		try {
			return locateElement(locator).isDisplayed();

		} catch (Exception e) {
			System.out.println("===Element is not displayed.===");
			return false;
		}

	}

	public boolean isElementDisplayed(By locator, int elementcount) {

		if (locateElements(locator).size() == elementcount) {
			System.out.println("===Element is available on the page" + elementcount + "times.===");
			return true;
		}

		else {
			System.out.println("===Element is not available on the page.===");
			return false;
		}
	}

	// method to find elements
	public List<WebElement> locateElements(By locator) {
		return driver.findElements(locator);
	}

	// Select tag drop-down utilities
	public void selectDropdownbyIndex(By locator, int index) {
		Select select = new Select(locateElement(locator));
		select.selectByIndex(index);
	}

	public void selectDropdownbyValue(By locator, String value) {
		Select select = new Select(locateElement(locator));
		select.selectByValue(value);
	}

	public void selectDropdownbyContainsText(By locator, String text) {
		Select select = new Select(locateElement(locator));
		select.selectByContainsVisibleText(text);

	}

	public void selectDropdownbyText(By locator, String text) {
		Select select = new Select(locateElement(locator));
		select.selectByVisibleText(text);
	}

	public void printDropdownText(By locator) {
		Select select = new Select(locateElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
		}
	}

	public List<String> getDropdownOptionsTextList(By locator) {
		Select select = new Select(locateElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("Number of options: " + optionsList.size());
		List<String> optionsListText = new ArrayList<String>();

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsListText.add(text);
		}
		return optionsListText;
	}

	public int getDropdownOptionscount(By locator) {
		Select select = new Select(locateElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("Number of options: " + optionsList.size());
		return optionsList.size();

	}

	public void selectvalueinDropdown(By locator, String value) {
		Select select = new Select(locateElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("Number of options: " + optionsList.size());
		boolean f = false;
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				f = true;
				break;
			}
		}
		if (f) {
			System.out.println(value + "is available and selected.");
		} else {
			System.out.println(value + "is not available.");
		}

	}

	public void performSearch(By textbox_search, By suggestionsList, String wordtosearch, String suggestionpick)
			throws InterruptedException {
		enterText(textbox_search, wordtosearch);
		boolean f = false;
		Thread.sleep(4000);

		List<WebElement> searchList = locateElements(suggestionsList);
		System.out.println(searchList.size());

		for (WebElement s : searchList) {
			String text = s.getText();
			if (text.contains(suggestionpick)) {
				s.click();
				break;
			}
		}
		if (f) {
			System.out.println(suggestionpick + "is available and clicked.");
		} else {
			System.out.println(suggestionpick + "is not available.");
		}

	}

	public void selectchoices(By dropdownselection, By selectionList, String selectionType, String... choicevalue) {
		performClick(dropdownselection);
		List<WebElement> choiceList = locateElements(selectionList);

		System.out.println(choiceList.size());

		if (selectionType.equalsIgnoreCase("All")) {
			for (WebElement e : choiceList) {
				String text = e.getText();
				System.out.println(text);
				e.click();
			}

		} else if (selectionType.equalsIgnoreCase("Single") || selectionType.equalsIgnoreCase("Multi")) {
			for (WebElement e : choiceList) {
				String text = e.getText();
				System.out.println(text);

				for (String s : choicevalue) {
					if (text.equalsIgnoreCase(s)) {
						e.click();
					}
				}
			}
		}
	}

	// Actions utilities
	public void actionSendKeys(By locator, CharSequence... value) {
		Actions act = new Actions(driver);
		act.sendKeys(locateElement(locator), value).build().perform();

	}

	public void actionPerformClick(By locator) {
		Actions act = new Actions(driver);
		act.click(locateElement(locator)).build().perform();
	}

	public void handleTwoLevelMenuSubMenuHandling(By parentlocator, By childMenulocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(locateElement(parentlocator)).build().perform();
		Thread.sleep(2000);
		locateElement(childMenulocator).click();
	}

	public void handleFourLevelMenuSubMenuHandling(By level1menu, By level2menu, By level3menu, By level4menu)
			throws InterruptedException {
		Actions act = new Actions(driver);
		act.click(locateElement(level1menu)).build().perform();
		Thread.sleep(1000);
		act.moveToElement(locateElement(level2menu)).build().perform();
		Thread.sleep(1000);
		act.moveToElement(locateElement(level3menu)).build().perform();
		Thread.sleep(1000);
		act.click(locateElement(level4menu)).build().perform();
	}

	// Enter text with pause in textbox
	public void actionSendKeyswithPause(By locator, String value, long pausetime) {
		Actions act = new Actions(driver);
		char ch[] = value.toCharArray();
		for (char c : ch) {
			act.sendKeys(locateElement(locator), String.valueOf(c)).pause(pausetime).perform();
		}
	}

	// Utilities for Waits
	public WebElement waitforElementPresence(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitforElementPresence(By locator, long time, long pollingtime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time), Duration.ofSeconds(pollingtime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitforVisbilityofElement(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitforVisbilityofElement(By locator, long time, long pollingtime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time), Duration.ofSeconds(pollingtime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void clickElementwhenReady(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public List<WebElement> waitforElementsPresence(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		} catch (TimeoutException e) {
			return Collections.emptyList();
		}

	}

	public List<WebElement> waitforVisibilityofElements(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			return Collections.emptyList();
		}
	}

	// Fluent wait feature
	public WebElement waitforVisibilityofElementsusingFluentWait(By locator, long time, long pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(pollingtime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("===Element not found.===");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// Waits for alert
	public Alert waitforAlertusingFluentWait(long time, long pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(pollingtime)).ignoring(NoAlertPresentException.class)
				.withMessage("===Alert not found===");
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public Alert waitforAlert(long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	// Method to handle alerts
	public String captureAlertText(long time) {
		return waitforAlert(time).getText();
	}

	public void acceptAlert(long time) {
		waitforAlert(time).accept();
	}

	public void dismissAlert(long time) {
		waitforAlert(time).dismiss();
	}

	public void enterTextintoAlert(long time, String text) {
		waitforAlert(time).sendKeys(text);
	}

	/**
	 * 
	 * @param title
	 * @param time
	 * @return
	 */
	public String returnPageTitle(String title, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		// title = title.toLowerCase();
		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}

		} catch (TimeoutException e) {
			System.out.println("===Page title not found after" + time + "seconds.");
		}
		return null;
	}

	public String returnPageTitlecontains(String fractiontitle, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		fractiontitle = fractiontitle.toLowerCase();
		try {
			wait.until(ExpectedConditions.titleContains(fractiontitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("===Page title not found after" + time + "seconds.");
		}
		return null;
	}

	/**
	 * 
	 * @param URL
	 * @param time
	 * @return
	 */
	public String returnPageURL(String URL, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		// URL = URL.toLowerCase();
		try {

			if (wait.until(ExpectedConditions.urlToBe(URL))) {
				return driver.getCurrentUrl();
			}

		} catch (TimeoutException e) {
			System.out.println("===Page URL not found after" + time + "seconds.");
		}
		return null;
	}

	/**
	 * 
	 * @param fractionURL
	 * @param time
	 * @return
	 */
	public String returnPageFractionURLcontains(String fractionURL, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		//fractionURL = fractionURL.toLowerCase();
		try {
			if (wait.until(ExpectedConditions.urlContains(fractionURL))) {
				return driver.getCurrentUrl();
			}

		} catch (TimeoutException e) {
			System.out.println("===Fraction of Page URL not found after" + time + "seconds.");
		}
		return null;
	}
}
