package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {  //check if test result is failed
			if (count < maxTry) { 
				count++;
				result.setStatus(result.FAILURE); //if 
				return true;
			} else {
				result.setStatus(result.FAILURE);
			}

		} else {
			result.setStatus(result.SUCCESS);
		}
		return false;
	}

}
