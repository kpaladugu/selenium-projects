package com.amazon.libs.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.libs.util.Common;

public class HomePage {
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchTextBox;
	
	@FindBy(className="nav-search-submit")
	private WebElement searchButton;
	
	WebDriver driver;
	Common common;
	
	public HomePage(WebDriver driver, Common common){
		this.driver = driver;
		this.common = common;
		PageFactory.initElements(driver, this);
	}
	
	private void enterSearchText(String searchText){
		common.enterText(searchTextBox, searchText);
	}
	
	private void clickSearchButton(){
		common.clickElement(searchButton);
	}
	
	public void search(String searchText){
		enterSearchText(searchText);
		clickSearchButton();
	}

}
