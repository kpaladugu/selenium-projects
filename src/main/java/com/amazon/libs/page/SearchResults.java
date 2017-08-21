package com.amazon.libs.page;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.amazon.libs.util.Common;

public class SearchResults {
	
	@FindBys({@FindBy(className = "s-item-container")})
	private List<WebElement> searchResults;
	
	WebDriver driver;
	Common common;

	public SearchResults(WebDriver driver, Common common){
		this.driver = driver;
		this.common = common;
		PageFactory.initElements(driver, this);
	}
	
	public void verifySearchResultsDisplayed(){		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchResults.size() > 0, "No search results are displayed on the page!!!");
		softAssert.assertAll();
	}
}
