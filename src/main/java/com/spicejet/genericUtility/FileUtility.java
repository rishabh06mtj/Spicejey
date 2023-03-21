package com.spicejet.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	private FileInputStream fis;
	private Properties p;


	/**
	 * This method is used to open the property file
	 * @param filePath
	 */
	public void openPropertyFile(String filePath)
	{
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		p=new Properties();
		try {
			p.load(fis);
		} 
		catch (IOException e) {

			e.printStackTrace();
		}	
	}
	/**
	 * This method is used to fetch the value from property files
	 * @param filePath
	 * @param Key
	 * @return
	 */
	public String getDataFromPropertyFille(String Key)
	{

		String value = p.getProperty(Key).trim();
		return value;
	}
	/**
	 * This method is used to close the property files
	 */
	public void closePropertyFile()
	{
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
