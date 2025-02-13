package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basepackage.BaseTest;
import com.qa.opencart.constants.AppError;
import com.qa.opencart.utility.ExcelUtil;


public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void doLogin() {
//		String username = prop.getProperty("username").trim();
//		String password = prop.getProperty("password").trim();
		hp = lp.performLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchTestData() {

		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung Galaxy Tab 10.1" }, { "canon", "Canon EOS 5D" } };
	}
	
	

	@Test(dataProvider = "getSearchTestData")
	public void productInfoHeaderTest(String product, String productTypeName) {
		sp = hp.performSearch(product);
		pip = sp.selectProduct(productTypeName);
		Assert.assertEquals(pip.getProductName(), productTypeName);
	}

//	@DataProvider
//	public Object[][] getProductImageData() {
//		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "macbook", "MacBook Air", 4 }, { "imac", "iMac", 3 },
//				{ "samsung", "Samsung SyncMaster 941BW", 1 }, { "samsung", "Samsung Galaxy Tab 10.1", 7 } };
//
//	}
	
	@DataProvider
	public Object[][] getProductImageData(){
		return ExcelUtil.getTestData("Sheet1");
	}

	@Test(dataProvider = "getProductImageData")
	public void productTypeImagesCountTest(String product, String productTypeName, String imageCount) {
		sp = hp.performSearch(product);
		pip = sp.selectProduct(productTypeName);
		int actualImageCount = pip.countProductImages();
		Assert.assertEquals(actualImageCount, Integer.parseInt(imageCount));
	}
	
	
	
	@Test()
	public void productTypeInfoTest() {
		Map<String, String> ProductDetailsDatahmap = new HashMap<String, String>();
		Map<String, String> ProductPriceDetailsDatahmap = new HashMap<String, String>();
		sp = hp.performSearch("macbook");
		pip = sp.selectProduct("MacBook Pro");
		ProductDetailsDatahmap =  pip.getProductDetailsData();
		ProductDetailsDatahmap.forEach((k,v) -> System.out.println(k + ":" + v));
		ProductPriceDetailsDatahmap = pip.getProductPriceData();
		ProductPriceDetailsDatahmap.forEach((k,v) -> System.out.println(k + ":" + v));
		
		
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(pip.getProductName(), "MacBook Pro");
		
		sa.assertEquals(ProductDetailsDatahmap.get("Brand"), "Apple");
		sa.assertEquals(ProductDetailsDatahmap.get("Product Code"), "Product 18");
		sa.assertEquals(ProductDetailsDatahmap.get("Reward Points"), "800");
		sa.assertEquals(ProductDetailsDatahmap.get("Availability"), "In Stock");
		
		sa.assertEquals(ProductPriceDetailsDatahmap.get("Product Price"), "$2000.00");
		sa.assertEquals(ProductPriceDetailsDatahmap.get("Ex Tax"), "$2000.00");

		
	}
	
	
	@Test
	public void minValueinReviewTest() {
		sp = hp.performSearch("macbook");
		pip = sp.selectProduct("MacBook Pro");
		pip.writeReview("Naman", 10);
		pip.selectRating(3);
		Assert.assertTrue(pip.warningReviewMessage().contains("Warning"),AppError.INCORRECT_MESSAGE_FOUND_ERROR);
		
	}
	
	
	@Test
	public void ratingNotGivenTest() {
		sp = hp.performSearch("macbook");
		pip = sp.selectProduct("MacBook Pro");
		pip.writeReview("Naman", 10);
		Assert.assertTrue(pip.warningReviewMessage().contains("Warning"), AppError.INCORRECT_MESSAGE_FOUND_ERROR);
	}
	
	@Test
	public void productReviewSuccessTest() {
		sp = hp.performSearch("macbook");
		pip = sp.selectProduct("MacBook Pro");
		pip.writeReview("Naman", 35);
		pip.selectRating(4);
		Assert.assertTrue(pip.successReviewMessage().contains("Thank you for your review."), AppError.INCORRECT_MESSAGE_FOUND_ERROR);
	}
	

}
