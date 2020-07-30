package weatherComparator;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import weatherReportUtilities.InitializeDriver;
import weatherReportUtilities.Utilities;

/*This method is used compare temprator returned from API and UI and validate 
 * the difference againt threshold, if difference is more than threshold limit test will fail * 
 */
public class ComparatorTest extends InitializeDriver {
	Utilities util=new Utilities();
	@Test
	public void TC001()
	{
	String uiTemp=Utilities.comp.getProperty("Tempfrom_UI_City");
	System.out.println("Temperature from UI:"+uiTemp);
	String apiTemp=Utilities.comp.getProperty("Tempfrom_API_City");
	System.out.println("Temperature from API:"+apiTemp);
	String threshold=Utilities.testdata.getProperty("TempThreshold");
	System.out.println("Threshold:"+threshold);
	try {
	
			assertTrue(Math.abs(Double.valueOf(apiTemp)-Double.valueOf(uiTemp))<Double.valueOf(threshold));
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
