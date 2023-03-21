package com.ecom.spicejet.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.spicejet.genericUtility.FileUtility;
import com.spicejet.genericUtility.IconstantPath;
import com.spicejet.genericUtility.JavaUtility;
import com.spicejet.genericUtility.PropertyFileKeys;
import com.spicejet.genericUtility.SheetName;
import com.spicejet.genericUtility.WebDriverUtility;
import com.spicejet.genericUtility.WorkBookUtility;
import com.spicejet.objectRepository.FlightsPage;
import com.spicejet.objectRepository.HomePage;
import com.spicejet.objectRepository.PassengersPage;

public class BookFlightTest {

	public static void main(String[] args) throws InterruptedException {
		FileUtility fileUtility=new FileUtility();
		JavaUtility javaUtility=new JavaUtility();
		WorkBookUtility wbUtility=new WorkBookUtility();
		fileUtility.openPropertyFile(IconstantPath.PROPERTY_FILE_PATH);
		wbUtility.openExcelWorkBook(IconstantPath.EXCEL_PATH);
		String startCityName = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 2);
		String endCityName = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 3);
		String travelStartData = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 4).trim();
		String travelReturnDate = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 5).trim();
		String flightName = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 6);
		String flightNameReturn = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 7);
		String departureMonth = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 8);
		String returnMonth = wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 9);
		String children=wbUtility.getExcelData(SheetName.FLIGHTS.convertToString(), 2, 10);
		Integer childrenCount = (Integer)javaUtility.convertToAnyDataType(children, "int");
		String url = fileUtility.getDataFromPropertyFille(PropertyFileKeys.URL.convertToString());
		String time = fileUtility.getDataFromPropertyFille(PropertyFileKeys.TIMEOUT.convertToString());
		Long timeout = (Long)javaUtility.convertToAnyDataType(time, "long");
		String browser = fileUtility.getDataFromPropertyFille(PropertyFileKeys.BROWSER.convertToString());
		String customWait = fileUtility.getDataFromPropertyFille(PropertyFileKeys.CUSTOMWAIT.convertToString());
		Integer customWaitTime = (Integer)javaUtility.convertToAnyDataType(customWait, "int");
		WebDriverUtility wdUtility=new WebDriverUtility();
		WebDriver driver = wdUtility.setApplication(browser, timeout, url);
		wbUtility.closeExcelFile();
		fileUtility.closePropertyFile();
		HomePage homePage=new HomePage(driver);
		homePage.fillFlightDetails(startCityName, endCityName, "9", "15", "January", "2023", "January", "2023",childrenCount);
//		homePage.clickOnFlightsLink(startCityName, endCityName);
//		homePage.setFlightDetailsForRoundTrip(departureMonth,returnMonth,travelStartData, travelReturnDate, childrenCount);
//		wdUtility.initialiseExplicitWait(timeout);
//		wdUtility.waitTillElementClickable(homePage.searchBtnLoc());
//		homePage.clickOnSearchBtn();

		FlightsPage flightsPage=new FlightsPage(driver);
		flightsPage.clickonCheapestDepartureRadioBtn(flightName, customWaitTime);
		flightsPage.clickonCheapestReturnRadioBtn(flightNameReturn, customWaitTime);
		flightsPage.clickOnSubmitBtn(customWaitTime);

		PassengersPage pasnPage=new PassengersPage(driver);
		pasnPage.fillContactDetails();
		pasnPage.fillPassengerDetails();
		pasnPage.clickOnSubmitBtn();

	}
}
