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

public class Testcases21_30 {

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
	public void test_21() {

		String erefid = "testcase21";
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
		ccAgeElement.sendKeys("44");


		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("80");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_22() {

		String erefid = "testcase22";
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
		ccAgeElement.sendKeys("70");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_MAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("70");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();


		// ****** 3 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("70");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		
		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	
	@Test
	public void test_23() {

		String erefid = "testcase23";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);
		
		Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}


	@Test
	public void test_24() {

		String erefid = "testcase24";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("60");
		
		WebElement mElement = driver.findElement(By.id(name(Util.ID_MULT,
				1)));
		mElement.click();
		
		
		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}

	@Test
	public void test_25() {

		String erefid = "testcase25";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_FATHER));
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
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_CHILDREN));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("57");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}


	@Test
	public void test_26() {

		String erefid = "testcase26";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_CHILDREN));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("40");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}



	@Test
	public void test_27() {

		String erefid = "testcase27";
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

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("54");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();


		// ****** 3 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_MOTHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("70");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		
		Utils.testRiskCategory(driver, Util.CATEGORY_1_STRING, erefid);

	}

	@Test
	public void test_28() {

		String erefid = "testcase28";
		System.out.println("Test case " + erefid);

		driver.get(testURL + erefid);

		// ***** 1 ***
		WebElement element = driver.findElement(By
				.id(Util.GWT_DEBUG_MOTHER));
		element.click();

		WebElement nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Eva");

		WebElement ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		WebElement ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("80");


		WebElement otElement = driver.findElement(By.id(name(Util.ID_OT, 1)));
		otElement.click();

		WebElement otAgeElement = driver.findElement(By.id(name(Util.ID_OT_AGE,
				1)));
		
		otAgeElement.sendKeys("80");
				
		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_FATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("80");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();


		Utils.testRiskCategory(driver, Util.CATEGORY_2_STRING, erefid);

	}


	

	@Test
	public void test_29() {

		String erefid = "testcase29";
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
		ccAgeElement.sendKeys("44");

		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
				Utils.testRiskCategory(driver, Util.CATEGORY_0_STRING, erefid);

	}

	@Test
	public void test_30() {

		String erefid = "testcase30";
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
		ccAgeElement.sendKeys("80");


		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();
		
		// ****** 2 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_PAT_GRANDFATHER));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("104");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();


		// ****** 3 *****
		element = driver.findElement(By
				.id(Util.GWT_DEBUG_SIBLINGS));
		element.click();

		nameElement = driver.findElement(By
				.id(name(Util.ID_NAME, 1)));
		nameElement.sendKeys("Fred");

		ccElement = driver.findElement(By.id(name(Util.ID_CC, 1)));
		ccElement.click();

		ccAgeElement = driver.findElement(By.id(name(Util.ID_CC_AGE,
				1)));
		ccAgeElement.sendKeys("55");
		
		okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		Utils.testRiskCategory(driver, Util.CATEGORY_3_STRING, erefid);

	}


	

	
	 
}
