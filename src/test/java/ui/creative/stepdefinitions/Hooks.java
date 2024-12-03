package ui.creative.stepdefinitions;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.zaproxy.clientapi.core.ClientApiException;

import ui.creative.testcomponents.BaseTest;
import ui.creative.testcomponents.ReadXL;
import ui.creative.testcomponents.SecurityTester;
import ui.creative.testcomponents.TestSetup;
import ui.creative.testcomponents.ThreadLocalManager;
import ui.creative.testcomponents.VideoRecorder;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks extends BaseTest {

	public Hooks() throws IOException{
		super();
	}
	
	@Before
	public void TestConfigSetup(Scenario scenario) throws Exception {
		
		this.scenario = scenario;
		test = extent.createTest(scenario.getName());
	    logInfo.set(test);
//	    softAssert = new SoftAssert();
	    softAssertThreadLocal.set(new SoftAssert());
//for parallel execution------------------------
	    
	    ThreadLocalManager.setScenarioName(scenario.getName());
//	    scenarioName = scenario.getName();
	    
	    String []module =scenario.getName().split(" ");
	    test.assignCategory(module[0]);
	}
	
	@After
	public void teardown(Scenario scenario) throws InterruptedException {
//		softAssert.assertAll();
		
		if (performSecurityTest==true) {
			try {
				SecurityTester.generateSecurityTestReport();
			} catch (ClientApiException e) {
				e.printStackTrace();
			}
		}
		softAssertThreadLocal.get().assertAll();
	}
}
	

