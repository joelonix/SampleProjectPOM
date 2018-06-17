package com.opentext.planpage.testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.opentext.objects.ObjectFactory;
import com.opentext.objects.PlanPage;

public class VerifyExploreItems extends PlanPage
{

	@Test
	public void verifyexplitem() throws Exception
	{
		launchURL("appUrl");
		wait.until(ExpectedConditions.elementToBeClickable(selUtils.getCommonObject("sinupbttn_xpath")));
		
		ObjectFactory.getHomePageInstance().clickOnLoginTab();
		ObjectFactory.getHomePageInstance().appLogin();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("plantripmenu_xpath")));
		clickOnPlanMenu();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("exploreitems_xpath")));
		vExploreItems(exploreList);
		
		
	}
	
	
	
	
}
