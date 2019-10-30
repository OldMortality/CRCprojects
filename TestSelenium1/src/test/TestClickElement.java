package test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ac.otago.infoscience.eCRC.client.Util;

public class TestClickElement {

	void go() {
		System.out.println("Hello World");
		FirefoxProfile profile = new FirefoxProfile();

		try {
			File f = new File("C:/Users/michel/Downloads/gwt-dev-plugin.xpi");
			if (f.exists()) {
				System.out.println("GWT plugin exists");
			} else {
				System.out.println("GWT plugin does not exist");
			}
			profile.addExtension(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebDriver driver = new FirefoxDriver(profile);

		driver.get("http://127.0.0.1:8888/ecrc.html?gwt.codesvr=127.0.0.1:9997&name=Joe&dob=13sep1964&nhi=1234567&erefid=123444");

		// driver.get("http://localhost:8080/ecrc/ecrc.html?name=Fred&erefid=123");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.id("gwt-debug-Father"));
		// WebElement element =
		// driver.findElement(By.id("gwt-debug-thebutton"));

		Dimension d = element.getSize();
		System.out.println("element size:" + d.height + " " + d.width);
		element.click();

		WebElement nameElement = driver.findElement(By.id("gwt-debug-" + Util.ID_NAME + "-" + 1));
		nameElement.sendKeys("Fred");
		
		WebElement ccElement = driver.findElement(By.id("gwt-debug-" + Util.ID_CC + "-" + 1));
		ccElement.click();
		
		WebElement ccAgeElement = driver.findElement(By.id("gwt-debug-" + Util.ID_CC_AGE + "-" + 1));
		ccAgeElement.sendKeys("44");
		
		
		WebElement okButton = driver.findElement(By.id("gwt-debug-"
				+ Util.ID_BUTTON_OK));
		okButton.click();

		
		
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

		// driver.quit();
	}

	public static void main(String[] args) {
		TestClickElement t = new TestClickElement();
		t.go();
	}
}
