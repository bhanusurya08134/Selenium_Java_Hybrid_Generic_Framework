package ui.creative.stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ui.creative.testcomponents.BaseTest;
import ui.creative.testcomponents.ExcelFileReader;
import ui.creative.testcomponents.TestSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DWS_07_Perform_a_Product_Review extends BaseTest {

	TestSetup testSetup;

	public DWS_07_Perform_a_Product_Review(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Then("I search a product with testData {string}")
	public void i_search_a_product_with_test_data(String tcid) {
		try {
			test = logInfo.get().createNode("I search a product with testData");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("ReviewsPage", tcid);
			reportInfo(testData.toString());
			
			String ActualProductName = testSetup.pageObjectManager.getHomePage().searchProduct(testData);
			reportScreenshot(testSetup.driver);
			if(ActualProductName.equalsIgnoreCase(testData.get("ReviewProduct")))
				reportPass("Product name", testData.get("ReviewProduct"), ActualProductName);
			else
				reportFail("Product name", testData.get("ReviewProduct"), ActualProductName);
			
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	@Then("I click on Add review and write and submit review with testdata {string}")
	public void i_click_on_add_review_and_write_and_submit_review_with_testdata(String tcid) {
		try {
			test = logInfo.get().createNode("I click on Add review and write and submit review with testdata");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("ReviewsPage", tcid);
			reportInfo(testData.toString());
			
			testSetup.pageObjectManager.getHomePage().clickOnAddReviewToNavigateReviewsPage(testData);
			reportScreenshot(testSetup.driver);
			testSetup.pageObjectManager.getProductReviewPage().writeReviewAndRating(testData);
			reportScreenshot(testSetup.driver);
			String confirmationMessage = testSetup.pageObjectManager.getProductReviewPage().clickSubmitReviewOption();
			reportScreenshot(testSetup.driver);
			
			if(confirmationMessage.equalsIgnoreCase("Product review is successfully added."))
				reportPass("Review Submit message", "Product review is successfully added.", confirmationMessage);
			else
				reportFail("Review Submit message", "Product review is successfully added.", confirmationMessage);
		}
		catch (Exception e) {
			e.printStackTrace();
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
	}

	@Then("I validate submitted review with testdata {string}")
	public void i_validate_submitted_review_with_testdata(String tcid) {
		try {
			test = logInfo.get().createNode("I validate submitted review with testdata");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("ReviewsPage", tcid);
			reportInfo(testData.toString());
			
			String actualReviewTitle = testSetup.pageObjectManager.getProductReviewPage().validateSubmittedReview(testData);
			reportScreenshot(testSetup.driver);
			
			if(actualReviewTitle.equalsIgnoreCase(testData.get("ReviewTitle")))
				reportPass("Review title", testData.get("ReviewTitle"), actualReviewTitle);
			else
				reportFail("Review title", testData.get("ReviewTitle"), actualReviewTitle);
	
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	



}
