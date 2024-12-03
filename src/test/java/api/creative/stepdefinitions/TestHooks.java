package api.creative.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import api.creative.testcomponents.APIBaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TestHooks extends APIBaseClass {
	public TestHooks() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Before
	public void before(Scenario scenario) throws IOException, AWTException {
	   
	    this.scenario = scenario;
	    test = extent.createTest(scenario.getName());
	    String []api =scenario.getName().split(" ");
	    test.assignCategory(api[0]);
	    logInfo.set(test);
		System.out.println("Scenario : "+scenario.getName());
		
		softAssert = new SoftAssert();

	}
	
	@After
	public void after() {
		System.out.println("Status : "+test.getStatus());
		softAssert.assertAll();
		extent.flush();

	}
	
	
}
