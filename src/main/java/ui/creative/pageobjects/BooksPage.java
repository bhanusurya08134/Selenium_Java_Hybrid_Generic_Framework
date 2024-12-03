package ui.creative.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class BooksPage extends ReusableLibrary
{
	WebDriver driver;

	public BooksPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="(//a[text()='Computing and Internet'])/../../div[3]/div[2]//input")  //(//a[text()='Computing and Internet'])[2]/../../div[3]/div[2]//input[@type='button']
	WebElement addToCartButton;
	
	@FindBy(xpath="//p[@class='content']")
	WebElement cartConfirmationMessage;
	
	@FindBy(xpath="//span[@class='cart-label' and text()='Shopping cart']")
	WebElement shoppingCartButton;
	
	@FindBy(xpath="(//input[@type='checkbox'])[1]")
	WebElement selectItemCheckBox;
	
	@FindBy(xpath="//input[@type='checkbox' and @name='termsofservice']")
	WebElement termOfServiceCheckBox;
	
	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkOutButton;
	
	@FindBy(xpath="(//input[@title='Continue' and @type='button'])[1]")
	WebElement continueButton;
	
	@FindBy(xpath="//select[@id='BillingNewAddress_CountryId']")
	WebElement countryDropDownButton;
	
	@FindBy(xpath="//input[@id='BillingNewAddress_City']")
	WebElement cityTextBox;
	
	@FindBy(xpath="//input[@id='BillingNewAddress_Address1']")
	WebElement billingAddress1TextBox;
	
	@FindBy(xpath="//input[@id='BillingNewAddress_ZipPostalCode']")
	WebElement postalCodeTextBox;
	
	@FindBy(xpath="//input[@id='BillingNewAddress_PhoneNumber']")
	WebElement PhoneNumberTextBox;
	
	@FindBy(xpath="(//input[@title='Continue' and @type='button'])[2]")
	WebElement shippingAddressContinueBtn;
	
	@FindBy(xpath="(//input[@class='button-1 shipping-method-next-step-button' and @type='button'])")
	WebElement shippingMethodContinueBtn;
	
	@FindBy(xpath="(//input[@class='button-1 payment-method-next-step-button' and @type='button'])")
	WebElement paymentMethodContinueBtn;
	
	@FindBy(xpath="(//input[@class='button-1 payment-info-next-step-button' and @type='button'])")
	WebElement paymentInformationContinueBtn;
	
	@FindBy(xpath="//input[@class='button-1 confirm-order-next-step-button']")
	WebElement confirmButton;
			
	@FindBy(xpath="//div[@class='title']//strong")
	WebElement confirmMessage;
	
	@FindBy(xpath="(//ul[@class='details']//li)[1]")
	WebElement orderNumber;
	
	@FindBy(xpath="(//a[text()='Computing and Internet'])/../../div[3]/div[2]//input")  //(//a[text()='Computing and Internet'])[2]/../../div[3]/div[2]//input[@type='button']
	WebElement ComputingaddToCartButton;
	
	@FindBy(xpath="(//a[text()='Fiction'])/../../div[3]/div[2]//input")  //(//a[text()='Computing and Internet'])[2]/../../div[3]/div[2]//input[@type='button']
	WebElement FictiongaddToCartButton;
	
	@FindBy(xpath="(//a[text()='Health Book'])/../../div[3]/div[2]//input")  //(//a[text()='Computing and Internet'])[2]/../../div[3]/div[2]//input[@type='button']
	WebElement HealthBookaddToCartButton;
	
	@FindBy(xpath="//span[normalize-space()='Shopping cart']")  
	WebElement SelectShoppingCart;
	
	@FindBy(xpath="//*[text()='Fiction' ]/../../td/input[@name='removefromcart']")  
	WebElement FictionRemoveCheckBox;
	
	@FindBy(xpath="//input[@name='updatecart']")  
	WebElement UpdateShopingCarty;
	
	@FindBy(xpath="//*[@class='product']")  
	List<WebElement> products;
	
	
	
	

	public String selectBookAndAddToCart(Map<String, String> testData) throws InterruptedException {

		waitForAngularRequestsToFinish();

//      waitForElementToBeClickableThenClick(addToCartButton);
		
		//To optimize synchronization in shopping cart checkout
		System.out.println(!driver.findElement(By.xpath("(//a[@class='ico-cart'])[1]/span[2]")).getText().equalsIgnoreCase("(0)"));
		int i = 0;
		while (!driver.findElement(By.xpath("(//a[@class='ico-cart'])[1]/span[2]")).getText().equalsIgnoreCase("(0)")) {
			System.out.println("Cart is Not Empty . . .");
			sleepMin();
			i=i+1;
			if(i == 10) {
				break;	
			}
		}

		addToCartButton = driver.findElement(By.xpath("(//a[text()='"+testData.get("BookName")+"'])/../../div[3]/div[2]//input"));

		addToCartButton.click();

		sleepMin();

		String confirmationMessage = cartConfirmationMessage.getText();

		waitForAngularRequestsToFinish();
		
		sleepMAX();

		waitForElementToBeClickableThenClick(shoppingCartButton);
		
		waitForAngularRequestsToFinish();

		return confirmationMessage;
	}


	public String checkOutWithCustomerInfirmation(Map<String, String> testData) throws InterruptedException {
		
//		waitForAngularRequestsToFinish();
//		
//		waitForElementToBeClickableThenClick(shoppingCartButton);
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(selectItemCheckBox);
		
		sleepMin();
		
		waitForElementToBeClickableThenClick(termOfServiceCheckBox);
		
		waitForElementToBeClickableThenClick(checkOutButton);
		
		waitForAngularRequestsToFinish();
		
//		waitForElementToBeClickableThenClick(countryDropDownButton);
//		
//		System.out.println(testData.get("Country"));
//		
//		driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']//option[text()='"+testData.get("Country")+"']")).click();
//		
//		waitForElementToBeClickableThenSendkeys(cityTextBox, testData.get("City"));
//		
//		waitForElementToBeClickableThenSendkeys(billingAddress1TextBox, testData.get("Address1"));
//		
//		waitForElementToBeClickableThenSendkeys(postalCodeTextBox, testData.get("PostalCode"));
//		
//		waitForElementToBeClickableThenSendkeys(PhoneNumberTextBox, testData.get("PhoneNumber"));
		
		
		
		return null;
	}


	public String[] orderBookConfirmation(Map<String, String> testData) throws InterruptedException {
		
		waitForElementToBeClickableThenClick(continueButton);
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(shippingAddressContinueBtn);
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(shippingMethodContinueBtn);
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(paymentMethodContinueBtn);
		
		waitForAngularRequestsToFinish();
		
		waitForElementToBeClickableThenClick(paymentInformationContinueBtn);
		
		waitForAngularRequestsToFinish();
		
		
//Scroll to confirm button
		
		List<WebElement> confirmButtonVisibility = driver.findElements(By.xpath("//input[@class='button-1 confirm-order-next-step-button']"));
		
		if(confirmButtonVisibility.size()==0) {
			
			System.out.println("Confirm button not visible at first time");
			
			scrollToElement("//th[contains(text(),'Total')]");
			sleepMin();
			scrollToElement("//span[contains(text(),'Sub-Total:')]");
			sleepMin();
			scrollToElement("//input[@class='button-1 confirm-order-next-step-button']");
		}
		
		

		waitForElementToBeClickableThenClick(confirmButton);
		
		waitForAngularRequestsToFinish();
		
		String confirmationMessageText = confirmMessage.getText();
		
		String orderNumberText = orderNumber.getText();
		
		return new String[] {confirmationMessageText , orderNumberText};
	}

	
	
	public void AddMultipleBooks() throws InterruptedException {
		waitForElementToBeClickableThenClick(ComputingaddToCartButton);
		sleepMin();
		waitForElementToBeClickableThenClick(FictiongaddToCartButton);
	}
	
	public void SelectShopingCart() {
		waitForElementToBeClickableThenClick(SelectShoppingCart);
		
	}
	
	public void removeBookinShopingCart() throws InterruptedException {
		waitForElementToBeClickableThenClick(FictionRemoveCheckBox);
		waitForElementToBeClickableThenClick(UpdateShopingCarty);
		
	}
	
	public String verifyProductIsRemoved(Map<String, String> testData) {
		for(int i=0; i<products.size(); i++) {
			
			if(products.get(i).getText()!=testData.get("RemovedBook")) {
				return "Book Removed Successfully";
			}else {
				return "Book Not Removed Successfully";
			}
		}
		return null;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
