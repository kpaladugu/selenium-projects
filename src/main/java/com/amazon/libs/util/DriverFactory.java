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
		
		if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", propertyReader.getConfigData("ff.driver.path"));
				driver = new FirefoxDriver();
		}else{
				System.setProperty("webdriver.chrome.driver", propertyReader.getConfigData("chrome.driver.path"));
				driver = new ChromeDriver();
		}
		
		return driver;
	}
}
