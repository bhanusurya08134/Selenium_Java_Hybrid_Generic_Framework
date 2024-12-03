package api.creative.testcomponents;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Listeners extends APIBaseClass implements ITestListener{
	
	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static ExtentReports extent;

	public void onTestStart(ITestResult result) {
	
		
	}

	public void onTestSuccess(ITestResult result) {

		
		
	}

	public void onTestFailure(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		String environment = System.getProperty("env");
		env = environment;
		//setUp Environment
		setUpEnvironment();
		//setUpTestEnvironment
		setUpTestDataEnvironment();
		try {
			//setUpApiConfiguration
			setupConfiguration();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {	
			
		extent= APIBaseClass.setUp();
		
		}catch (IOException e) {
		e.printStackTrace();
		}
	
		
	}
	
	public void onFinish(ITestContext context) {
		
		
		APIBaseClass.extent.flush();	
		System.out.println("Execution completed ...");
		
		System.out.println("Report Generated  Successfully. . .");	
		
	}
	

}
