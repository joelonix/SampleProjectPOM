package com.opentext.homepage.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.opentext.objects.HomePage;

public class VerifyEmail extends HomePage
{

	@Test
	public void verifyEmail() throws Exception
	{
		
		launchURL("gmailUrl");
		
		vfyEmailAndSubscribe(config.getProperty("emailID"), config.getProperty("emailpswd"));
		String mainTab = driver.getWindowHandle();
		
		//move to new tab
		moveToNextTab(mainTab);
		
		String actual=selUtils.getObject("afterlogname_xpath").getText().trim();
		Assert.assertTrue(config.getProperty("emailID").contains(actual));
		
		//Login to application
		//selUtils.click(selUtils.getObject("loginimg_xpath"));
		//appLogin();
		
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(mainTab);
		
		
		
	}
	
	
	
}
