package com.opentext.base;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.opentext.base.WaitMethods;

public class SelUtils 
{
	
	public WebDriver driver;
	public WaitMethods waitMethods = new WaitMethods();
	private Properties objR = TestBase.objR, commonOR = TestBase.commonOR;

	public SelUtils()
	{
		this.driver=TestBase.getDriver();
	}
	
	
	/**
	 * Selenium click method
	 * @param element
	 */
	public void click(WebElement element)
	{
		element.click();
	}
	
	/**
	 * Select any of the option from the list
	 * @param element
	 * @param value	
	 */
	public void selectItem(WebElement element,String value)
	{
		//waitMethods.waitForWebElementPresent(element);
		Select listbox=new Select(element);
		listbox.selectByVisibleText(value);		
	}
	
	/**
	 * Selenium sendkeys method
	 * @param element
	 * @param textValue
	 */
	public void enterText(WebElement element, String textValue)
	{
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	public WebElement getObject(String object)
	{
		WebElement elt=null;
		String locators;
		locators=object.substring(object.lastIndexOf('_')+1);
		int counter=0;

		try{
			forloop: for(counter=0;counter<4;counter++)
			{

				try{
					switch (locators) {
					case "id":
						elt=driver.findElement(By.id(objR.getProperty(object)));
						break forloop;

					case "Name":
						elt=driver.findElement(By.name(objR.getProperty(object)));
						break forloop;

					case "CSS":
						elt=driver.findElement(By.cssSelector(objR.getProperty(object)));
						break forloop;

					case "xpath":
						elt=driver.findElement(By.xpath(objR.getProperty(object)));
						break forloop;

					case "link":
						elt=driver.findElement(By.linkText(objR.getProperty(object)));
						break forloop;
						
					case "className":
						elt=driver.findElement(By.className(objR.getProperty(object)));
						break forloop;

					default:
						Assert.fail("Object repo format is not proper");
						break;
					}
				}catch (NoSuchElementException e) 
				{

				}
			}

		if(counter==4)
		{
			Assert.assertFalse("".equals(elt));
		}

		}catch (NoSuchElementException e) 
		{
			Assert.fail("Cannot find object with key--"+object);
		}

		return elt;

	}
	
	public WebElement getCommonObject(String object)
	{
		WebElement elt=null;
		String locators;
		locators=object.substring(object.lastIndexOf('_')+1);
		int counter=0;

		try{
			forloop: for(counter=0;counter<4;counter++)
			{

				try{
					switch (locators) {
					case "id":
						elt=driver.findElement(By.id(commonOR.getProperty(object)));
						break forloop;

					case "Name":
						elt=driver.findElement(By.name(commonOR.getProperty(object)));
						break forloop;

					case "CSS":
						elt=driver.findElement(By.cssSelector(commonOR.getProperty(object)));
						break forloop;

					case "xpath":
						elt=driver.findElement(By.xpath(commonOR.getProperty(object)));
						break forloop;

					case "link":
						elt=driver.findElement(By.linkText(commonOR.getProperty(object)));
						break forloop;

					default:
						Assert.fail("Object repo format is not proper");
						break;
					}
				}catch (NoSuchElementException e) 
				{

				}
			}

		if(counter==4)
		{
			Assert.assertFalse("".equals(elt));
		}

		}catch (NoSuchElementException e) 
		{
			Assert.fail("Cannot find object with key--"+object);
		}

		return elt;

	}
	
	
	/**
	 * gets list of options as String items
	 * @param object
	 * @return drop down options
	 */
	public String[] getSelListItems(WebElement element)
	{
		Select dropDown = new Select(element);           
		int iter=0;
		List<WebElement> options = dropDown.getOptions();
		String[] listitems=new String[options.size()];
		try
		{
			for(WebElement option:options){
				listitems[iter]=option.getText();
				iter++;
			}
		}catch (Throwable t) {
			Assert.fail("Failed during getting list Items from drop down");
		}
		return listitems;
	}
	
	/**
	 * validate multi list values
	 * @param listelement
	 * @param expItems
	 */
	public void vMultiListOptions(WebElement listelement,String[] expItems )
	{
		String[] appItems=   getSelListItems(listelement);
		List<String>  appList = Arrays.asList(appItems);  
		for(int iter=0;iter<expItems.length;iter++){
			Assert.assertTrue(appList.contains(expItems[iter]),"Expected listItem "+expItems[iter]+" is not Present");
		}
	}
}
