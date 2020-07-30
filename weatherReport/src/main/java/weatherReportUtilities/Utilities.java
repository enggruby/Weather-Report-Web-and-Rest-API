package weatherReportUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Scanner;

public class Utilities {
	public static Properties testdata=new Properties();
	public static FileInputStream testdataFile;
	public static Properties OR = new Properties();	
	public static Properties config = new Properties();	
	public static FileInputStream configfis;	
	public static FileInputStream ORfis;	
	public static FileInputStream RFORfis;	
	public static Properties comp=new Properties();
	public static FileInputStream comparatorFile;
	
/*
 * this method will load all the value from objects repository, enviornment and test data properties 
 * 
 */		

public void initConfiguration() {	
		
		try {	
			configfis = new FileInputStream(	
					System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\Environment.properties");	
		} catch (FileNotFoundException e) {	
			e.printStackTrace();	
		}	
		try {	
			config.load(configfis);	
			
		} catch (IOException e1) {	
			e1.printStackTrace();	
		}	
		try {	
			ORfis = new FileInputStream(	
					System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\ObjectRepo.properties");	
		} catch (FileNotFoundException e) {	
			e.printStackTrace();	
		}	
		try {	
			OR.load(ORfis);	
		
		} catch (IOException e) {	
			e.printStackTrace();	
		}	
		
		try {	
			testdataFile = new FileInputStream(	
					System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\Testdata.properties");	
		} catch (FileNotFoundException e) {	
			e.printStackTrace();	
		}	
		try {	
			testdata.load(testdataFile);
			
			System.out.println("Testdata file loaded");
		
		} catch (IOException e) {	
			e.printStackTrace();	
		}
		try {	
			comparatorFile = new FileInputStream(	
					System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\Comparator.properties");	
		} catch (FileNotFoundException e) {	
			e.printStackTrace();	
		}	
		try {	
			comp.load(comparatorFile);
			
			System.out.println("Comparator file loaded");
		
		} catch (IOException e) {	
			e.printStackTrace();	
		}
	}	

/*
 * this method will convert temperature from Celsius to Fahrenheit and from Fahrenheit to Celsius
 * 
 */	
public String tempConvertFahrenheittoCelsius (String currentFormat,String newFormat, float temp)
{
   
	double convertedTemp = 0;
	DecimalFormat dformat = new DecimalFormat("0.00"); //Make new decimal format

      
	try {
	        if(currentFormat.equalsIgnoreCase("Celsius") && newFormat.equalsIgnoreCase("Fahrenheit"))
	        {
	        System.out.print("Enter temperature in Fahrenheit:");
	        convertedTemp =((temp*9)/5)+32;  
	        System.out.println("Temperature in Fahrenheit:"+convertedTemp);   
	        }
	        else  if(currentFormat.equalsIgnoreCase("Fahrenheit") && newFormat.equalsIgnoreCase("Celsius"))
	        {
	        	 System.out.print("Enter temperature in Celsius:");
	            // fahrenheit = s.nextDouble();
	        	 convertedTemp = (temp-32)*(0.5556);
	             System.out.println("Temperature in Fahrenheit:"+convertedTemp);  
	        }
	   }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return dformat.format(convertedTemp);
    }

/*
 * this method is used to update the value of a specific property in Object Reporities properties file
 * This method need two parameter, one is property which need to be updaed and other is new value
 */	
public void updateORPropertiesFile(String property, String newvalue) throws IOException
{
	FileInputStream in;
	try {
		in = new FileInputStream(System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\ObjectRepo.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out;
		out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\ObjectRepo.properties");
		props.setProperty(property, newvalue);
		props.store(out, null);
		out.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

/*
* this method is used to update the value of  temperature city wise from API and UI
* Comparator.properties will store temperature from API and UI, this data will be used for comparasion
*/	
public void updateComparatorPropertiesFile(String property, String newvalue) throws IOException
{
	FileInputStream in;
	try {
		in = new FileInputStream(System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\Comparator.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out;
		out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\resource\\propertiesFiles\\Comparator.properties");
		props.setProperty(property, newvalue);
		props.store(out, null);
		out.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}