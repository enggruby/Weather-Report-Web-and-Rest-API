package weatherReport.UI.Test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import weatherReport.UILibrary.BrowserLib;
import weatherReportUtilities.InitializeDriver;
import weatherReportUtilities.Utilities;

public class UITest_ValidateTemperature extends InitializeDriver {
	Utilities util=new Utilities();
	
	@Test
	public void ValidateTempDeatils_001() {
		String pinCity=Utilities.testdata.getProperty("ValidateTempDeatils_001.pinCity");
		String cityWeatherDt;String []temp;String finaltemp;
		try {
			//Updating the Object repository for City which is entered in Testdata.properties file
			util.updateORPropertiesFile("weather_pinCityCheckBox", "ID,"+pinCity);
			//Updating the Object repository for City which is entered in Testdata.properties file
			util.updateORPropertiesFile("weather_cityName", "XPATH,//div[@class='outerContainer' and @title='"+pinCity+"']");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BrowserLib browser=new BrowserLib();
		//Launching the Web Url
		browser.launchURL();
		//Click on Home Page ...
		browser.clickElement("homePage_ExpandableLink");
		//Click on Weather Link
		browser.clickElement("homePage_weatherLink");
		//enter city in Pin City
		browser.enterText("weather_pinCity",pinCity);
		//Select check box
		browser.isSelectedCheckBox("weather_pinCityCheckBox");
		//Click on respective city to get weather details
		browser.clickElement("weather_cityName");
		//Capturing weather details
		cityWeatherDt=browser.getText("weatherPage_weatherdtlCity"); 
		
		temp=cityWeatherDt.split(":");
		System.out.print("temp from UI"+temp[1]);
		finaltemp=temp[1];
		System.out.print("temp in degree>"+finaltemp);
		//update the temperatur in Comparator. properties file, so that it can be used for comparision
		try {
			util.updateComparatorPropertiesFile("Tempfrom_UI_City",finaltemp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}	
