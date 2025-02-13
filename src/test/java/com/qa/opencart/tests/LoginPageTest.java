package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.basepackage.*;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class LoginPageTest extends BaseTest {

	@Test
	public void LoginPageTitleTest() {
		ChainTestListener.log("verifying Login page title");
		String actualTitle = lp.getPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}

	@Test
	public void LoginPageURLTest() {
		String URL = lp.getLoginPageURL();
		// Assert.assertEquals(URL, "route=account/login", "===>URL Mismatch");
		Assert.assertTrue(URL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND_ERROR);
	}

	@Test
	public void ForgotPasswordLinkExistTest() {
		boolean isLinkExist = lp.forgotPWDLinkcheck();
		Assert.assertTrue(isLinkExist, "===>Forgot Password Link is missing.");

	}

	@Test(priority = Integer.MAX_VALUE)
	public void performLoginTest() {
		hp = lp.performLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(hp.getPageTitle(), AppConstants.HOME_PAGE_TITLE, "===>Home Page not navigated.");
	}

	@Test(description = "validating the logo display in login page")
	public void logoDisplayedTest() {
		Assert.assertTrue(csp.isLogoDisplayed(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@DataProvider
	public Object[][] footerLinkTestData() {
		return new Object[][] { { "About Us" }, { "Wish List" }, { "Privacy Policy" }

		};
	}

	@Test(dataProvider = "footerLinkTestData")
	public void footerLinkTest(String footerLink) {
		Assert.assertTrue(csp.getFooterLink(footerLink));
	}

}
