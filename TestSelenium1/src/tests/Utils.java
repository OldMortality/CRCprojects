package tests;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ac.otago.infoscience.eCRC.client.Util;

public class Utils {

	// Used to switch of preload, or Selenium tests will go haywire.
	static String NO_PRELOAD = "&preload=false";
	
	static String DEV_MOVE_URL = "http://127.0.0.1:8888/ecrc.html?gwt.codesvr=127.0.0.1:9997&name=Joe&dob=13sep1964&nhi=1234567" + NO_PRELOAD + "&erefid=";
	static String LOCAL_TOMCAT_URL = "http://localhost:8080/ecrc/ecrc.html?name=Joe&dob=13sep1964&nhi=1234567" + NO_PRELOAD + "&erefid=";
	static String OTAGO_DEV_URL = "http://ecrc.otago.ac.nz/ecrc/ecrc.html?name=Joe&dob=13sep1964&nhi=1234567" + NO_PRELOAD + "&erefid=";
	static String BPAC_TEST_URL = "https://secure.bestpractice.org.nz:8443/ecrc/ecrc.html?name=Joe&dob=13sep1964&nhi=1234567" + NO_PRELOAD + "&erefid=";
	static String BPAC_REST_URL = "https://secure.bestpractice.org.nz:8443/ecrcrest/gethistory?erefid=";
	
	
	
	static String LOCAL_TOMCAT_REST_URL = "http://localhost:8080/ecrcrest/gethistory?erefid=" ; 
	
	// These are the ones being used.
	static String ACTIVE_URL = BPAC_TEST_URL;
	static String ACTIVE_URL_REST = BPAC_REST_URL;
	
	

	public static boolean testWebservice() {
		// TODO Auto-generated method stub
		/*
		if (ACTIVE_URL.equals(LOCAL_TOMCAT_URL)) {
			return true;
		} else
			return false;
		
		*/
		return true;
	}
	
	static void testRiskCategory(WebDriver driver, String shouldBe, String erefid) {
		// ****** click Calculate risk button

		WebElement submitButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_SUBMIT));
		submitButton.click();

		// Sleep until the div we want is visible or 5 seconds is over
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			WebElement resultsDiv = driver.findElement(By.id("gwt-debug-"
					+ Util.ID_RISK_IMAGE));

			// If results have been returned, the results are displayed in a
			// drop down.
			if (resultsDiv.isDisplayed()) {

			}

		}
		String riskLevel = "-1";

		WebElement riskElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_RISK_LEVEL));
		riskLevel = riskElement.getAttribute("risklevel");

		System.out.println("Risklevel is: " + riskLevel);
		
		/*  
		 * remember to change back to shouldBe
		 * 
		 */
		assertEquals("run number: " + 1, shouldBe, riskLevel);

		
		if (tests.Utils.testWebservice() ) {
			String riskCategoryFromRest = RestClientTest.getRiskCategory(erefid);
			assertEquals("rest: ", shouldBe, riskCategoryFromRest);
		} else {
			System.out.println("Skipping testing of web service ");
		}
		
		// Press close button to preserve memory.
		WebElement closeButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_CLOSE_ALL));
		closeButton.click();
		
/*
		Set<String> set = driver.getWindowHandles();

		String base = driver.getWindowHandle();

		if (set.size() > 1) {

			driver.switchTo().window((String) set.toArray()[0]);

			driver.close();
			driver.switchTo().window(base);
		}
*/
	}
	
	static String surround(String familyHistory, String erefid, String riskCategory) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\"?>");
		sb.append("<FAMILY_HISTORY>");
	
		sb.append("<EREFID>");
		sb.append(erefid);
		sb.append("</EREFID>");
		
		sb.append("<RISK>");
		sb.append(riskCategory);
		sb.append("</RISK>");
		sb.append("<RELATIONS>");
		
		sb.append(familyHistory);
		
		sb.append("</RELATIONS>");
		sb.append("</FAMILY_HISTORY>");
		return sb.toString();
		
	}

	public static void testRest(String correctXML,
			String erefid) {
		
		String fromRest = RestClientTest.getRest(erefid);
		assertEquals(erefid, correctXML, fromRest);
		
	}

}
