package com.opentext.objects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.opentext.base.TestBase;

public class PlanPage extends TestBase
{
	
	public String[] exploreList={"Search for flights","Search Ryanair Rooms","Fare Finder","Route map","Destinations","Our blog"};


	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws Exception
	{
		intiliaize();	
	}

	public void clickOnPlanMenu()
	{
		//selUtils.click(selUtils.getObject("plantripmenu_xpath"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", selUtils.getObject("plantripmenu_xpath"));
	}
	
	public void vExploreItems(String expected[]) throws Exception
	{
		String[] appItems1=   getListItems(selUtils.getObject("exploreitems_xpath").findElements(By.tagName("li")));
		List<String>  appList = Arrays.asList(appItems1);  
		for(int iter=0;iter<appItems1.length;iter++){
			Assert.assertTrue(appList.contains(expected[iter]),"Expected listItems are not Present");
		}
	}

}
