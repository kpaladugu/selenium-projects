package com.amazon.libs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	WebDriver driver;
	
	public WebDriver getDriver(){
		return createLocalDriver();		
	}
	
	public WebDriver createLocalDriver(){
		PropertyReader propertyReader = PropertyReader.getPropertyReaderInstance();
		String browser = propertyReader.getConfigData("browserName");
		
		switch(browser.toLowerCase()){
			case "firefox":
				System.setProperty("webdriver.gecko.driver", propertyReader.getConfigData("ff.driver.path"));
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", propertyReader.getConfigData("chrome.driver.path"));
				driver = new ChromeDriver();
				break;
		}
		
		return driver;
	}
}
