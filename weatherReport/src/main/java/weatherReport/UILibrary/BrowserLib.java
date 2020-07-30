package weatherReport.UILibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import weatherReportUtilities.InitializeDriver;
import weatherReportUtilities.Utilities;


public class BrowserLib  extends InitializeDriver {
	public int lowwait = 3;	
	public int mediumwait = 5;
	public int highwait = 10;
		
	Utilities util=new Utilities();
	public String appUrl="";
		
/*This method is ued to launch the URL, it will take URL from Enviprnment.properties file*/
 	public void launchURL() {	
		try {
			getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appUrl=	util.config.get("url").toString();	
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(mediumwait, TimeUnit.SECONDS);	

	}	

	/*This method is used to click on any element. Parameter Key is the object of UI which need to be clicked
	 * 
	 */
	public void clickElement(String key) {	
		System.out.println("click method "+key);	
		try {
			if (driver.findElement(locateElement(key)).isDisplayed())
			{
			WebElement element = driver.findElement(locateElement(key));	
			element.click();
			Wait(lowwait);
			}
		} catch (Exception e) {	
			throw new AssertionError("Unable to click "+key+ "field. Failed with Exception: "+e);	
		}	
	}
	
	
	
	/*This method is used wait during actions*/		
	public void Wait(int timeInSec)  {	
		
			try {
				Thread.sleep((timeInSec) * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	/*This methid will locate web element based on the parameter Key
	 * Key is combination of locator and object property, based on location this method will locate web element
	 */
	public By locateElement(String key) throws IOException {	
		By element = null;	
		String line = util.OR.getProperty(key);	
		String by = line.split(",")[0].trim();	
		String value = line.split(",")[1].trim();	
		if (by.equalsIgnoreCase("NAME"))	
			element = By.name(value);	
		else if (by.equalsIgnoreCase("LINKTEXT"))	
			element = By.linkText(value);	
		else if (by.equalsIgnoreCase("XPATH"))	
			element = By.xpath(value);	
		else if (by.equalsIgnoreCase("CSS"))	
			element = By.cssSelector(value);	
		else if (by.equalsIgnoreCase("ID"))	
			element = By.id(value);	
		else if (by.equalsIgnoreCase("CLASSNAME"))	
			element = By.className(value);	
		else if (by.equalsIgnoreCase("TAGNAME"))	
			element = By.tagName(value);	
		return element;	
	}

	
	
	/*This method is used to enter text in any input text
	 * 
	 */
	public void enterText(String key, String input) {	
		System.out.println("enter method "+key);	
		try {	
			WebElement element = driver.findElement(locateElement(key));	
			element.clear();	
			element.sendKeys(input);	
		} catch (Exception e) {	
			throw new AssertionError("Unable to enter text at "+key+ "field. Failed with Exception: "+e);	
		}	
	}
	
	/*This method is used to get text of a selected element
	 * 
	 */
	public String getText(String key) {	
		System.out.println("enter method "+key);
		String elementText="";
		try {	
			WebElement element = driver.findElement(locateElement(key));	
			 elementText=element.getText();	
				
		} catch (Exception e) {	
			throw new AssertionError("Unable to find "+key+ "field. Failed with Exception: "+e);	
		}
		return elementText;
	}	
	/*This method is ued to check if a check box is selected or not, if not selected then it will check the box
	 * 
	 */
	public void isSelectedCheckBox(String key)  {
		try {
		WebElement e = driver.findElement(locateElement(key));	
		if (!e.isSelected()) {	
			e.click();	
		
		}
		Wait(lowwait);
		} catch (Exception e) {	
			throw new AssertionError("Unable to getvalue of "+key+ "field. Failed with Exception: "+e);	
		}
	}
	

}
