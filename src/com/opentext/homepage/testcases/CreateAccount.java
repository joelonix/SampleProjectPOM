package com.opentext.homepage.testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.opentext.objects.HomePage;

public class CreateAccount extends HomePage
{

	@Test
	public void accountSinup() throws Exception
	{
		launchURL("appUrl");
		wait.until(ExpectedConditions.elementToBeClickable(selUtils.getObject("sinupbttn_xpath")));
		clickOnSinup();
		createAccount(config.getProperty("accEmail"), config.getProperty("accPswd"));
		
	}
	
	
	
}
