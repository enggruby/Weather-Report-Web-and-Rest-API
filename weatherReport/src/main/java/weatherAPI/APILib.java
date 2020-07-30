package weatherAPI;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import weatherReportUtilities.InitializeDriver;
import weatherReportUtilities.Utilities;

public class APILib extends InitializeDriver {

	/*This method is used to return the response of webservice 
	 * This method will return weather details of a given city(city which is mentionedd in Testdata.propertes
	 * It will pick application URL and Authentication key from Environment.properties file
	 * This method will return Response object 
	 */
	
	public  Response getweatherReqwithCity(String cityname) 
	{
		String apiURL=Utilities.config.getProperty("apiUrl");
		String authKey=Utilities.config.getProperty("apiKey").trim();
		System.out.println("appURL::"+apiURL);
		System.out.println("authKey::"+authKey);
		//RestAssured.baseURI ="https://samples.openweathermap.org/data/2.5/";
		RestAssured.baseURI=apiURL;
		RequestSpecification request = RestAssured.given(); 
		
		 Response response = request.queryParam("q", cityname) 
               .queryParam("appid", authKey) 
               .get("/weather");
		
		 if (response.statusCode()==200)
			{
				System.out.println("Response is Successfull");
			
		}
		 else {response=null;}
			
			return response;
		
	}
}
		 
	
	
