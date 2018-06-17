package com.opentext.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.opentext.commonconstants.CommonConstants;

public class TestBase 
{

	public static SelUtils selUtils;
	public static Properties config,commonOR,objR;
	public static String sProject;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions action;


	static 
	{
		objR=new Properties();
		config=new Properties();
		commonOR=new Properties();

	}

	//@BeforeSuite(alwaysRun=true)
	public  void intiliaize() throws InvalidPropertiesFormatException, FileNotFoundException, IOException
	{
		try
		{
			config.loadFromXML(new FileInputStream(CommonConstants.CONFIGFILE));
			objR.loadFromXML(new FileInputStream(CommonConstants.OBJREPOFILE));
			commonOR.loadFromXML(new FileInputStream(CommonConstants.COMMONCONFIGFILE));
			//String pageName=getUIMapPage();
			//objR.loadFromXML(new FileInputStream(pageName));
		}catch(Exception ie)
		{
			ie.printStackTrace();
			Assert.fail("XML Files not loaded as expected");

		}
	}

	/*public  String getUIMapPage()
	{
		String [] projectName=this.getClass().toString().split(" ")[1].trim().split("\\.");
		String uiMap="";
		sProject=projectName[projectName.length-3];
		if(sProject!="")
		{

			uiMap=CommonConstants.OBJECREPO+sProject+"_UIMap.xml";

		}
		else
		{
			Assert.fail("");
		}
		return uiMap;
	}*/

	@Parameters({"browser"})
	@BeforeTest(alwaysRun=true)
	public void setUp(String browser)
	{
		try{
			DesiredCapabilities capability=null;
			if("firefox".equalsIgnoreCase(browser))
			{
				FirefoxProfile profile =new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.showWhenStarting", false);
				profile.setPreference("pdfjs.disabled", true);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "");
				capability=DesiredCapabilities.firefox();
				capability.setCapability(FirefoxDriver.PROFILE, profile);
				capability.setCapability("unExpectedAlertBehaviour", "ignore");
				driver=new FirefoxDriver(capability);

			}
			if("IE".equalsIgnoreCase(browser))
			{
				System.setProperty("webdriver.ie.browser", CommonConstants.IEDRIVERPATH+"\\IEDriverServer.exe");
				capability=DesiredCapabilities.internetExplorer();
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				driver=new InternetExplorerDriver(capability);

			}
			
			if("IE".equalsIgnoreCase(browser))
			{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
			if("Chrome".equalsIgnoreCase(browser))
			{
				System.setProperty("webdriver.chrome.driver", CommonConstants.CHROMEDRIVERPATH+"\\chromedriver.exe");
				capability=DesiredCapabilities.chrome();
				//capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				driver=new ChromeDriver(capability);
				//driver=RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
				//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
				
			}
			//driver.manage()
			driver.get(config.getProperty("appUrl"));
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			wait=new WebDriverWait(driver, 20);
			setDriver(driver);
			selUtils = PageFactory.initElements(driver, SelUtils.class);
			
		}catch (Throwable t) 
		{
			t.printStackTrace();
			Assert.fail("Exception thrown while opening browser");
		}
	}
	
	/**
	 * Method which Logout's and then Closes browser
	 */
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		//logout();
		driver.quit();
	}

	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static void setDriver(WebDriver driver)
	{
		TestBase.driver=driver;
	}




// Common methods used across all modules
	public void login()
	{
	   /*selUtils.enterText(selUtils.getCommonObject("username_id"), config.getProperty("username"));
	   selUtils.enterText(selUtils.getCommonObject("username_id"), config.getProperty("password"));
	   selUtils.click(selUtils.getCommonObject("loginBtn_id"));*/
	}
	
	public void launchURL(String url)
	{
		driver.get(config.getProperty(url));
	}
	
	public String decodePswd(String paswd)
	{
		byte[] decode=Base64.decodeBase64(paswd);
		return new String(decode);
	}
	

	/**
	 * Method for returning object locator
	 * 
	 * @param objLocator
	 * @return locPath
	 */
	public static String getPath(String objLocator) {
		String locPath = "";
		try {
			locPath = objR.getProperty(objLocator);
			if ("".equals(locPath)) {
				Assert.fail("Path to locate element is null");
			}

		} catch (Throwable t) {
			Assert.fail("Cannot find locator path with key -- " + objLocator);
		}
		return locPath;

	}

	/**
	 * Returns the web element
	 * @param locator
	 * @return elt
	 */
	public WebElement getObjectDirect(final By locator) {
		int counter =0;
		WebElement elt=null;
		try
		{
			for(counter=0;counter<4;counter++)
			{
				try{
					elt = driver.findElement(locator);
					break;
				}
				catch (Exception e1){
					Thread.sleep(1000);
				}
			}
			if(counter==4){
				//Assert.assertFalse("".equals(elt));gives pmd warining,returns element as null,dosent go to catch block
				Assert.assertFalse(elt.equals(""));
			}
		}
		
		catch (Exception e)
		{					
			Assert.fail("Cannot find object with key -- "+ locator);
		}	
		
		return elt;

	}


	/**
	 * Convert web elements list to string list
	 * 
	 * @param ele
	 * @return list string options
	 */
	public List<String> getListItemsAsString(List<WebElement> ele) {
		int iter = 0;
		List<WebElement> options = ele;
		List<String> listitems = new ArrayList<String>();
		for (iter = 0; iter < options.size(); iter++) {
			listitems.add(options.get(iter).getText());

		}
		return listitems;
	}
	
	public String[] getListItems(List<WebElement> element) throws Exception
	{
		//Select dropDown = new Select(element);           
		int iter=0;
		List<WebElement> lists = element;
		String[] listitems=new String[lists.size()];
		try
		{
			for(WebElement option:lists){
				listitems[iter]=option.getText();
				iter++;
			}
		}catch (Throwable t) {
			Assert.fail("Failed during getting list Items from drop down");
		}
		return listitems;
	}


}
