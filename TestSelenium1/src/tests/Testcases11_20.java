package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ac.otago.infoscience.eCRC.client.Util;

public class Testcases11_20 {

	static WebDriver driver = null;
	static String testURL = tests.Utils.ACTIVE_URL;

	@AfterClass
	public static void after() {
		driver.quit();
	}

	@BeforeClass
	public static void before() {

		System.out.println("Starting the test. before() ");

		System.out.println("testing deployment + " + testURL);

		FirefoxProfile profile = new FirefoxProfile();
		if (testURL.equals(tests.Utils.DEV_MOVE_URL))
			try {
				System.out
						.println("adding GWT developer extenstion to Firefox");
				File f = new File(
						"C:/Users/michel/Downloads/gwt-dev-plugin.xpi");
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
	public void test_11() {

		String erefid = "testcase11";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
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
		ccAgeElement.sendKeys("80");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("60");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_12() {

		String erefid = "testcase12";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
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
		ccAgeElement.sendKeys("65");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_MAT_GRANDFATHER));
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
		element = driver.findElement(By.id(Util.GWT_DEBUG_MOTHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_13() {

		String erefid = "testcase13";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("100");

		WebElement otElement = driver.findElement(By.id(name(Util.ID_OT, 1)));
		otElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE,
				1)));
		otAgeElement.sendKeys("100");

		// comment
		WebElement coElement = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_COMMENTS));
		String comment1 = "Comment about Eva";
		coElement.sendKeys(comment1);

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("55");

		WebElement coElement2 = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_COMMENTS));
		String comment2 = "Comment about Fred";
		coElement2.sendKeys(comment2);

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

		// Create correct XML to test web service

		StringBuffer sb = new StringBuffer();

		// **** 1 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.PAT_GRANDMOTHER + "</TYPE>");
		sb.append("<NAME>Eva</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>100</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>1</HNPCC>");
		sb.append("<AGE_HNPCC>100</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT>" + comment1 + "</COMMENT>");
		sb.append("</RELATION>");
		// **** 2 *****
		sb.append("<RELATION>");
		sb.append("<TYPE>" + Util.FATHER + "</TYPE>");
		sb.append("<NAME>Fred</NAME>");
		sb.append("<CC>1</CC>");
		sb.append("<AGE_CC>55</AGE_CC>");
		sb.append("<MULTPOLS>0</MULTPOLS>");
		sb.append("<HNPCC>0</HNPCC>");
		sb.append("<AGE_HNPCC>-1</AGE_HNPCC>");
		sb.append("<DECEASED>0</DECEASED>");
		sb.append("<AGE_DECEASED>-1</AGE_DECEASED>");
		sb.append("<COMMENT>" + comment2 + "</COMMENT>");
		sb.append("</RELATION>");

		String correctXML = Utils.surround(sb.toString(), erefid,
				Util.CATEGORY_3_STRING);

		Utils.testRest(correctXML, erefid);

	}

	@Test
	@Ignore
	public void test_14() {

	}

	@Test
	public void test_15() {

		String erefid = "testcase15";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
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
		ccAgeElement.sendKeys("100");

		WebElement otElement = driver.findElement(By.id(name(Util.ID_OT, 1)));
		otElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE,
				1)));
		otAgeElement.sendKeys("100");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("90");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_16() {

		String erefid = "testcase16";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("100");

		WebElement mElement = driver.findElement(By.id(name(Util.ID_MULT, 1)));
		mElement.click();

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_17() {

		String erefid = "testcase17";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
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

		// ***** 2 ***
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

		// ***** 3 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("57");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_18() {

		String erefid = "testcase18";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("54");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_AUNTIES));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("80");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();
		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_19() {

		String erefid = "testcase19";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
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
		ccAgeElement.sendKeys("80");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_MAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("81");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 3 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("82");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 4 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("83");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	@Test
	public void test_20() {

		String erefid = "testcase20";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By.id(Util.GWT_DEBUG_CHILDREN));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("56");

		// ****** 2 *****
		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 2)));
		nameElement.sendKeys("Joe");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 2)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 2)));
		ccAgeElement.sendKeys("56");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}
	
	

}
