package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.basepackage.BaseTest;

public class ShoppingCartPageTest extends BaseTest{

	@BeforeClass
	public void doLogin() {
		hp = lp.performLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test()
	public void countNoofProductsTest() {
		sp = hp.performSearch("macbook");
		pip = sp.selectProduct("MacBook Air");
		pip.addtoCart("3");
		scp =  pip.viewShoppingCart();
		int noofProducts = scp.countProductCountinCart();
		Assert.assertEquals(noofProducts, 1);
	}
	
	
}
