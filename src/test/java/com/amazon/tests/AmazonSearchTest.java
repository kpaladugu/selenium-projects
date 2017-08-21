package com.amazon.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.libs.page.HomePage;
import com.amazon.libs.page.SearchResults;

public class AmazonSearchTest extends TestScriptInitializer{
	
	@BeforeClass
	public void beforeClass(){
		moduleName = "Search";
		testCaseName = this.getClass().getSimpleName();
	}

	@Test(dataProvider = "testData")
	public void searchTest(String tcName, String executeFlag, String searchText){
		HomePage homePage = new HomePage(driver, common);
		homePage.search(searchText);
		
		SearchResults searchResults = new SearchResults(driver, common);
		searchResults.verifySearchResultsDisplayed();		
	}
}
