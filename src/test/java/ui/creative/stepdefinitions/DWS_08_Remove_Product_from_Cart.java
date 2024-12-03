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

public class DWS_08_Remove_Product_from_Cart extends BaseTest {

	TestSetup testSetup;

	public DWS_08_Remove_Product_from_Cart(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}


	@Then("I Edit the User Profile in my account page With testData {string}")
	public void i_edit_the_user_profile_in_my_account_page(String tcid) {
		try {
			test = logInfo.get().createNode("I Edit the User Profile in my account page");

//			Map<String, String> testData = ExcelFileReader.getDataInMap("MyAccountPage", tcid);
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("MyAccountPage", tcid);
			reportInfo(testData.toString());

			reportInfo("I edit the Phone Number in user details");

			testSetup.pageObjectManager.getMyAccoutPage().EditTheCustomerDetails(testData);
			reportScreenshot(testSetup.driver);

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();

		}

	}

	@Then("I Validate the User Profile changes are saved successfully With testData {string}")
	public void i_validate_the_user_profile_changes_are_saved_successfully(String tcid) {

		try {
			test = logInfo.get().createNode("I Validate the User Profile changes are saved successfully");

			Map<String, String> testData = ExcelFileReader.getDataInMap("MyAccountPage", tcid);
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
//			Map<String, String> testData = ExcelFileReader.getDataInMap("module2", fieldId, tcid);
			reportInfo(testData.toString());

			reportInfo("I validate the phone number changed successfully");

			String ActualMblNumber = testSetup.pageObjectManager.getMyAccoutPage().ValidateCustomerDetailsAfterEdit();
			if(ActualMblNumber.equals(testData.get("EditedPhoneNumber"))) {
				reportPass("Phone Number",testData.get("EditedPhoneNumber"), ActualMblNumber);
				reportInfo("Phone number changed successfully");
				reportScreenshot(testSetup.driver);
			}
			else {
				reportPass("Phone Number",testData.get("EditedPhoneNumber"), ActualMblNumber);
				reportInfo("Phone number not changed successfully");
			}	

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();

		}

	}


	

}
