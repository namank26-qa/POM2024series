package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exceptions.FrameworkExceptions;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager op;
	public static String highlight;
	public static ThreadLocal<WebDriver> tld = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	@Step("initating the driver using properties:{0}")
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		// System.out.println("Browser name is: " + prop.getProperty("browser"));
		log.info("Browser name is: " + prop.getProperty("browser"));
		highlight = prop.getProperty("highlight");
		op = new OptionsManager(prop);
		boolean remoteExecutionflag = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browserName.trim().toLowerCase()) {
		case "chrome": {
			if (remoteExecutionflag) {
				initRemoteDriver("chrome");
			} else {

				System.out.println("COP catch1");
				tld.set(new ChromeDriver(op.getChromeOptions()));
				System.out.println("COP catch2");
			}
			break;
		}
		case "firefox": {
			if (remoteExecutionflag) {
				initRemoteDriver("firefox");
			} else {
				tld.set(new FirefoxDriver(op.getFirefoxOptions()));
			}

			break;
		}
		case "edge": {
			driver = new EdgeDriver();
			break;
		}
		default:
			// System.out.println("Please pass the valid browser name..." + browserName);
			log.error("Please pass the valid browser name..." + browserName);
			throw new FrameworkExceptions("===Invalid Browser Name===");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(URL);
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(URL);

		return getDriver();

	}
/**
 * Method to Initiate the remote driver with Selenium grid
 * @param browserName
 */
	private void initRemoteDriver(String browserName) {
		log.info("Browser running in the grid: " + browserName);
		try {
			switch (browserName.trim().toLowerCase()) {
			case "chrome":
				tld.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getChromeOptions()));
				break;
			case "firefox":
				tld.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getFirefoxOptions()));
				break;

			default:
				log.error(browserName + "is not supported in grid.");
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}

	}

	public static WebDriver getDriver() {
		return tld.get();
	}

	/**
	 * Method to return properties from config file
	 * 
	 * @return
	 */

	// supply env name using maven command line
	// mvn clean install -Denv="qa"

	public Properties initProp() {

		prop = new Properties();
		InputStream fis = null;
		String envName = System.getProperty("env");
		System.out.println("test suite running environment: " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is passed, hence running test suite on QA env");
				fis = new FileInputStream(AppConstants.QA_CONFIG_FILE_RESOURCE_PATH);
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa": {
					fis = new FileInputStream(AppConstants.QA_CONFIG_FILE_RESOURCE_PATH);
					break;
				}
				case "prod": {
					fis = new FileInputStream(AppConstants.CONFIG_FILE_RESOURCE_PATH);
					break;
				}
				case "stage": {
					fis = new FileInputStream(AppConstants.STAGE_CONFIG_FILE_RESOURCE_PATH);
					break;
				}
				case "dev": {
					fis = new FileInputStream(AppConstants.DEV_CONFIG_FILE_RESOURCE_PATH);
					break;
				}
				case "uat": {
					fis = new FileInputStream(AppConstants.UAT_CONFIG_FILE_RESOURCE_PATH);
					break;
				}
				default:
					throw new FrameworkExceptions("====Invalid ENV ====");
				}
			}

			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return prop;
	}

	public String pathofScreenshot() {
		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + "_" + System.currentTimeMillis() + ".png";
		File destFile = new File(path);
		try {
			FileHandler.copy(srcfile, destFile);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return path;
	}

	public File getScreenshot() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	}

}
