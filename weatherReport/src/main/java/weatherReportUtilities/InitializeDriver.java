package weatherReportUtilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;



public class InitializeDriver {

	public static WebDriver driver; //WebDriver Object
	Utilities util= new Utilities();
	
	/*
	 * This method is used to create the instance of webdriver
	 */
	public void getDriver() throws Exception{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\resource\\executables\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();	
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--disable-extensions");	
		options.addArguments("--disable-infobars");	
		options.addArguments("--start-maximized");

		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Initialize Browser: Browser is initalized");

	}
/*
 * this method will be called before any class and it will load all the objects repository and test data properties 
 * 
 */
	@BeforeClass
	public void loadConfig() {
		util.initConfiguration();
	}

	/*
	 * this method will close the browser after execution
	 * 
	 */
	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("closeBrowser: Browser is closed");
	}

}
