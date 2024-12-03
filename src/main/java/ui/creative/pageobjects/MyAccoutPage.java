package ui.creative.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class MyAccoutPage extends ReusableLibrary
{
	WebDriver driver;

	public MyAccoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[text()='Addresses']")
	WebElement addressCustomer;
	
	@FindBy(xpath="//input[@value='Edit']")
	WebElement addressEdit;
	
	@FindBy(xpath="//input[@id='Address_PhoneNumber']")
	WebElement PhoneNumber;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement EditAddressSave;
	
	@FindBy(xpath="//li[@class='phone']")
	WebElement PhoneNumberAfterEdit;
	
	

	
	
	
	
	public void EditTheCustomerDetails(Map<String, String> testData) {
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(addressCustomer);
		waitForElementToBeClickableThenClick(addressEdit);
		waitForElementToBeClickableThenClearThenSendkeys(PhoneNumber, testData.get("EditedPhoneNumber"));
		waitForElementToBeClickableThenClick(EditAddressSave);
	}
	
	public String ValidateCustomerDetailsAfterEdit() {
		String phoneNumberElement=getWebelementText(PhoneNumberAfterEdit);
		String phoneNumber=phoneNumberElement.trim();

		String numbersOnly = phoneNumber.replaceAll("\\D", "");

		return numbersOnly;
				
		
	}
	

}
