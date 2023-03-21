package com.spicejet.objectRepository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spicejet.genericUtility.JavaUtility;
import com.spicejet.genericUtility.WebDriverUtility;

public class FlightsPage {

	WebDriver driver;
	WebDriverUtility wDriverUtility;
	JavaUtility javaUtility;

	String flightDepartueRadioButton="//div[text()='Select your' and text()='Departure to']/../../.."
			+ "/following-sibling::div/descendant::div[text()='%s']/../../../following-sibling::div//*[name()='svg']";
	String flightDeparturePrice="//div[text()='Select your' and text()='Departure to']/../../.."
			+ "/following-sibling::div/descendant::div[text()='%s']/../../../following-sibling::div//span/..";

	
	String flighReturnRadioButton="//div[text()='Select your' and text()='Return Flight to']/../../.."
			+ "/following-sibling::div/descendant::div[text()='%s']/../../../following-sibling::div//*[name()='svg']";
	String flightReturnPrice="//div[text()='Select your' and text()='Return Flight to']/../../.."
			+ "/following-sibling::div/descendant::div[text()='%s']/../../../following-sibling::div//span/..";
	
	String upDPrice="//div[text()='Select your' and text()='Return Flight to']/../../.."
			+ "/following-sibling::div/descendant::div/../../../following-sibling::div//span/..";
	
	@FindBy(xpath = "//div[text()='Continue']/parent::div[@data-testid='continue-search-page-cta']")
	private WebElement submitBtn;
	
	
	
	public FlightsPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}
	
	private List<WebElement> StringToWebElement(String partialXpath,String replaceData)
	{
		String xpath=String.format(partialXpath, replaceData);
		return driver.findElements(By.xpath(xpath));
	}
	
	private WebElement FindAndClickOnCheapestFareRadioBtn(List<WebElement> radioBtn,List<WebElement> radioPrice)
	{
		List<Integer> priceList=new ArrayList<Integer>(); 
		
//		radioPrice.stream().forEach(price -> priceList.add(Integer.parseInt(price.getText().split(" ")[1].replaceAll(",",""))));
		//
//		System.out.println(radioBtn.size());
		 
		for (WebElement price : radioPrice) {
			String prc = price.getText().split(" ")[1];
			String prc1[]=prc.split(",");
			String cleanedPrc="";
			for(int i=0;i<prc1.length;i++)
			{
				cleanedPrc=cleanedPrc+prc1[i];
			}
			priceList.add(Integer.parseInt(cleanedPrc));

		}
		System.out.println(priceList.size());
		int indx=0;
		int small=priceList.get(0);
		for(int i=0;i<priceList.size();i++)
		{
			if(small>priceList.get(i))
			{
				small=priceList.get(i);
				indx=i;
			}
		}
		System.out.println("cheapest price: "+small);
		System.out.println("price name"+radioPrice.get(indx).getText());

		return radioBtn.get(indx);
	}
	
	/**
	 * This method is used to click on the cheapest departure ticket based on the flight number
	 * @param dFlightName
	 * @param customWaitTime
	 */
	public void clickonCheapestDepartureRadioBtn(String dFlightName,int customWaitTime)
	{
		List<WebElement> dRadioBtn = StringToWebElement(flightDepartueRadioButton, dFlightName);
		List<WebElement> dRadioPrice=StringToWebElement(flightDeparturePrice, dFlightName);
		WebElement cheapDepartureTicket = FindAndClickOnCheapestFareRadioBtn(dRadioBtn,dRadioPrice);
		int y=cheapDepartureTicket.getLocation().getY();
		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("window.scrollTo(0,+"+y+")", "");
		js.executeScript("arguments[0].scrollIntoView()", cheapDepartureTicket);
		javaUtility=new JavaUtility();
		javaUtility.customWait(cheapDepartureTicket, customWaitTime);		
	}
	
	/**
	 * This method is used to click on the cheapest return ticket based on the flight number
	 * @param rFlightName
	 * @param customWaitTime
	 */
	public void clickonCheapestReturnRadioBtn(String rFlightName,int customWaitTime)
	{
		List<WebElement> rRadioBtn = StringToWebElement(flighReturnRadioButton, rFlightName);
		List<WebElement> rRadioPrice=StringToWebElement(flightReturnPrice, rFlightName);
		WebElement cheapReturnTicket = FindAndClickOnCheapestFareRadioBtn(rRadioBtn,rRadioPrice);
		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("window.scrollTo(0,+"+y+")", "");
		js.executeScript("arguments[0].scrollIntoView()", cheapReturnTicket);
		javaUtility=new JavaUtility();
		javaUtility.customWait(cheapReturnTicket, customWaitTime);
	}
	
	/**
	 * This method is used to click on the submit button
	 * @param waitTime
	 */
	public void clickOnSubmitBtn(int waitTime)
	{
		javaUtility=new JavaUtility();
		javaUtility.customWait(submitBtn, waitTime);
	}







}
