package ui.creative.pageobjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class HomePage extends ReusableLibrary
{
		WebDriver driver;
		
		public HomePage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		
		@FindBy(xpath="(//a[@class='account'])[1]")
		WebElement currentUser;
		
///DWS
		
		@FindBy(xpath="(//a[contains(text(),'Books') or @class='top-menu-triangle'])[1]")
		WebElement bookTabBtn;
		
		@FindBy(xpath="(//a[contains(text(),'Computers') or @class='top-menu-triangle'])[1]")
		WebElement computersTabBtn;
		
		@FindBy(xpath="//div[@class='header']//li[1]")
		WebElement myaccount;// //input[@checked="checked"]
	
		@FindBy(xpath="(//a[contains(text(),'Jewelry') or @class='top-menu-triangle'])[1]")
		WebElement JewelryPage;
		
		@FindBy(xpath="//span[@class='cart-label' and text()='Wishlist']")
		WebElement WishListButton;
		
		@FindBy(xpath="//input[@id='small-searchterms']")
		WebElement searchBox;
		
		@FindBy(xpath="//input[@value='Search']")
		WebElement searchButton;
		
		@FindBy(xpath="(//div[@class='product-grid']//a[contains(text(),'')])[2]")
		WebElement searchResultProduct;
		
		@FindBy(xpath="//h1[@itemprop='name']")
		WebElement OpenedProductName;
	
		@FindBy(xpath="//div[@class='product-review-links']//a[contains(text(),'Add your review')]")
		WebElement addReviewButton;
		
		@FindBy(xpath="(//input[@type='checkbox'])[1]")
		WebElement selectItemCheckBox;
		
		@FindBy(xpath="//input[@name='updatecart']")
		WebElement updateCartButton;
		
		
		
		
		public String currentUserText() {
			
			return currentUser.getText();
		}


		public String currentWebPage() {
			
			String currentPage = driver.getTitle();
			
			return currentPage;
		}

		public void navigateToBooksTab() {
			
			bookTabBtn.click();
			
		}

		public void navigateToComputersTab() {
			
			computersTabBtn.click();
		}
		
		public void clickmyaccount() {
			waitForElementToBeClickableThenClick(myaccount);
			waitForAngularRequestsToFinish();
			
		}

		public void navigateJewelryPage() {
			waitForElementToBeClickableThenClick(JewelryPage);
			waitForAngularRequestsToFinish();
		}


		public void navigateToWishListPage() {
			waitForElementToBeClickableThenClick(WishListButton);
			waitForAngularRequestsToFinish();
		}


		public String searchProduct(Map<String, String> testData) {
			
			waitForElementToBeClickableThenClearThenSendkeys(searchBox, testData.get("ReviewProduct"));
			waitForElementToBeClickableThenClick(searchButton);
			waitForAngularRequestsToFinish();
			waitForElementToBeClickableThenClick(searchResultProduct);
			waitForAngularRequestsToFinish();
			String ActualProductName = OpenedProductName.getText();
			
			return ActualProductName;
		}


		public void clickOnAddReviewToNavigateReviewsPage(Map<String, String> testData) {
			
			waitForElementToBeClickableThenClick(addReviewButton);
			waitForAngularRequestsToFinish();
		}


		public void clearCart() {
			waitForElementToBeClickableThenClick(selectItemCheckBox);
			
			waitForElementToBeClickableThenClick(updateCartButton);
			
			waitForAngularRequestsToFinish();
		}
		
		
		
}
