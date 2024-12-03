package ui.creative.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.creative.componentgroups.ReusableLibrary;

import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ProductReviewPage extends ReusableLibrary
{
	protected static final Component Content = null;

	WebDriver driver;

	public ProductReviewPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@class='review-title']")
	WebElement reviewTitleTextBox;

	@FindBy(xpath = "//textarea[@class='review-text']")
	WebElement reviewTextBox;

	@FindBy(xpath = "//input[@value='Submit review']")
	WebElement submitReviewButton;

	@FindBy(xpath = "//div[@class='result']")
	WebElement submitResult;


	public void writeReviewAndRating(Map<String, String> testData) throws InterruptedException {
		waitForElementToBeClickableThenClearThenSendkeys(reviewTitleTextBox, testData.get("ReviewTitle"));

		waitForElementToBeClickableThenClearThenSendkeys(reviewTextBox, testData.get("ReviewText"));

		String rating = testData.get("Rating");
		sleepMin();
		driver.findElement(By.xpath("//div[@class='review-rating']//input[@value='"+rating+"']")).click();

//		scrollToTop();
	}


	public String clickSubmitReviewOption() {
		waitForElementToBeClickableThenClick(submitReviewButton);
		waitForAngularRequestsToFinish();
		String confirmationMessage = submitResult.getText();
		return confirmationMessage;
	}


	public String validateSubmittedReview(Map<String, String> testData) throws InterruptedException {
		scrollToBottom();
		sleepMin();
		WebElement actualReviewTitle = driver.findElement(By.xpath("(//div[@class='product-review-item'])[last()]//div[@class='review-title']/strong[text()='"+testData.get("ReviewTitle")+"']"));
		
		return actualReviewTitle.getText();
	}




}
