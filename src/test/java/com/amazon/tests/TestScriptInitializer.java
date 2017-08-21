package com.amazon.tests;

import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.amazon.libs.util.Common;
import com.amazon.libs.util.DriverFactory;
import com.amazon.libs.util.ExcelUtility;
import com.amazon.libs.util.PropertyReader;

public class TestScriptInitializer {
	
	protected WebDriver driver;
	protected Common common;
	protected String moduleName;
	protected String testCaseName;
	
	@BeforeTest
	public void beforeTest(){
		
		PropertyReader propertyReader = PropertyReader.getPropertyReaderInstance();
		String appUrl = propertyReader.getConfigData("app.url");
		
		DriverFactory driverFactory = new DriverFactory();		
		driver = driverFactory.createLocalDriver();
		
		common = new Common(driver);
		common.browseUrl(appUrl);
	}
	
	@DataProvider(name="testData")
	public Object[][] getTestData(){
		String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx";
		
		ExcelUtility excelUtility = new ExcelUtility(excelFilePath, moduleName);
		Map<Integer, Map<String, String>> testDataMap = excelUtility.getData(testCaseName);
		
		Map<String, String> firstRowData = testDataMap.get(0);
		Object[][] arrTestData = new Object[testDataMap.size()][firstRowData.size()];
		
		for(int i = 0;i < testDataMap.size();i++){			
			Map<String, String> rowData = testDataMap.get(i);
			
			Iterator<String> rowDataValues = rowData.values().iterator();
			int j = 0;
			while(rowDataValues.hasNext()){
				arrTestData[i][j] = rowDataValues.next();
				j++;
			}
		}
		
		return arrTestData;
	}
	
	@AfterTest
	public void afterTest(){
		if(driver!=null){
			driver.quit();
		}
	}

}
