package api.creative.testcomponents;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ui.creative.testcomponents.JsonFile;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class APIBaseClass {

	public FileInputStream fis;
	public static Properties prop;
	public static ExtentSparkReporter report = null;
	public static ExtentReports extent = null;
	public ExtentTest test = null;
	public static String dirName=null;
	public static String baseURI=null;
	public static String token=null;
	public static String bearertoken=null;

	public static String username=null;
	public static String password=null;
	public 	static Scenario scenario;
	public static ThreadLocal<ExtentTest> logInfo = new ThreadLocal<ExtentTest>(); // Thread safe
	public static RequestSpecification req = null;
	public static ResponseSpecification res = null;
	public static PrintStream log;
	public static String dbconnection=null;
	public static String dbusername=null;
	public static String dbpassword=null;
	public static String TestDataPath=null;
	public static SoftAssert softAssert = null;
	public static String env =null;
	
	public APIBaseClass() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/GlobalData.properties");
		prop.load(fis);
		dbconnection=prop.getProperty("dbconnection");
		dbusername=prop.getProperty("dbusername");
		dbpassword=prop.getProperty("dbpassword");
	}
	
	public static void setUpTestDataEnvironment() {
		if(env.equalsIgnoreCase("TEST")) {
			TestDataPath = "/src/test/java/api/creative/testdata/test/testdata.xlsx";
		}
		else if(env.equalsIgnoreCase("DEV")) {
			TestDataPath = "/src/test/java/api/creative/testdata/dev/testdata.xlsx";
		}
		else if(env.equalsIgnoreCase("UAT")){
			TestDataPath = "/src/test/java/api/creative/testdata/uat/testdata.xlsx";
		}
	}
	
	public void setUpEnvironment() {
		if(env == null)
			env = "TEST";
	}
	
	public void setupConfiguration() throws Exception {
		
		Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("ApiConfiguration", "Api_config");
		baseURI=testData.get("baseURI");
	}
	
	public static String getTimeStamp() {
		Locale locale = new Locale("en", "UK");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "dd-MMM-HH_mm_ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);

		String timestamp = simpleDateFormat.format(new Date());
		return timestamp;
	}
	
	
	
	public static ExtentReports setUp() throws IOException {
		String fileName = "Run_" +getTimeStamp();
		String reportLocation = System.getProperty("user.dir") + "/API_Reports/"+fileName+".html";
		report = new ExtentSparkReporter(reportLocation);	
		report.config().setEncoding("utf-8");
		System.out.println("Extent Report location initialized . . .");


		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "Creative API Testing");
		extent.setSystemInfo("Environment URL", "https://demowebshop.tricentis.com/");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");
		JsonFile.writeDirectoryName(fileName);
		return extent;
	}
	
	
	
	public void reportInfo(String msg) {
		test.pass(msg);
	}
	public void reportPass(String title,String exp, String act) {
		String message = "<b>" +"Expected " +  title+" : "+"</b>"  + "<font color=" + "green>" +exp + "</font>"+ "\t" + "<b>" + "Actual " +title+ " : "+"</b>"  + "<font color=" + "green>" +act+ "</font>";
		test.pass(message);
	}
	public void reportFail(String title,String exp, String act) {
		String message = "<b>" +"Expected " +  title+" : "+"</b>"  + "<font color=" + "red>" +exp + "</font>"+ "\t" + "<b>" + "Actual " +title+ " : "+"</b>"  + "<font color=" + "red>" +act+ "</font>";
		test.fail(message);
		softAssert.assertEquals(exp, act);
	}
	
	public void reportPayload(String msg) {
		Markup m = MarkupHelper.createCodeBlock(msg, CodeLanguage.JSON);
		test.info(m);
	}
	public void validateField(String title,String expected, String actual) {
		
		if(expected.equals(actual))
			reportPass(title,expected, actual);
		else {
			reportFail(title,expected, actual);
			
		}
			
	}
		public void validateContent(String title,String expected, String actual) {
		
		Boolean flag=true;
		String[] actID=actual.substring(1,actual.length()-1).split(",");
		
		for (String str  : actID) {
			if(!expected.contains(str.trim()))
				flag=false;
		}
		if(flag)
			reportPass(title,expected, actual);
		else 
			reportFail(title,expected, actual);
		
	}
	public void validateNullValues(String title, String actual) {
		
		if(actual==null)
			reportPass(title, "null ", "null");
		else
			reportFail(title, "null ", "null");
	}

	
	
	
	
	private static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}
	
	public static void testStepHandle(String teststatus,ExtentTest extenttest,Throwable throwable) {
		switch (teststatus) {
		case "FAIL":		
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));			
			extenttest.log(Status.FAIL, throwable.fillInStackTrace());
		case "PASS":			
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;
			
		default:
			break;
		}
	}
	
	public static void zoomOut(int s) throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < s; i++) {

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public static void zoomIn(int s) throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < s; i++) {

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}
	
	
}
