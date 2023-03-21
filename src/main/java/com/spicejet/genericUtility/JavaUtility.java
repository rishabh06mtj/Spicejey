package com.spicejet.genericUtility;

import java.util.Random;

import org.openqa.selenium.WebElement;

public class JavaUtility {
	/**
	 * This method is used to generate a random number
	 * @param limit
	 * @return
	 */
	public int generateRandomNumber(int limit)
	{
		return new Random().nextInt(limit);
	}

	/**
	 * This method is used to convert String into any datatype
	 * @param value
	 * @param strategy
	 * @return
	 */
	public Object convertToAnyDataType(String value,String strategy)
	{
		Object data=null;
		if(strategy.equals("int"))
		{
			data=Integer.parseInt(value);
		}
		else if(strategy.equals("long"))
		{
			data=Long.parseLong(value);
		}
		return data;
	}
	
	public void customWait(WebElement element,int waitTime)
	{
		int i=0;
		while(i<waitTime)
		try
		{
			element.click();
			break;
		}
		catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
