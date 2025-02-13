package com.qa.opencart.tests;

import java.util.*;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.basepackage.*;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void doLogininHomePage() {
		hp = lp.performLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void HomePageTitleTest() {
		String homePageTitle = hp.getPageTitle();
		Assert.assertEquals(homePageTitle, "My Account", "===>Home Page Title Mismatch");
	}

	@Test
	public void HomePageURLTest() {
		String homePageURL = hp.getHomePageURL();
		Assert.assertTrue(homePageURL.contains("route=account/account"), "===>Home Page URL Mismatch");
	}

	@Test
	public void LogoutLinkExistTest() {
		Assert.assertTrue(hp.isLogoutLinkpresent(), "===>Logout Link is not present.");
	}

	@Test
	public void HeadersinHomePageTest() {
		List<String> headersinHomePage = hp.getHeaders();
		System.out.println("===>Headers in Home Page:" + headersinHomePage);
//		for (String s : headersinHomePage) {
//			Assert.assertTrue(s.contains("My Orders"), "===>" + s + "header value not found");
//			break;
//		}

	}

	@DataProvider
	public Object[][] getSearchTestData() {

		return new Object[][] { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 }, { "canon", 1 }, { "airtel", 0 } };
	}

	@Test(dataProvider = "getSearchTestData")
	public void ProductSearchinHomePageTest(String searchkey, int resultcount) {
		sp = hp.performSearch(searchkey);
		Assert.assertEquals(sp.getsearchResultCount(), resultcount);
	}

}
