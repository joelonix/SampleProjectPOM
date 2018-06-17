package com.opentext.homepage.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.opentext.objects.HomePage;

public class LoginTest extends HomePage
{

	@Test
	public void logintest() throws Exception
	{
		//launchURL("appUrl");
		
		//login to the application
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("glphsClosebtn_id")));
		selUtils.click(selUtils.getObject("glphsClosebtn_xpath"));
		selUtils.click(selUtils.getObject("loginimg_xpath"));
		//selUtils.click(selUtils.getObject("logntab_xpath"));
		appLogin();
		
		Thread.sleep(2000);
		selUtils.click(selUtils.getObject("onewaytrip_id"));
		Thread.sleep(2000);
		
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject("glphsClosebtn_id"));
		
		//String xpath = getPath("lognname_xpath").replace("DYNAMIC_VALUE", config.getProperty("emailID"));
		//String actual=selUtils.getObject("afterlogname_xpath").getText().trim();
		//Assert.assertTrue(config.getProperty("emailID").contains(actual));
		selUtils.enterText(selUtils.getObject("from1_xpath"), "Dublin");
		selUtils.enterText(selUtils.getObject("destinationairport_xpath"), "sxf");
		selUtils.click(selUtils.getObject("continuebtn_xpath"));
		Thread.sleep(2000);
		
		selUtils.enterText(selUtils.getObject("flyoutdd_xpath"),"01");
		selUtils.enterText(selUtils.getObject("flyoutmm_xpath"),"07");
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject("ddhandle_xpath"));
		//selUtils.click(selUtils.getObject("ddhandle_xpath"));
		Thread.sleep(3000);
		selUtils.click(selUtils.getObject("adults_xpath"));
		selUtils.click(selUtils.getObject("children_xpath"));
		selUtils.click(selUtils.getObject("letsgobttn_xpath"));
		
		Thread.sleep(3000);
		
		selUtils.click(selUtils.getObject("pricefrombtn_xpath"));
		Thread.sleep(3000);
		
		selUtils.click(selUtils.getObject("standardfarebtn_xpath"));
		Thread.sleep(3000);
		
		selUtils.click(selUtils.getObject("contin_xpath"));
		Thread.sleep(3000);
		
		selUtils.click(selUtils.getObject("okbtn_xpath"));
		Thread.sleep(3000);
		
		selectSeat();
		
	 }
		
	
	
	public void selectSeat(){
		
		
		
		List<WebElement> rows=selUtils.getObject("seattble_xpath").findElements(By.xpath("//div[@class='seat-wrapper seat-group-ONSALE']"));
				
		outer:for(int rowcount=0;rowcount<rows.size();rowcount++){
			List<WebElement> cols=rows.get(rowcount).findElements(By.xpath("//span[@class='seat-row-seat onsale']"));
			
			for(int colcount=0;colcount<cols.size();colcount++){
				{
					cols.get(colcount).click();
					if(colcount==3) {
						break;
					}
				}
				
				
			}
			
			break outer;
		}
		
		

	}
		
	
	
	
}
