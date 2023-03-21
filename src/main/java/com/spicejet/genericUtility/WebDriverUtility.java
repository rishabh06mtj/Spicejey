package com.spicejet.genericUtility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility {

	/**
	 * This class contains reusable methods to work with webdriver 
	 * @author abhishek
	 *
	 */
	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * This method is used to open the application
	 * based on the browser provided
	 * enter the url
	 * @param browser
	 * @param timeout
	 * @param url
	 * @return
	 */
	public WebDriver setApplication(String browser,Long timeout,String url)
	{
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		if(browser.equals(browser))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(options);
		}
		else if(browser.equals(browser))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equals(browser))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else
		{
			System.out.println("enter valid browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
	
	/**
	 * This method is used to initialise explicit wait
	 * @param time
	 */
	public void initialiseExplicitWait(long time)
	{
		wait=new WebDriverWait(driver,time);
	}
	
	/**
	 * This method is used to waitTillVisibility Of elements
	 * @param waitCondition
	 */
	public void waitTillElementClickable(WebElement waitCondition)
	{
		
		wait.until(ExpectedConditions.elementToBeClickable(waitCondition));
	}

}
