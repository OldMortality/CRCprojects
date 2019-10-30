package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloWorld {
	
	
	void sayHello() {
		System.out.println("Hello World");
		WebDriver driver =  new FirefoxDriver();
		driver.get("http://127.0.0.1:8888/ecrc.html?gwt.codesvr=127.0.0.1:9997?name=Joe&dob=13sep1964&nhi=1234567&erefid=123444");
		
		driver.quit();
	}
	
	
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.sayHello();
	}
}
