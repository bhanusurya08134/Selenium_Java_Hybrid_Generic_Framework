package ui.creative.testcomponents;

import org.openqa.selenium.WebDriver;

import ui.creative.pageobjects.BooksPage;
import ui.creative.pageobjects.DemoWebShopPage;
import ui.creative.pageobjects.HomePage;
import ui.creative.pageobjects.JewelryPage;
import ui.creative.pageobjects.LandingPage;
import ui.creative.pageobjects.MyAccoutPage;
import ui.creative.pageobjects.ProductReviewPage;
import ui.creative.pageobjects.WishListPage;




public class PageObjectManager {
	
	
	public WebDriver driver;
	public LandingPage lp;
	public HomePage lm;
	public DemoWebShopPage dws;
	public JewelryPage jp;
	public WishListPage wl;
	public ProductReviewPage pr;
	public BooksPage BP;
	public MyAccoutPage ap;
	
	


	
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}
	
	public LandingPage getLandingPage() {
		lp = new LandingPage(driver);
		return lp;
	}
	

	public HomePage getHomePage() {
		lm = new HomePage(driver);
		return lm;
	}

	public DemoWebShopPage getDemoWebShopPage() {
		dws = new DemoWebShopPage(driver);
		return dws;
	}
	
	public JewelryPage getJewelryPage() {
		jp = new JewelryPage(driver);
		return jp;
	}
	
	public WishListPage getWishListPage() {
		wl = new WishListPage(driver);
		return wl;
	}
	
	public ProductReviewPage getProductReviewPage() {
		pr = new ProductReviewPage(driver);
		return pr;
	}
	
	public BooksPage getBooksPage() {
		BP = new BooksPage(driver);
		return BP;
	}
	
	public MyAccoutPage getMyAccoutPage() {
		ap = new MyAccoutPage(driver);
		return ap;
	}
	
}
