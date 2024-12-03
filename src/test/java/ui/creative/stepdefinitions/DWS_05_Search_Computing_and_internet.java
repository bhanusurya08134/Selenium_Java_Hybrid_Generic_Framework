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

public class DWS_05_Search_Computing_and_internet extends BaseTest {

	TestSetup testSetup;

	public DWS_05_Search_Computing_and_internet(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Given("I search and validate Computing and internet {string}")
	public void i_search_and_validate_computing_and_internet(String tcid) {
		try {
			test = logInfo.get().createNode("I search and validate Computing and internet");

			Map<String, String> testData = ExcelFileReader.getDataInMap("CustomerDetails", tcid);
			System.out.println(testData);
			
//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
//			Map<String, String> testData = ExcelFileReader.getDataInMap("module1", fieldId, tcid);
			reportInfo(testData.toString());

			String tittle = testSetup.pageObjectManager.getDemoWebShopPage().searchComputingandinternet(testData);
			// reportScreenshot(testSetup.driver);

			if(tittle.equalsIgnoreCase(testData.get("Search_From_store"))) {
				reportPass("Book Tittle", testData.get("Search_From_store"), tittle);
				reportScreenshot(testSetup.driver);
				reportInfo("search and validate "+testData.get("Search_From_store")+" with name  "+tittle+" successfully");
			}
			else {
				reportFail("Book Tittle", testData.get("Search_From_store"), tittle);
				reportScreenshot(testSetup.driver);
			}



		} catch (Exception e) {
			e.printStackTrace();
			testStepHandle("FAIL", testSetup.driver, test, e);
		}
	}

}
