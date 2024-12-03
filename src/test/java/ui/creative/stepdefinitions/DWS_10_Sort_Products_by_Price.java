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

public class DWS_10_Sort_Products_by_Price extends BaseTest {

	TestSetup testSetup;

	public DWS_10_Sort_Products_by_Price(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}


	@Then("I Select the Sorting Option Price Low to High")
	public void i_select_the_sorting_option_price_low_to_high() {


		try {
			test = logInfo.get().createNode("I Select the Sorting Option Price Low to High");
			reportInfo("Sort Prize Low TO High");
			testSetup.pageObjectManager.getJewelryPage().SortPriceLowToHigh();
			reportScreenshot(testSetup.driver);
			reportInfo("I Validate All the Products Orderded Low to high");
			boolean LowToHighStatus = testSetup.pageObjectManager.getJewelryPage().ValidatePriceOrderedLowToHigh();
			
			if(LowToHighStatus==true) {
				reportPass("Sort By Low To High ","Products Price SuccessFully arranged to Low to High","Products Price SuccessFully arranged to Low to High");
				reportScreenshot(testSetup.driver);
			}else {
				reportFail("Sort By Low To High","Products Price arranged SuccessFully to Low to High","Products Price NOT arranged SuccessFully to Low to High");
				reportScreenshot(testSetup.driver);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}




	@Then("I Select the Sorting Option Price High to Low")
	public void i_select_the_sorting_option_price_high_to_low() {

		try {
			test = logInfo.get().createNode("I Select the Sorting Option Price High to Low");
			reportInfo("Sort Prize High TO Low");
			testSetup.pageObjectManager.getJewelryPage().SortPriceHighToLow();
			reportScreenshot(testSetup.driver);
			boolean LowToHighStatus = testSetup.pageObjectManager.getJewelryPage().ValidatePriceOrderedHighToLow();
			
			reportInfo("I Validate Product arranged SuccessFully to High to Low");
			if(LowToHighStatus==true) {
				reportPass("Sort By High to Low","Products Price SuccessFully arranged to High to Low","Products Price aSuccessFully arranged  to High to Low");
				reportScreenshot(testSetup.driver);
			}else {
				reportFail("Sort By High to Low","Products Price SuccessFully arranged to High to Low","Products Price NOT SuccessFully arranged  to High to Low");
				reportScreenshot(testSetup.driver);
			} 




		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}

	@Then("I Select the Sorting Option Name A to Z")
	public void i_select_the_sorting_option_name_a_to_z() {
		
		try {
			test = logInfo.get().createNode("I Select the Sorting Option Name A to Z");
			reportInfo("Sort Product Names A to Z");
			testSetup.pageObjectManager.getJewelryPage().SortAtoZ();
			reportScreenshot(testSetup.driver);
			boolean AtoZStatus = testSetup.pageObjectManager.getJewelryPage().ValidateProductNamesAtoZ();
	
			reportInfo("I validate Products Names SuccessFully Ordered to A to Z");
			if(AtoZStatus==true) {
				reportPass("Sort By A to Z","Products Names SuccessFully arranged  to A to Z","Products Names SuccessFully arranged  to A to Z");
				reportScreenshot(testSetup.driver);
			}else {
				reportFail("Sort By A to Z","Products Names SuccessFully arranged  to A to Z","Products Names Not SuccessFully arranged to A to Z");
				reportScreenshot(testSetup.driver);
			} 

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}

	@Then("I Select the Sorting Option Name Z to A")
	public void i_select_the_sorting_option_name_z_to_a() {
		
		try {
			test = logInfo.get().createNode("I Select the Sorting Option Name Z to A");
			reportInfo("Sort Product Names Z to A");
			testSetup.pageObjectManager.getJewelryPage().SortZtoA();
			reportScreenshot(testSetup.driver);
			boolean ZtoAStatus = testSetup.pageObjectManager.getJewelryPage().ValidateProductNamesZtoA();
			
			reportInfo("I validate Products Names SuccessFully Ordered to Z to A");
		   
			if(ZtoAStatus==true) {
				reportPass("Sort By Z to A","Products Names SuccessFully arranged to Z to A","Products Names SuccessFully arranged to Z to A");
				reportScreenshot(testSetup.driver);
			}else {
				reportFail("Sort By Z to A","Products Names SuccessFully arranged to Z to A","Products Names Not SuccessFully arranged to Z to A");
				reportScreenshot(testSetup.driver);
			} 
	

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}
	

}
