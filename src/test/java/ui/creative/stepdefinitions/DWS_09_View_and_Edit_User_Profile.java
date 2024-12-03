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

public class DWS_09_View_and_Edit_User_Profile extends BaseTest {

	TestSetup testSetup;

	public DWS_09_View_and_Edit_User_Profile(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}
	@Then("I select the Multiple Products With testData {string}")
	public void i_select_the_multiple_products(String tcid) {

		try {
			test = logInfo.get().createNode("I select the Multiple Products");

//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
//			Map<String, String> testData = ExcelFileReader.getDataInMap("module2", fieldId, tcid);
//			reportInfo(testData.toString());
			reportInfo("I Added  Multiple Books to ShopingCart");
			testSetup.pageObjectManager.getBooksPage().AddMultipleBooks();
			reportScreenshot(testSetup.driver);
			testSetup.pageObjectManager.getBooksPage().SelectShopingCart();
			reportScreenshot(testSetup.driver);

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	@Then("I Remove any one of the product in shopping cart page With testData {string}")
	public void i_remove_any_one_of_the_product_in_shopping_cart_page(String tcid) {

		try {
			test = logInfo.get().createNode("I Remove any one of the product in shopping cart page");

//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
//			Map<String, String> testData = ExcelFileReader.getDataInMap("module2", fieldId, tcid);
//			reportInfo(testData.toString());
			reportInfo("I Remove One of the Books In ShopingCart");
			testSetup.pageObjectManager.getBooksPage().removeBookinShopingCart();
			reportScreenshot(testSetup.driver);

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}

	@Then("I verify the Product Is removed in shopping cart page With testData {string}")
	public void i_verify_the_product_is_removed_in_shopping_cart_page(String tcid) {

		try {
			test = logInfo.get().createNode("I verify the Product Is removed in shopping cart page");

//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("BooksPage", tcid);
			reportInfo(testData.toString());
			reportInfo("I Verify Book is Removed in ShopingCart");
			String status = testSetup.pageObjectManager.getBooksPage().verifyProductIsRemoved(testData);
			if(status.equals("Book Removed Successfully")) {
				reportPass("Status","FictionBook Removed Successfully",testData.get("RemovedBook")+ status);
				reportScreenshot(testSetup.driver);
			}else {
				reportFail("Status","Book Not Removed Successfully", status);
			}
			
			testSetup.pageObjectManager.getHomePage().clearCart();

		} catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();


		}

	}



	

}
