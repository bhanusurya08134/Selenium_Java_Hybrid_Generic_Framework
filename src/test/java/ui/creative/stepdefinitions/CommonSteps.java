package ui.creative.stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import ui.creative.testcomponents.BaseTest;
import ui.creative.testcomponents.ExcelFileReader;
import ui.creative.testcomponents.TestSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps extends BaseTest {

	TestSetup testSetup;

	public CommonSteps(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Given("I login to Demo Web Shop application with valid credentials")
	public void i_login_to_demo_web_shop_application_with_valid_credentials() {
		try {
			test = logInfo.get().createNode("I login to LM application with valid credentials");
			
			Map<String, String> testData = ExcelFileReader.getDataInMap("ENVIRONMENT", env);
			
			testSetup.pageObjectManager.getLandingPage().launchDWS(testData.get("URL"));
			testSetup.pageObjectManager.getLandingPage().loginDWS(testData.get("USERNAME"), testData.get("PASSWORD"));
			Thread.sleep(2000);
			//zoomOut(3);

			if (testSetup.pageObjectManager.getHomePage().currentUserText().equals(testData.get("USERNAME"))) {
				reportPass("Current User", testData.get("USERNAME"),
						testSetup.pageObjectManager.getHomePage().currentUserText());
				reportInfo("Usrer " + testData.get("USERNAME") + " logged in successfully");
				reportScreenshot(testSetup.driver);
			}

			else
				reportFail("Current User", "Automation user",
						testSetup.pageObjectManager.getHomePage().currentUserText());
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}

	}
		
	@When("I close the browser")
	public void i_close_the_browser() throws IOException, InterruptedException {
		try {
			test = logInfo.get().createNode("I close the browser");
			reportInfo("Browser closed successfully");
		//	testSetup.baseTest.intializeDriver().close();
			testSetup.baseTest.closeBrowser();
		//	testSetup.pageObjectManager.getLandingPage().closeBrowser();
			
		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
		}

	}
	
	@Then("I navigate to Books page")
	public void i_navigate_to_books_page() {
		try {
			test = logInfo.get().createNode("I navigate to Books page");
			testSetup.pageObjectManager.getHomePage().navigateToBooksTab();
			 reportScreenshot(testSetup.driver);
			reportInfo("Navigate to Books page successfully");
		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
	}
	
	
	@Then("I navigate to Computers page")
	public void i_navigate_to_computers_page() {
		try {
			test = logInfo.get().createNode("I navigate to Computers page");
			testSetup.pageObjectManager.getHomePage().navigateToComputersTab();
			 reportScreenshot(testSetup.driver);
			reportInfo("Navigate to Computers page successfully");
		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
	}
	
	

	@Then("I navigate to My account page")
	public void i_navigate_to_my_account_page() {
		try {
			test = logInfo.get().createNode("I navigate to My account page");
			testSetup.pageObjectManager.getHomePage().clickmyaccount();
			reportScreenshot(testSetup.driver);
			reportInfo("Navigate to My account page successfully");

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}
	
	@Then("I navigate to Jewelry page")
	public void i_navigate_to_jewelry_page() {
		try {
			test = logInfo.get().createNode("I navigate to Jewelry page");
			testSetup.pageObjectManager.getHomePage().navigateJewelryPage();
			reportScreenshot(testSetup.driver);
			reportInfo("Navigate to Jewelry page successfully");

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	@Then("I navigate to Wishlist page")
	public void i_navigate_to_wishlist_page() {
		try {
			test = logInfo.get().createNode("I navigate to Wishlist page");
			testSetup.pageObjectManager.getHomePage().navigateToWishListPage();
			reportScreenshot(testSetup.driver);
			reportInfo("Navigate to Wishlist page successfully");

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}
	



	

}
