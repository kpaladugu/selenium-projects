package com.amazon.libs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGSuiteBuilder {
	
	String executionDriverFilePath = System.getProperty("user.dir") + File.separator + "driver" + File.separator + "ExecutionDriver.xlsx";
	
	XmlSuite suite;
	XmlTest test;
	XmlClass xmlclass;	
	
	List<XmlSuite> suites = null;
	List<XmlTest> tests = null;
	List<XmlClass> classes = null;	
	
	@Test
	public void createXmlSuite(){
		ExcelUtility excelObj = new ExcelUtility(executionDriverFilePath, "BatchExecution");
		Map<Integer, Map<String, String>> batchExecutionData = excelObj.getData();
		
		for(int i = 1; i<=batchExecutionData.size();i++){
			Map<String, String> testScriptData = batchExecutionData.get(i);
			
			String moduleName = testScriptData.get("Module");
			String subModuleName = testScriptData.get("Sub-Module");
			String testCaseName = testScriptData.get("Testcase Name");
			String testClassName = testScriptData.get("Test class name");
			String browsers = testScriptData.get("Browsers");
			String executionFlag = testScriptData.get("Execute");
			
			
			if(executionFlag.equalsIgnoreCase("YES")){
				//create one suite for each module name
				createSuites(moduleName);	
				
				//Create tests in each suite(i.e., add sub-modules to modules)
				createTests(moduleName, subModuleName);				
			}	
		}
		
		System.out.println(suites);
	}
	
	
	private void createSuites(String moduleName){
		
		if(suites == null){
			suites = new ArrayList<XmlSuite>();
			suite = new XmlSuite();
			suite.setName(moduleName);
			suites.add(suite);
			
		}else{
			boolean suiteFound = false;
			for(int i = 0;i < suites.size();i++){
				XmlSuite currentSuite = suites.get(i);
				if(currentSuite.getName().equalsIgnoreCase(moduleName)){
					suiteFound = true;
					break;
				}
			}
			
			if(!suiteFound){
				suite = new XmlSuite();
				suite.setName(moduleName);
				suites.add(suite);
			}
		}		
	}
	
	private void createTests(String moduleName, String subModuleName){
		
		for(int i = 0; i < suites.size();i++){
			suite = suites.get(i);
			if(suite.getName().equalsIgnoreCase(moduleName)){
				tests = suite.getTests();
				
				if(tests.size() == 0){
					test = new XmlTest();
					test.setName(subModuleName);
					tests = new ArrayList<XmlTest>();
					tests.add(test);
					suite.setTests(tests);
					suites.set(i, suite);
					break;
				}else{
					boolean testFound = false;
					for(int j = 0; j < tests.size();j++){
						test = tests.get(j);
						if(test.getName().equalsIgnoreCase(subModuleName)){
							testFound = true;
							break;
						}
					}
					
					if(!testFound){
						test.setName(subModuleName);
						tests.add(test);
						suite.setTests(tests);
						suites.set(i, suite);
						break;
					}
				}
			}			
		}
	}
}
