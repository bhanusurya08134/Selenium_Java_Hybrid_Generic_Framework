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

public class DWS_03_Order_Computer extends BaseTest {

	TestSetup testSetup;

	public DWS_03_Order_Computer(TestSetup testSetup) throws IOException {
		this.testSetup = testSetup;
	}

	@Then("I select Computer and add to cart {string}")
	public void i_select_computer_and_add_to_cart(String tcid) {
		try {
			test = logInfo.get().createNode("I select Computer and add to cart");

//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("CustomerDetails", tcid);
			reportInfo(testData.toString());

			String confirmationMessage = testSetup.pageObjectManager.getDemoWebShopPage().selectComputerAndAddToCart(testData);

			if(confirmationMessage.equalsIgnoreCase("The product has been added to your shopping cart"))
				reportPass("Confirmation Message", "The product has been added to your shopping cart", confirmationMessage);
			else
				reportFail("Confirmation Message", "The product has been added to your shopping cart", confirmationMessage);

			reportScreenshot(testSetup.driver);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}

	@Then("I order the Computer {string}")
	public void i_order_the_computer(String tcid) {
		try {
			test = logInfo.get().createNode("I order the Computer");

//			String fieldId = tcid.split("_")[0]+"_"+tcid.split("_")[1] ;
			Map<String, String> testData = ExcelFileReader.getDataInMap("CustomerDetails", tcid);
			reportInfo(testData.toString());

			testSetup.pageObjectManager.getBooksPage().checkOutWithCustomerInfirmation(testData);
			reportScreenshot(testSetup.driver);

			String[] orderConfirm = testSetup.pageObjectManager.getBooksPage().orderBookConfirmation(testData);

			if(orderConfirm[0].equalsIgnoreCase("Your order has been successfully processed!")) {
				reportPass("Order Status", "Your order has been successfully processed!", orderConfirm[0]);
				reportInfo("'"+testData.get("ComputerName2")+"' ordered successfully with "+orderConfirm[1]);
			}
			else {
				reportFail("Order Status", "Your order has been successfully processed!", orderConfirm[0]);
			}

			reportScreenshot(testSetup.driver);
		}
		catch (Exception e) {
			testStepHandle("FAIL", testSetup.driver, test, e);
			e.printStackTrace();
		}
	}
}
