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

public class DWS_04_Validate_customer_details extends BaseTest {

	TestSetup testSetup;

	public DWS_04_Validate_customer_details(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}
	
	@Then("I validate the Customer information {string}")
	public void i_validate_the_customer_information(String tcid) throws Exception {
		try {
			test = logInfo.get().createNode("I validate the Customer information");
			
			Map<String, String> testData = ExcelFileReader.getDataInMap("CustomerDetails", tcid);
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
//			Map<String, String> testData = ExcelFileReader.getDataInMap(excelModule, fieldId, tcid);
			reportInfo(testData.toString());

			String status = testSetup.pageObjectManager.getDemoWebShopPage().gendervalidate(testData);
		
			if(status.equalsIgnoreCase("true")) {
				reportPass("Gender:Male", "true", status);
				reportScreenshot(testSetup.driver);
				reportInfo("Gender 'Male' validate successfully");
			}
			else {
				reportFail("Gender:Male", "true", status);
			}
			
			
			String text = testSetup.pageObjectManager.getDemoWebShopPage().Firstnamevalidation();
			
			System.out.println(text.equalsIgnoreCase(testData.get("FirstName"))+"\n"+text+"\n"+testData.get("FirstName"));
		
			if(text.equalsIgnoreCase(testData.get("FirstName"))) {
				reportPass("FirstName", testData.get("FirstName"), text);
				reportScreenshot(testSetup.driver);
				reportInfo("FirstName validation with name '"+text+"' successfully");
			}
			else {
				reportFail("FirstName", testData.get("FirstName"), text);
			}
			
			String text1 = testSetup.pageObjectManager.getDemoWebShopPage().LastNamevalidation();
		
			if(text1.equalsIgnoreCase(testData.get("LastName"))) {
				reportPass("LastName", testData.get("LastName"), text1);
				reportScreenshot(testSetup.driver);
				reportInfo("LastName validation with name '"+text1+"' successfully");
			}
			else {
				reportFail("LastName", testData.get("LastName"), text1);
			}
			
			
			String Mail = testSetup.pageObjectManager.getDemoWebShopPage().Mailvalidation();
		
			if(Mail.equalsIgnoreCase(testData.get("EmailId"))) {
				reportPass("Mail", testData.get("EmailId"), Mail);
				reportScreenshot(testSetup.driver);
				reportInfo("Mail validation with '"+Mail+"' successfully");
			}
			else {
				reportFail("Mail", testData.get("EmailId"), Mail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
		
	}
	

}
