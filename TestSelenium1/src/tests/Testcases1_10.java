package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ac.otago.infoscience.eCRC.client.Util;

public class Testcases1_10 {

	static WebDriver driver = null;
	
 
	
	static String testURL = tests.Utils.ACTIVE_URL;

	@AfterClass
	public static void after() {
		driver.quit();
	}

	@BeforeClass
	public static void before() {

		System.out.println("Starting the test. before() ");

		// choose deployment of eCRC web application
		testURL = tests.Utils.ACTIVE_URL;
		System.out.println("testing deployment: + " + testURL);

		FirefoxProfile profile = new FirefoxProfile();
		
		
		
		
		if (testURL.equals(tests.Utils.DEV_MOVE_URL))
			try {
				System.out
						.println("adding GWT developer extenstion to Firefox");
				File f = new File(
						"C:/Users/michel/Downloads/gwt-dev-plugin28.xpi");
				if (f.exists()) {
					System.out.println("GWT plugin exists");
				} else {
					System.out.println("GWT plugin does not exist");
				}
				profile.addExtension(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Problem with the extension");
				e1.printStackTrace();
			}

		
		driver = new FirefoxDriver(profile);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if (driver != null) {
			System.out.println("Firefox driver created successfully");
		} else {
			System.out.println("No firefox driver. This is not going to work.");
		}
		
		
		

	}

	/**
	 * returns "gwt-debug-" + in + "-" + i
	 * 
	 * @param in
	 * @param i
	 * @return
	 */
	String name(String in, Integer i) {
		return "gwt-debug-" + in + "-" + i;
	}

	// just trying it all out. Not a testcase from the folder.

	@Test
	public void test_0() {

		System.out.println("Test case 0");

		String erefid = "testcase0";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// Find the stick person of the father (hidden button)

		WebElement element = driver.findElement(By.id("gwt-debug-Father"));

		// click on Father
		element.click();

		WebElement nameElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_NAME + "-" + 1));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_CC + "-" + 1));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_CC_AGE + "-" + 1));
		ccAgeElement.sendKeys("44");

		WebElement deElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_DE + "-" + 1));
		deElement.click();

		WebElement deAgeElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_DE_AGE + "-" + 1));
		deAgeElement.sendKeys("77");

		// enter a comment

		WebElement comment = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_COMMENTS));
		comment.sendKeys("Comment about dad");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

		// Create correct XML to test web service

		StringBuffer sb = new StringBuffer();

		// **** 1 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.FATHER + "</TYPE>");
		sb.append("<NAME>Fred</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>44</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>1</DECEASED>");
		sb.append("<AGE_DECEASED>77</AGE_DECEASED>");
		sb.append("<COMMENT>Comment about dad</COMMENT>");
		sb.append("</RELATION>");

		String correctXML = Utils.surround(sb.toString(), erefid,
				Util.CATEGORY_2_STRING);

		Utils.testRest(correctXML, erefid);

		 

		
	}

	@Test
	public void test_1() {

		System.out.println("Test case 1");

		String erefid = "testcase1";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// Find the stick person of the father (hidden button)

		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_MOTHER));

		// click on Father
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("49");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// WebElement submitButton = driver.findElement(By.id("gwt-debug-"
		// + Util.ID_BUTTON_SUBMIT));
		// submitButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

		// Create correct XML to test web service

		StringBuffer sb = new StringBuffer();

		// **** 1 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.MOTHER + "</TYPE>");
		sb.append("<NAME>Eva</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>49</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT></COMMENT>");
		sb.append("</RELATION>");

		String correctXML = Utils.surround(sb.toString(), erefid,
				Util.CATEGORY_2_STRING);

		Utils.testRest(correctXML, erefid);

	}

	@Test
	public void test_2() {

		System.out.println("Test case 2");

		String erefid = "testcase2";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// **** 1 ********

		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_MAT_GRANDMOTHER));

		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("54");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ******* 2 *******

		element = driver.findElement(By.id(Util.GWT_DEBUG_MAT_GRANDFATHER));

		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("54");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

		// Create correct XML to test web service

		StringBuffer sb = new StringBuffer();

		// **** 2 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.MAT_GRANDFATHER + "</TYPE>");
		sb.append("<NAME>Fred</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>54</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT></COMMENT>");
		sb.append("</RELATION>");
		// **** 1 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.MAT_GRANDMOTHER + "</TYPE>");
		sb.append("<NAME>Eva</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>54</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT></COMMENT>");
		sb.append("</RELATION>");

		String correctXML = Utils.surround(sb.toString(), erefid,
				Util.CATEGORY_0_STRING);

		Utils.testRest(correctXML, erefid);

	}

	@Test
	public void test_3() {

		System.out.println("Test case 3");

		String erefid = "testcase3";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// ****** 1 ***

		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("80");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 2 ***

		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));

		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("54");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_4() {

		System.out.println("Test case 4");

		String erefid = "testcase4";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// ****** Find the stick person of the father

		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("60");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** Find the stick person of the paternal grandmother (hidden
		// button)

		element = driver.findElement(By.id(Util.GWT_DEBUG_MOTHER));

		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("60");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}

	@Test
	public void test_5() {

		System.out.println("Test case 5");

		String erefid = "testcase5";
		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		// ****** Find the stick person of the maternal grandfather

		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_MAT_GRANDFATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("44");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** Find the stick person of the paternal grandmother (hidden
		// button)

		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));

		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("44");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		// ****** Find the stick person of the paternal grandmother (hidden
		// button)

		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));

		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("44");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	@Test
	public void test_6() {

		String erefid = "testcase6";
		System.out.println("Test case " + erefid);

		// testing hosted mode, adding the erefid to the parameters.
		driver.get(testURL + erefid);

		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_MOTHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("50");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}

	@Test
	public void test_7() {

		String erefid = "testcase7";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ******** 1 ****
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_MAT_AUNTIES));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Joe");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("100");

		WebElement mElement = driver.findElement(By.id(name(Util.ID_MULT, 1)));
		mElement.click();

		// enter a comment

		WebElement comment = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_COMMENTS));
		String comment1 = "Comment about Joe";
		comment.sendKeys(comment1);

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ******** 2 *****

		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_AUNTIES));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("60");

		// ******** 3 ****

		// mind the 2! 2nd person of this type!
		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 2)));
		nameElement.sendKeys("Rita");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 2)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 2)));
		ccAgeElement.sendKeys("60");
		
		
		// enter a comment

		WebElement commentElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_COMMENTS));
		String comment2 = "Comment about Fred and Rita";
		commentElement.sendKeys(comment2);


		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);
		;

	
		// Create correct XML to test web service

		StringBuffer sb = new StringBuffer();

		// **** 3 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.PAT_AUNTIES + "</TYPE>");
		sb.append("<NAME>Rita</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>60</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT>" + comment2 + "</COMMENT>");
		sb.append("</RELATION>");
		// **** 2 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.PAT_AUNTIES + "</TYPE>");
		sb.append("<NAME>Fred</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>60</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT>" + comment2 + "</COMMENT>");
		sb.append("</RELATION>");
		// **** 1 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.MAT_AUNTIES + "</TYPE>");
		sb.append("<NAME>Joe</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>100</AGE_CC>");
		sb.append("<MULTPOLS>1</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT>" + comment1 + "</COMMENT>");
		sb.append("</RELATION>");

		String correctXML = Utils.surround(sb.toString(), erefid,
				Util.CATEGORY_3_STRING);

		Utils.testRest(correctXML, erefid);

	}

	@Test
	public void test_8() {

		String erefid = "testcase8";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ********
		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("100");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ********
		element = driver.findElement(By.id(Util.GWT_DEBUG_CHILDREN));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("54");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_9() {

		String erefid = "testcase9";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ********
		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("75");
		WebElement mElement = driver.findElement(By.id(name(Util.ID_MULT, 1)));
		mElement.click();

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);
	}

	@Test
	public void test_10() {

		String erefid = "testcase10";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_MOTHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("70");
		
		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();


		// ***** 3 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

}
