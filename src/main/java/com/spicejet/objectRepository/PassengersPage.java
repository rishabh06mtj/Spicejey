package com.spicejet.objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassengersPage {
	WebDriver driver;
	@FindBy(xpath = "//div[text()='Passenger Information']/following-sibling::div/descendant::div[contains(text(),'Passenger')]")
	private List<WebElement> passengerTotalList;
	String totalPassengerXpath="//div[text()='Passenger Information']/following-sibling::div/descendant::div[contains(text(),'Passenger %S')]";


	@FindBy(xpath = "//div[text()='I am flying as the primary passenger']/preceding-sibling::div/*[name()='svg']")
	private WebElement iAmFlyingAsAprimaryPassenger;

	@FindBy(xpath = "//div[text()='Select']")
	private WebElement selectStatusDrpDn;

	@FindBy(xpath = "//div[text()='Master']")
	private WebElement chooseStatus;

	String travellerNameXpath="//input[@data-testid='traveller-%s-first-traveller-info-input-box']";

	String travellerLastNameXpath="//input[@data-testid='traveller-%s-last-traveller-info-input-box']";

	@FindBy(xpath = "//div[text()='Next']")
	private WebElement nextBtn;


	@FindBy(xpath = "//input[@data-testid='first-inputbox-contact-details']")
	private WebElement contactDetailsFrstNameTxtBx;

	@FindBy(xpath = "//input[@data-testid='last-inputbox-contact-details']")
	private WebElement contactDetailsLastNameTxtBx;

	@FindBy(xpath = "//input[@data-testid='contact-number-input-box']")
	private WebElement contactDetailsNumberTxtBx;


	@FindBy(xpath = "//input[@data-testid='emailAddress-inputbox-contact-details']")
	private WebElement contactDetailsEmailTxtBx;

	@FindBy(xpath = "//input[@data-testid='city-inputbox-contact-details']")
	private WebElement contactDetailsCityTxtBx;

	@FindBy(xpath = "//div[@data-testid='traveller-info-continue-cta']")
	private WebElement submitBtn;
	public PassengersPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	private WebElement StringToWebElement(String partialXpath,String replaceData)
	{
		String xpath=String.format(partialXpath, replaceData);
		return driver.findElement(By.xpath(xpath));
	}
	
	
	/**
	 * This method is used to fill the contact details
	 */
	public void fillContactDetails()
	{
		contactDetailsFrstNameTxtBx.sendKeys("ram");
		contactDetailsLastNameTxtBx.sendKeys("chand");
		contactDetailsNumberTxtBx.sendKeys("9108544004");
		contactDetailsEmailTxtBx.sendKeys("avipdh@gmail.com");
		contactDetailsCityTxtBx.sendKeys("delhi");

	}
	
	/**
	 * This method is used to fill the details for the list of passengers
	 */
	public void fillPassengerDetails() 
	{
		int pCount=passengerTotalList.size();
		System.out.println("passenger count"+pCount);
		int j=1;
		for(int i=1;i<=pCount;i++)
		{
			if(i==1)
			{
				iAmFlyingAsAprimaryPassenger.click();
			}
			else if(i>1)
			{

				selectStatusDrpDn.click();
				chooseStatus.click();

				WebElement travellerNameList = StringToWebElement(travellerNameXpath, Integer.toString(j));
				travellerNameList.sendKeys("hello wrld"+(char)(96+j));
				WebElement travellerLastNameList = StringToWebElement(travellerLastNameXpath, Integer.toString(j));
				travellerLastNameList.sendKeys("game over");
				j++;
			}
			if(i!=pCount)
			{
				nextBtn.click();
			}
		}
	}

	/**
	 * This method is used to click on submit button
	 */
	public void clickOnSubmitBtn()
	{	
		submitBtn.click();
	}
}
