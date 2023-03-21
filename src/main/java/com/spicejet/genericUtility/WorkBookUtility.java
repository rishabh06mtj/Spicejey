package com.spicejet.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WorkBookUtility {

	private FileInputStream fis = null;
	private Workbook wb=null;


	/**
	 * THis method is used to open the workBook
	 * @param excelPath
	 */
	public void openExcelWorkBook(String excelPath)
	{

		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to fetch data from excel files using row and cell indexes.
	 * @param excelPath
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 */
	public String getExcelData(String sheetName,int row,int cell){


		DataFormatter df=new DataFormatter();
		String data=df.formatCellValue(wb.getSheet(sheetName).getRow(row).getCell(cell));
		return data;	
	}


	/**
	 * This method is used to close the excel file.
	 */
	public void closeExcelFile()
	{
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to fetch data from excel file using TestCaseName and ColumnName
	 * @param excelPath
	 * @param sheetName
	 * @param testcaseName
	 * @param columnName
	 * @return
	 */
	public String getExcelData(String sheetName,String testcaseName,String columnName)
	{
		String data="";
		DataFormatter df=new DataFormatter();
		try
		{
			Sheet sheet = wb.getSheet(sheetName);
			int rowLength=sheet.getLastRowNum();
			int cellLength=sheet.getRow(0).getLastCellNum();
			String expectedTestCaseName = testcaseName;
			String expectedColumnName= columnName;

			for (int i = 0; i <= rowLength; i++) {
				String actualTestCaseName = sheet.getRow(i).getCell(0).toString();
				if(expectedTestCaseName.equals(actualTestCaseName))
				{
					for (int j = 1; j < cellLength; j++) {
						String actualColumnName=sheet.getRow(i).getCell(j).toString();
						if(expectedColumnName.equals(actualColumnName))
						{
							data=df.formatCellValue(sheet.getRow(i+1).getCell(j));
							break;
						}
					}
					break;
				}
			}		

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * This method is used to write data in excel file
	 * @param excelPath
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeToExcel(String excelPath,String sheetName,int row,int cell,String value)
	{
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook wb=null;
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(value);

		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
