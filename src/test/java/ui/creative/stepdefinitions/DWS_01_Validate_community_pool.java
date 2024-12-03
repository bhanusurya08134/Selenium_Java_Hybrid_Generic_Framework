package ui.creative.stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.aventstack.extentreports.model.Test;

import ui.creative.testcomponents.BaseTest;
import ui.creative.testcomponents.ExcelFileReader;
import ui.creative.testcomponents.TestSetup;

public class DWS_01_Validate_community_pool extends BaseTest {

	TestSetup testSetup;

	public DWS_01_Validate_community_pool(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Given("I open the Demo Web Shop application")
	public void i_open_the_demo_web_shop_application() {
		try {
			test = logInfo.get().createNode("I open the Demo Web Shop application");

			Map<String, String> testData = ExcelFileReader.getDataInMap("ENVIRONMENT", env);
			testSetup.pageObjectManager.getLandingPage().launchDWS(testData.get("URL"));

			String currentWebPage = testSetup.pageObjectManager.getHomePage().currentWebPage();
			if (currentWebPage.equalsIgnoreCase("Demo Web Shop")) {
				reportPass("Webpage", "Demo Web Shop", currentWebPage);
				reportInfo(currentWebPage+" open successfully");
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

	@Then("I validate the community pool")
	public void i_validate_the_community_pool() {
		try {
			test = logInfo.get().createNode("I validate the community pool");

			String voteMessage = testSetup.pageObjectManager.getDemoWebShopPage().validateCommunityPool();

			if(voteMessage.equalsIgnoreCase("Only registered users can vote.")) {
				reportPass("Vote message", "Only registered users can vote.", voteMessage);
				reportInfo("I validate community pool successfully");
			}
			else
				reportFail("Vote message", "Only registered users can vote.", voteMessage);

			reportScreenshot(testSetup.driver);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

}
