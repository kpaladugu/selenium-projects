package com.amazon.libs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common{
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public Common(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
	}
	
	public void browseUrl(String url){
		driver.get(url);
	}
	
	public void enterText(WebElement element, String data){
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(data);
		
	}
	
	public String getText(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	public void clickElement(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();		
	}
	
	public void selectListBoxValue(WebElement element, String valueToBeSelected){
		Select selectObj = new Select(element);
		selectObj.selectByVisibleText(valueToBeSelected);
	}
	
}