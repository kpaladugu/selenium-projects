package com.amazon.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestParent {
	 @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("before method from parent");
	  }
	 
	 @DataProvider(name = "testData1")
	 public void setTestData(){
		 System.out.println("testdata");
	 }
	 
	 @AfterMethod
	  public void afterMethod() {
		  System.out.println("after method from parent");
	  }
}
