package ui.creative.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class WishListPage extends ReusableLibrary
{
	WebDriver driver;

	public WishListPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//td[@class='product']/a")
	WebElement WishListProductName;

	@FindBy(xpath = "//input[@value='Email a friend']")
	WebElement EmailFriendButton;
	
	@FindBy(xpath = "//input[@class='friend-email']")
	WebElement FriendMailTextBox;
	
	@FindBy(xpath = "//textarea[@class='personal-message']")
	WebElement PersonalMessageArea;
	
	@FindBy(xpath = "//input[@name='send-email']")
	WebElement SendMailButton;
	
	@FindBy(xpath = "//div[@class='page-body']/div")
	WebElement ConfirmMessage;
	
	@FindBy(xpath = "//span[@class='cart-label' and text()='Wishlist']")
	WebElement wishListPage;
	
	@FindBy(xpath = "//input[@name='removefromcart']")
	WebElement removeCheckBox;
	
	@FindBy(xpath = "//input[@name='updatecart']")
	WebElement updateWishlistButton;


	

	public String validateJewelInWishList(Map<String, String> testData) throws InterruptedException {
		sleepMin();
		String ProductInWIshList = WishListProductName.getText();
		return ProductInWIshList;
	}

	public void email_a_Friend(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(EmailFriendButton);
		sleepMin();
		waitForElementToBeClickableThenClearThenSendkeys(FriendMailTextBox, testData.get("FriendEmail"));
		sleepMin();
		waitForElementToBeClickableThenClearThenSendkeys(PersonalMessageArea, testData.get("Message"));
		sleepMin();
	}

	public String clickSendEmail(Map<String, String> testData) {
		waitForElementToBeClickableThenClick(SendMailButton);
		waitForAngularRequestsToFinish();
		String confirmTextMessage = ConfirmMessage.getText();

		return confirmTextMessage;
	}

	public void removeProductFromWishlist(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClick(wishListPage);
		waitForAngularRequestsToFinish();
		waitForElementToBeClickableThenClick(removeCheckBox);
		sleepMin();
		waitForElementToBeClickableThenClick(updateWishlistButton);
		waitForAngularRequestsToFinish();
	}




}
