package ui.creative.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class DemoWebShopPage extends ReusableLibrary
{
	WebDriver driver;

	public DemoWebShopPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	
//DWS_01	
	
	@FindBy(xpath="//span[text()='Back']")
	WebElement BackBtn;

	@FindBy(xpath="(//input[@type='radio'])[1]")
	WebElement ExcellentRadioBtn;
	
	@FindBy(xpath="//input[@value='Vote']")
	WebElement voteButton;
	
	@FindBy(xpath="//div[@class='poll-vote-error']")
	WebElement voteErrorMessage;
	

	
	@FindBy(xpath="//p[@class='content']")
	WebElement cartConfirmationMessage;
	
	@FindBy(xpath="//span[@class='cart-label' and text()='Shopping cart']")
	WebElement shoppingCartButton;
	
	
//DWS_03
	
	@FindBy(xpath="//img[@alt='Picture for category Desktops']")
	WebElement deskTopsCatogoryImageOption;
	
	@FindBy(xpath="//a[text()='Build your own cheap computer']/../..//input[@type='button']")
	WebElement computerAddToCartButton;
	
	@FindBy(xpath="//input[@class='button-1 add-to-cart-button']")
	WebElement computerAddToCartConfirmBtn;
	
	
//DWS_05
	
	@FindBy(xpath="//input[@id='small-searchterms']")
	WebElement searchstore;   
	
	@FindBy(xpath="//div[@class='product-name']")
	WebElement booktittle;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement search;
	
	@FindBy(xpath="//a[normalize-space()='Computing and Internet']") //a[normalize-space()='Computing and Internet']]
	WebElement clickonbook;
	
	@FindBy(xpath="//div[@class='header']//li[1]")
	WebElement myaccount;
	
//DWS_04
	
	@FindBy(xpath="//input[@checked=\"checked\"]")
	WebElement radioBtn;
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement FirstName;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement LastName;
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement Email;
	
	
	
	
	
	
	public String validateCommunityPool() throws InterruptedException {
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(ExcellentRadioBtn);
		
		waitForElementToBeClickableThenClick(voteButton);
		
		Thread.sleep(500);
//		String Message1 = waitForPresenceOfElementThenGettext(voteErrorMessage);
		String Message = voteErrorMessage.getText();
		
		return Message;
	}


	

//DWS_03
	
	public String selectComputerAndAddToCart(Map<String, String> testData) throws InterruptedException {

		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(deskTopsCatogoryImageOption);
		
		waitForAngularRequestsToFinish();

	//	waitForElementToBeClickableThenClick(computerAddToCartButton);
		computerAddToCartButton = driver.findElement(By.xpath("//a[text()='"+testData.get("ComputerName2")+"']/../..//input[@type='button']"));
		
		computerAddToCartButton.click();

		waitForAngularRequestsToFinish();
		
		
    //To optimize synchronization in shopping cart checkout
		System.out.println(!driver.findElement(By.xpath("(//a[@class='ico-cart'])[1]/span[2]")).getText().equalsIgnoreCase("(0)"));
		int i = 0;
		while (!driver.findElement(By.xpath("(//a[@class='ico-cart'])[1]/span[2]")).getText().equalsIgnoreCase("(0)")) {
			System.out.println("Cart is Not Empty . . .");
			sleepMin();
			i=i+1;
			if(i == 15) {
				break;	
			}
		}
		
		
		waitForElementToBeClickableThenClick(computerAddToCartConfirmBtn);
		
		sleepMin();

		String confirmationMessage = cartConfirmationMessage.getText();
		
		waitForAngularRequestsToFinish();
		
		sleepMAX();

		waitForElementToBeClickableThenClick(shoppingCartButton);
		
		waitForAngularRequestsToFinish();

		return confirmationMessage;
	}

//DWS_05
	
	public String searchComputingandinternet(Map<String, String> testData) throws Exception {
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenSendkeys(searchstore, testData.get("Search_From_store"));
		
		waitForElementToBeClickableThenClick(search);
		
		waitForAngularRequestsToFinish();
		
		sleepMin();
		
//		waitForElementToBeClickableThenClick(clickonbook);
		
		driver.findElement(By.xpath("//a[normalize-space()='"+testData.get("Search_From_store")+"']")).click();
		
		String tittle = booktittle.getText();
		
		Thread.sleep(500);
		
		return tittle;
	}


	
//DWS_04
	
	public String gendervalidate(Map<String, String> testData) {
		
		String status = radioBtn.getAttribute("checked");
		
		return status;
	}


	public String Firstnamevalidation() {
		
		String text = FirstName.getAttribute("value");
		
		return text;
	}


	public String LastNamevalidation() {
		
		String text = LastName.getAttribute("value");
		
		return text;
		
	}


	public String Mailvalidation() {
		
		String text = Email.getAttribute("value");
		
		return text;
	}

	

}
