package ui.creative.pageobjects;

import java.util.*;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

public class JewelryPage extends ReusableLibrary
{
	WebDriver driver;

	public JewelryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//input[@value='Add to wishlist']")
	WebElement AddToWishListButton;
	
	@FindBy(xpath="//select[@id='products-orderby']")
	WebElement SortBy;

	@FindBy(xpath="//*[text()='Price: Low to High']")
	WebElement LowToHigh;

	@FindBy(xpath="//*[@class='price actual-price']")
	List<WebElement> AllProducts;

	@FindBy(xpath="//*[text()='Price: High to Low']")
	WebElement HighToLow;

	@FindBy(xpath="//h2[@class='product-title']//a")
	List<WebElement> AllProductNames;

	@FindBy(xpath="//*[text()='Name: A to Z']")
	WebElement AtoZ;

	@FindBy(xpath="//*[text()='Name: Z to A']")
	WebElement ZtoA;



	public void SortPriceLowToHigh() throws InterruptedException {
		waitForElementToBeClickableThenClick(SortBy);
		waitForElementToBeClickableThenClick(LowToHigh);
		sleepAvg();
	}


	public void SortPriceHighToLow() throws InterruptedException {
		waitForElementToBeClickableThenClick(SortBy);
		waitForElementToBeClickableThenClick(HighToLow);
		sleepAvg();
	}

	public void SortAtoZ() throws InterruptedException{
		waitForElementToBeClickableThenClick(SortBy);
		waitForElementToBeClickableThenClick(AtoZ);
		sleepAvg();

	}

	public void SortZtoA() throws InterruptedException{
		waitForElementToBeClickableThenClick(SortBy);
		waitForElementToBeClickableThenClick(ZtoA);
		sleepAvg();

	}

	public boolean ValidatePriceOrderedLowToHigh() {

		List<String> pricesAsString = new ArrayList();
		for (WebElement priceElement : AllProducts) {
			pricesAsString.add(priceElement.getText());
		}

		List<Double> pricesAsDouble = new ArrayList();
		for (String price : pricesAsString) {
			double numericPrice = Double.parseDouble(price.replaceAll("[^\\d.]", ""));
			pricesAsDouble.add(numericPrice);
		}
		List<Double> Actual = pricesAsDouble;

		Collections.sort(pricesAsDouble);
		List<Double> sortedPrices = new ArrayList();
		for (double price : pricesAsDouble) {
			sortedPrices.add(price);
		}
		List<Double> Expected = sortedPrices;

		boolean areEqual = true;

		for (int i = 0; i < Expected.size(); i++) {
			Double expectedValue = Expected.get(i);
			Double actualValue = Actual.get(i);

			if (expectedValue==(actualValue)) {
				return true;
			} else {
				return false;
			}
		}
		return areEqual;
	}





	public boolean ValidatePriceOrderedHighToLow() {

		List<String> pricesAsString = new ArrayList();
		for (WebElement priceElement : AllProducts) {
			pricesAsString.add(priceElement.getText());
		}

		List<Double> pricesAsDouble = new ArrayList();
		for (String price : pricesAsString) {
			double numericPrice = Double.parseDouble(price.replaceAll("[^\\d.]", ""));
			pricesAsDouble.add(numericPrice);
		}
		List<Double> Actual = pricesAsDouble;

		Collections.sort(pricesAsDouble);

		List<Double> sortedPrices = new ArrayList();
		for (double price : pricesAsDouble) {
			sortedPrices.add(price);
		}

		Collections.reverse(sortedPrices);

		List<Double> sortedPricesAfterReverse = new ArrayList();
		for (double price : sortedPrices) {
			sortedPricesAfterReverse.add(price);
		}

		List<Double> Expected = sortedPricesAfterReverse;
		boolean areEqual = true;

		for (int i = 0; i < Expected.size(); i++) {
			Double expectedValue = Expected.get(i);
			Double actualValue = Actual.get(i);

			if (expectedValue==(actualValue)) {
				return true;
			} else {
				return false;
			}
		}
		return areEqual;
	}

	public boolean ValidateProductNamesAtoZ() {

		List<String> productNames = new ArrayList();
		for (WebElement productElement : AllProductNames) {
			productNames.add(productElement.getText());
		}

		List<String> actual = productNames;

		Collections.sort(productNames);

		List<String> expectedNames = new ArrayList();
		for (String ExpProductNames : productNames) {
			expectedNames.add(ExpProductNames);
		}

		boolean areEqual = true;

		for (int i = 0; i < expectedNames.size(); i++) {
			String expectedValue = expectedNames.get(i);
			String actualValue = actual.get(i);

			if (expectedValue==(actualValue)) {
				return true;
			} else {
				return false;
			}
		}
		return areEqual;




	}

	public boolean ValidateProductNamesZtoA() {

		List<String> productNames = new ArrayList();
		for (WebElement productElement : AllProductNames) {
			productNames.add(productElement.getText());
		}

		List<String> actual = productNames;

		Collections.sort(productNames);

		List<String> sortedProductNames = new ArrayList();
		for (String price : productNames) {
			sortedProductNames.add(price);
		}

		Collections.reverse(productNames);

		List<String> sortedPricesAfterReverse = new ArrayList();
		for (String price : productNames) {
			sortedPricesAfterReverse.add(price);
		}

		List<String> expected = sortedPricesAfterReverse;

		boolean areEqual = true;

		for (int i = 0; i < expected.size(); i++) {
			String expectedValue = expected.get(i);
			String actualValue = actual.get(i);

			if (expectedValue==(actualValue)) {
				return true;
			} else {
				return false;
			}
		}
		return areEqual;

	}

	

	public String selectJewelAndAddToWishList(Map<String, String> testData) throws InterruptedException {
		WebElement selectJewel = driver.findElement(By.xpath("(//div[@class='product-grid']//a[contains(text(),'"+testData.get("JewelName")+"')])"));
		selectJewel.click();
		waitForAngularRequestsToFinish();
		waitForElementToBeClickableThenClick(AddToWishListButton);
		Thread.sleep(1000);
		String AllertMessage = driver.findElement(By.xpath("//p[@class='content']")).getText();

		return AllertMessage;
	}


	

}
