package com.opentext.objects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeSuite;

import com.opentext.base.TestBase;

public class HomePage extends TestBase
{


	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws InvalidPropertiesFormatException, FileNotFoundException, IOException
	{
		intiliaize();	
	}

	public void createAccount(String emailID, String pswd) throws Exception
	{
		selUtils.enterText(selUtils.getObject("email_xpath"), emailID);
		selUtils.enterText(selUtils.getObject("password_xpath"), decodePswd(pswd));
		selUtils.click(selUtils.getObject("accsingupbttn_xpath"));
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("succfulsignup_className")));
		//Thread.sleep(3000);
		//((JavascriptExecutor) driver).executeScript("arguments[0].click()", selUtils.getObject("accsingupbttn_xpath"));
		
	}
	
	public void clickOnSinup()
	{
		selUtils.getObject("sinupbttn_xpath").click();
	}
	
	public void vfyEmailAndSubscribe(String emailid, String pswd)
	{
		selUtils.enterText(selUtils.getObject("gmailemail_id"),emailid);
		selUtils.click(selUtils.getObject("nextbttn_id"));
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("gmailpswd_xpath")));
		selUtils.enterText(selUtils.getObject("gmailpswd_xpath"),decodePswd(pswd));
		selUtils.getObject("pswdnextbttn_id").click();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("updates_xpath")));
		selUtils.getObject("updates_xpath").click();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("myRyanairadmin_xpath")));
		selUtils.getObject("myRyanairadmin_xpath").click();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("verifyemaillink_xpath")));
		selUtils.click(selUtils.getObject("verifyemaillink_xpath"));
		
	}
	
	public void moveToNextTab(String mainTab)
	{
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(mainTab);
		    
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("loginimg_xpath")));
	}
	
	public void appLogin()
	{
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("rylogin_xpath")));
		selUtils.enterText(selUtils.getObject("rylogin_xpath"), config.getProperty("emailID"));
		selUtils.enterText(selUtils.getObject("rypswd_xpath"), decodePswd(config.getProperty("accPswd")));
		selUtils.click(selUtils.getObject("accsingupbttn_xpath"));
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject("avatarusr_xpath")));
	}
	
	public void clickOnLoginTab()
	{
		selUtils.click(selUtils.getObject("loginimg_xpath"));
	}
	
	


}
