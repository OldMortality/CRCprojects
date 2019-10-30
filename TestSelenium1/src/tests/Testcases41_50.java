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

public class Testcases41_50 {

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

	@Test
	public void test_41() {

		String erefid = "testcase41";
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
		ccAgeElement.sendKeys("55");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 2 *****
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
	public void test_42() {

		String erefid = "testcase42";
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
		ccAgeElement.sendKeys("75");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 2 *****
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("44");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_43() {

		String erefid = "testcase43";
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
		ccAgeElement.sendKeys("80");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 2 *****
		element = driver.findElement(By.id(Util.GWT_DEBUG_CHILDREN));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("40");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();
		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_44() {

		String erefid = "testcase44";
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
		ccAgeElement.sendKeys("70");

		WebElement otElement = driver.findElement(By.id(name(Util.ID_OT, 1)));
		otElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE,
				1)));
		otAgeElement.sendKeys("70");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 2 *****
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDMOTHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 3 *****
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		// ****** 4 *****
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_45() {

		String erefid = "testcase45";
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
		ccAgeElement.sendKeys("70");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		// ***** 2 ***
		element = driver.findElement(By.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
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
		element = driver.findElement(By.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE, 1)));
		ccAgeElement.sendKeys("70");

		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_46() {

		String erefid = "testcase46";
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
		ccAgeElement.sendKeys("55");

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
		ccAgeElement.sendKeys("100");

		WebElement oElement = driver.findElement(By.id(name(Util.ID_OT, 1)));
		oElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE, 1)));
		otAgeElement.sendKeys("100");
		
		
		okButton = driver.findElement(By.id("gwt-debug-" + Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_47() {

		String erefid = "testcase47";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Joe");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("44");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	@Test
	public void test_48() {

		String erefid = "testcase48";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Joe");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("44");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	@Test
	public void test_49() {

		String erefid = "testcase49";
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
		ccAgeElement.sendKeys("56");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_50() {

		String erefid = "testcase50";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_AUNTIES));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("60");

		// ***** 2 ***
		nameElement = driver.findElement(By.id(name(Util.ID_NAME, 2)));
		nameElement.sendKeys("Rita");

		WebElement otElement = driver.findElement(By.id(name(Util.ID_OT, 2)));
		otElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE,
				2)));
		otAgeElement.sendKeys("60");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	void testRiskCategory(String shouldBe) {
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
		assertEquals("run number: " + 1, shouldBe, riskLevel);

		// Press close button to preserve memory.
		WebElement closeButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_CLOSE));
		closeButton.click();

		Set<String> set = driver.getWindowHandles();

		String base = driver.getWindowHandle();

		if (set.size() > 1) {

			driver.switchTo().window((String) set.toArray()[0]);

			driver.close();
			driver.switchTo().window(base);
		}

	}

}
