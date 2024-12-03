package ui.creative.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ConfigFileReader;
import ui.creative.componentgroups.ReusableLibrary;



public class LandingPage extends ReusableLibrary {
	
	
	public WebDriver driver;
	ConfigFileReader prop;
	static String url=null;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[@class='ico-login']")		
	WebElement LoginBtn;
	
	@FindBy(xpath = "//input[@class='email']")		
	WebElement userEmailId;
		
	@FindBy(xpath="//input[@class='password']")
	WebElement userPW;
	
	@FindBy(xpath="//input[@value='Log in']")
	WebElement singInBtn;

	
	public WebDriver launchDWS(String url) {
		driver.get(url);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("document.body.style.zoom = '80%'");
		waitForAngularRequestsToFinish();
		return driver;
	}
//	
	public void loginDWS(String emailId, String pw) throws InterruptedException {
		waitForAngularRequestsToFinish();
	//	pw = decodeData(pw);
		
		waitForElementToBeClickableThenClick(LoginBtn);
	//	LoginBtn.click();
		sleepMin();
		
		userEmailId.sendKeys(emailId);
		userPW.sendKeys(pw);
		waitForElementToBeClickableThenClick(singInBtn);
	//	singInBtn.click();
		waitForAngularRequestsToFinish();
		String currentUrl= driver.getCurrentUrl();
	}

}
