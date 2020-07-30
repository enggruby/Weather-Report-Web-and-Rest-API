package weatherApiTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import weatherAPI.APILib;
import weatherReportUtilities.InitializeDriver;
import weatherReportUtilities.Utilities;

public class APITTest_ValidateWeatherResCityWise extends InitializeDriver  {

	Utilities util=new Utilities();
	@Test
	public void validateweatherAPI()
	{
		APILib api=new APILib();
		//fetching the city name from Testdata.properties file
		String cityName=Utilities.testdata.getProperty("ValidateTempDeatils_001.pinCity");
		//Getting the response from the API for cityname
		Response response= api.getweatherReqwithCity(cityName);
		//api.getweatherReqwithCity(cityName);
		 System.out.println("response>>"+response.asString());
			//String main = response.jsonPath().getString("main");
			JsonPath jsonPathEvaluator = response.jsonPath();
			//Fetching the main body from response
			 HashMap<String, Float>mainBody = jsonPathEvaluator.get("main");	
			 //getting temp from main body
			 Float temp=mainBody.get("temp");
			 System.out.print("temp>>"+temp);
			 //converting the temperature into Celsius
			String convertedTemp= util.tempConvertFahrenheittoCelsius("Fahrenheit","Celsius",temp);
			 System.out.println("temp after conversion from API>>"+ convertedTemp);
			//update the temperatur in Comparator. properties file, so that it can be used for comparision
			 try {
					util.updateComparatorPropertiesFile("Tempfrom_API_City", convertedTemp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	}
}
