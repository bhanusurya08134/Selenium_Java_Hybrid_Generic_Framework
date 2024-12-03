package api.creative.testrunners;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import api.creative.testcomponents.APIBaseClass;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src\\test\\java\\api\\creative\\features"},tags = "@DLL_Upload", glue = "api.creative.stepdefinitions", monochrome = true,plugin = {"html:target/cucumber.html"})
public class TestRunnerTEST extends AbstractTestNGCucumberTests{
	@BeforeClass
	public void beforeCalss() throws AWTException, IOException {

}

}
 
 