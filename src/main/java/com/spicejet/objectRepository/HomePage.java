package com.spicejet.objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spicejet.genericUtility.PropertyFileKeys;
import com.spicejet.genericUtility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//div[text()='Flights']")
	private WebElement FlightsLnk;	
	@FindBy(xpath = "//div[text()='Flights']/../../following-sibling::div/descendant::div[text()='round trip']")
	private WebElement roundTripRadio;



	@FindBy(xpath = "//div[text()='From']") 
	private WebElement fromInputField;
	
	@FindBy(xpath = "//div[text()='To']") 
	private WebElement toInputField;
	
	String fromCityXpath="//div[text()='%s']";
	

	String toCityXpath="//div[text()='%s']";
	
	@FindBy(xpath = "//div[text()='Departure Date']/following-sibling::div/div/*[name()='svg']") 
	private WebElement departureDateInputField;
	
	@FindBy(xpath = "//div[text()='Return Date']/following-sibling::div/div/*[name()='svg']")
	private WebElement returnDateInputField;
	
	String departureDateXpath="//div[@data-testid='undefined-calendar-picker']/descendant::div[contains(text(),'%s')]/../following-sibling::div/descendant::div[text()='%1s']";
	
	String returnDateXpath="//div[contains(text(),'December')]/../following-sibling::div/descendant::div[text()='%s']";
	
	@FindBy(xpath = "//div[text()='Passengers']") 
	private WebElement passengeListBx;
	
	@FindBy(xpath = "//div[text()='Children']/../following-sibling::div/div/div/../following-sibling::div/*[name()='svg']")
	private WebElement childrenAddBtn;
	
	@FindBy(xpath = "//div[@data-testid='home-page-flight-cta']")
	private WebElement searchFlightBtn;
	
	//sirApproach
	@FindBy(xpath = "//div[text()='round trip']")
	private WebElement roundTripRdBtn;
	@FindBy(xpath = "//div[text()='From']")
	private WebElement fromTxtBx;
	@FindBy(xpath = "//div[text()='From']/parent::div/descendant::div/input")
	private WebElement fromTxtField;
	String toTxtFieldXpath="//div[text()='%s']";
	@FindBy(xpath = "//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79']")
	private List<WebElement> listMonthYear;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillFlightDetails(String deptCity,String arrivalCity,String deptDate,String returnDate,String deptMonth,String deptYear,String retMonth,String retYear,int NumberOfChildren)
	{
		roundTripRadio.click();
		fromTxtBx.click();
		fromTxtField.sendKeys(deptCity);
		StringToWebElement(toTxtFieldXpath, arrivalCity).click();
		handleCalenderPopUp(listMonthYear,deptDate,deptMonth,deptYear,driver);
		handleCalenderPopUp(listMonthYear,returnDate,retMonth,retYear,driver);
		passengeListBx.click();
		clickOnChildrenButton(NumberOfChildren);
		passengeListBx.click();
		clickOnSearchBtn();
		
		
	}
	
	
	
	private WebElement StringToWebElement(String partialXpath,String replaceData)
	{
		String xpath=String.format(partialXpath, replaceData);
		return driver.findElement(By.xpath(xpath));
	}
	private WebElement StringToWebElement(String partialXpath,String replaceData1,String replaceData2)
	{
		String xpath=String.format(partialXpath, replaceData1,replaceData2);
		return driver.findElement(By.xpath(xpath));
	}
	
	private void clickOnChildrenButton(int num)
	{
		int i=1;
		while(i<=num)
		{
			childrenAddBtn.click();
			i++;
		}
		
	}
	
	/**
	 * This method is used to click on round trip radio button and enter the start and end city
	 * @param startCityName
	 * @param endCityName
	 */
	public void clickOnFlightsLink(String startCityName,String endCityName)
	{
		FlightsLnk.click();
		roundTripRadio.click();
		fromInputField.click();
		StringToWebElement(toCityXpath, startCityName).click();
		StringToWebElement(fromCityXpath, endCityName).click();
	}
	
	/**
	 * This method is used to enter the date for the departure  and return and click on the number of children 
	 * @param departureMonth
	 * @param returnMonth
	 * @param departureDate
	 * @param returnDate
	 * @param NumberOfChildren
	 */
	public void setFlightDetailsForRoundTrip(String departureMonth,String returnMonth,String departureDate,String returnDate,int NumberOfChildren)
	{
		
		
		StringToWebElement(departureDateXpath, departureMonth,departureDate).click();
		StringToWebElement(departureDateXpath, departureMonth,returnDate).click();
		passengeListBx.click();
		clickOnChildrenButton(NumberOfChildren);
		passengeListBx.click();
	}
	
	/**
	 * This method is used to click on search button
	 */
	public void clickOnSearchBtn()
	{
		
		searchFlightBtn.click();
	}
	public WebElement searchBtnLoc()
	{
		return searchFlightBtn;
	}
	private  void handleCalenderPopUp(List<WebElement> listMonthYear,String reqdDate,String reqMonth,String reqYear,WebDriver driver)
	{
		int count=0;
		for (int i = 0; i < listMonthYear.size(); i++) 
		{
			WebElement currentMonthYearElement = listMonthYear.get(i);
			String currentMonthYear = currentMonthYearElement.getText();
			System.out.println(currentMonthYear);
			String currentMonth = currentMonthYearElement.getText().split(" ")[0];
			String currentYear = currentMonthYearElement.getText().split(" ")[1];
			System.out.println(currentMonth);
			System.out.println(currentYear);
			if(currentMonth.equals("January") && currentYear.equals("2023"))
			{
				count=i+1;
				break;
			}
		}
		if(count>=1)
		{
			driver.findElement(By.xpath("(//div[text()='"+reqdDate+"'])["+count+"]")).click();
		}
		else
			System.out.println("enter a valid month and year");
	}
}
