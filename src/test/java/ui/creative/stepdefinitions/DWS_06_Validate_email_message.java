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

public class DWS_06_Validate_email_message extends BaseTest {

	TestSetup testSetup;

	public DWS_06_Validate_email_message(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Then("I select Jewel and add to wish list with testdata {string}")
	public void i_select_jewel_and_add_to_wish_list_with_testdata(String tcid) {
		try {
			test = logInfo.get().createNode("I select Jewel and add to wish list with testdata");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("WishlistPage", tcid);
			reportInfo(testData.toString());
			String AllertMessage = testSetup.pageObjectManager.getJewelryPage().selectJewelAndAddToWishList(testData);
			reportScreenshot(testSetup.driver);
			if(AllertMessage.equalsIgnoreCase("The product has been added to your wishlist"))
				reportPass("Confirm message", "The product has been added to your wishlist", AllertMessage);
			else
				reportFail("Confirm message", "The product has been added to your wishlist", AllertMessage);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	

	@Then("I validate Jewel in wish list with testdata {string}")
	public void i_validate_jewel_in_wish_list_with_testdata(String tcid) {
		try {
			test = logInfo.get().createNode("I validate Jewel in wish list with testdata");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("WishlistPage", tcid);
			reportInfo(testData.toString());
			String ProductInWIshList = testSetup.pageObjectManager.getWishListPage().validateJewelInWishList(testData);
			reportScreenshot(testSetup.driver);
			if(ProductInWIshList.equalsIgnoreCase(testData.get("JewelName")))
				reportPass("Jewel", testData.get("JewelName"), ProductInWIshList);
			else
				reportFail("Jewel", testData.get("JewelName"), ProductInWIshList);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	@Then("I send mail to friend Email about product with testdata {string}")
	public void i_send_mail_to_friend_email_about_product_with_testdata(String tcid) {
		try {
			test = logInfo.get().createNode("I send mail to friend Email about product with testdata");
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("WishlistPage", tcid);
			reportInfo(testData.toString());
			testSetup.pageObjectManager.getWishListPage().email_a_Friend(testData);
			reportScreenshot(testSetup.driver);
			String confirmTextMessage = testSetup.pageObjectManager.getWishListPage().clickSendEmail(testData);
			reportScreenshot(testSetup.driver);
			if(confirmTextMessage.equalsIgnoreCase("Your message has been sent."))
				reportPass("Confirm message", "Your message has been sent.", confirmTextMessage);
			else
				reportFail("Confirm message", "Your message has been sent.", confirmTextMessage);
			testSetup.pageObjectManager.getWishListPage().removeProductFromWishlist(testData);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}



}
