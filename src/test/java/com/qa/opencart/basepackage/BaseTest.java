package com.qa.opencart.basepackage;

import java.util.Properties;
import com.aventstack.chaintest.plugins.ChainTestListener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonSpacePage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;
import com.qa.opencart.pages.ShoppingCartPage;
import com.qa.opencart.utility.ElementUtil;
import com.qa.opencart.utility.ExcelUtil;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected LoginPage lp;
	protected ElementUtil el;
	protected HomePage hp;
	protected SearchPage sp;
	protected ProductInfoPage pip;
	protected Properties prop;
	protected CommonSpacePage csp;
	protected ShoppingCartPage scp;
	

	//@Parameters({"browser","browserversion","testname"}) //browser value coming from xml file
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) { //parameter value  //, String browserVersion, String testname
		df = new DriverFactory();
		prop = df.initProp(); //properties coming from config.properties file
		
		if(browserName != null) { 
			prop.setProperty("browser", browserName); //set browser property from xml file
			//prop.setProperty("browserversion", browserVersion);
			//prop.setProperty("testname", testname);
		}
		driver = df.initDriver(prop);

		lp = new LoginPage(driver);
		el = new ElementUtil(driver);
		hp = new HomePage(driver);
		sp = new SearchPage(driver);
		pip = new ProductInfoPage(driver);
		csp = new CommonSpacePage(driver);
		scp = new ShoppingCartPage(driver);
		
		

	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result ) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(df.getScreenshot(), "image/png");
		}
		
	}



	@AfterTest
	public void teardown() {
		// driver.quit();

	}

}
