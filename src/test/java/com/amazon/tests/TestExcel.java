package com.amazon.tests;

import java.util.Map;

import com.amazon.libs.util.ExcelUtility;

public class TestExcel {

	public static void main(String[] args) {
		
		String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx";
		
		ExcelUtility excelUtility = new ExcelUtility(excelFilePath, "Search");
		
		System.out.println("Row count: " + excelUtility.getRowCount());
		
		Map<Integer, Map<String, String>> testData = excelUtility.getData("TC2");
		
		for(int i =0; i<testData.size();i++){
			Map<String, String> testDataRow = testData.get(i);
			
			String searchText = testDataRow.get("Search Text");
			
			System.out.println(searchText);
		}
	}

}
