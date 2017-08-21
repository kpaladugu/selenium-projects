package com.amazon.libs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    Map<String, String> rowDataMap;            
    Map<Integer, Map<String, String>> iterationData = new LinkedHashMap<Integer, Map<String, String>>();
                            
    XSSFWorkbook workbook;
    XSSFSheet workSheet;
    
    public ExcelUtility(String xlFilePath, String sheetName) {
                
        FileInputStream fis = null;
        
        try {
              fis = new FileInputStream(xlFilePath);
        } catch (FileNotFoundException e) {
              e.printStackTrace();
        }
        
        try {
              workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
              e.printStackTrace();
        }
        
        workSheet = workbook.getSheet(sheetName);
    }
    
    public int getRowCount(){
        return workSheet.getLastRowNum();
    }
    
    public Map<Integer, Map<String, String>> getData(String testCaseName){                       
                
        int firstRow = workSheet.getFirstRowNum();
        int lastRow = workSheet.getLastRowNum();
        
        boolean isTestcaseFound = false;
        int headerRowNumber = -1;
        int mapCounter = 0;
        
        for(int i = firstRow;i <= lastRow;i++){                                 
                    
            XSSFRow currentRow = workSheet.getRow(i);
            Cell cell;
            try{
            	cell = currentRow.getCell(0);
            }catch(NullPointerException npe){
            	continue;
            }
            
            if(cell.getStringCellValue().equalsIgnoreCase(testCaseName)){                                        
                        
                if(!isTestcaseFound){
                    isTestcaseFound = true;
                    headerRowNumber = i-1;
                }
                
                if(isTestcaseFound){
                    Row headerRow = workSheet.getRow(headerRowNumber);
                    int cellCount = headerRow.getLastCellNum();
                    
                    rowDataMap = new LinkedHashMap<String, String>();
                    for(int cellIterator=0;cellIterator<cellCount;cellIterator++){
                        Cell headerCell = headerRow.getCell(cellIterator);
                        Cell dataCell = currentRow.getCell(cellIterator);
                        rowDataMap.put(headerCell.getStringCellValue(), dataCell.getStringCellValue());
                    }
                    
                    iterationData.put(mapCounter, rowDataMap);
                    mapCounter++;
                }
            }
        }
        
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return iterationData;
    }
    
    public Map<Integer, Map<String, String>> getData(){
    	
    	int firstRow = workSheet.getFirstRowNum();
    	int lastRow = workSheet.getLastRowNum();
    	int mapCounter = 1;
    	
    	XSSFRow headerRow = workSheet.getRow(firstRow);
    	
    	for(int i = firstRow + 1; i<= lastRow; i++){
    		XSSFRow dataRow = workSheet.getRow(i);
    		int cellNumber = dataRow.getLastCellNum();
    		
    		rowDataMap = new LinkedHashMap<String, String>();
    		for(int j = 0; j<cellNumber;j++){
    			Cell headerCell = headerRow.getCell(j);
    			Cell dataCell = dataRow.getCell(j);
    			rowDataMap.put(headerCell.getStringCellValue(), dataCell.getStringCellValue());
    		}
    		
    		iterationData.put(mapCounter, rowDataMap);
    		mapCounter++;
    	}
    	
    	return iterationData;
    }
    
}