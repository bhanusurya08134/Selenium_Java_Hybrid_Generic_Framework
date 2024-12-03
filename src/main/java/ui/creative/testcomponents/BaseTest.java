package ui.creative.testcomponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.paulhammant.ngwebdriver.NgWebDriver;

import api.creative.testcomponents.ExcelFileReaderAPI;
import ui.creative.pageobjects.LandingPage;
import ui.playarea.ZAPSecurityTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import ui.creative.componentgroups.ConfigFileReader;
import ui.creative.componentgroups.ResponseTags;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

//import io.cucumber.java.Scenario as sc;


/*This class is responsible for loading the configurations from properties files, 
Initializing the WebDriver, Implicit Waits, Extent Reports, and also to create the object of FileInputStream 
which is responsible for pointing towards the file from which the data should be read.*/



public class BaseTest extends SuperTestNG{

        
	public BaseTest() throws IOException {

		prop = new Properties();
		fis = new FileInputStream(propertyFilePath);
		prop.load(fis);
		
		property.set(prop);
		
	}
	
	
	public void setup(String browser) {
//		String browser = context.getCurrentXmlTest().getParameter("browser");
		
      if (browser == null || browser.isEmpty()) {
          throw new IllegalArgumentException("Browser parameter is not provided in the test suite.");
//    	  ThreadLocalManager.setBrowserName(null);
      }
      
      if (browser.equalsIgnoreCase("chrome")) {
      	  ThreadLocalManager.setBrowserName("chrome");

      } else if (browser.equalsIgnoreCase("edge")) {
    	  ThreadLocalManager.setBrowserName("edge");

      }else if (browser.equalsIgnoreCase("firefox")) {
    	  ThreadLocalManager.setBrowserName("firefox");
      }
	}

	public static void innitializePropertyFile() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(propertyFilePath);
		prop.load(fis);
		
		property.set(prop);
		
	}

	public void setupConfiguration() throws Exception {

		Map<String, String> testData = ExcelFileReader.getDataInMap("ApiConfiguration", "Api_config");
		baseURI=testData.get("baseURI");
	}
	
//	@SuppressWarnings("deprecation")
	public WebDriver intializeDriver() throws IOException, InterruptedException {
		
		System.out.println(ThreadLocalManager.getBrowserName());
		
		if(ThreadLocalManager.getBrowserName()==null){
//			browserName = "chrome";  // IF NO ARGUMENT IS PASSED FOR BROWSER, SET HERE DEFAULT BROWSER
			ThreadLocalManager.setBrowserName("chrome");
		}
		

		if (driver == null) {
			//String browserName = System.getProperty("BrowserName") != null ? System.getProperty("BrowserName")
			//		: prop.getProperty("BrowserName");
			
			Thread.sleep(2000);		
			
			if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("edge")) {
				// Edge
				WebDriverManager.edgedriver().setup();

				EdgeOptions opt = new EdgeOptions();

				// Set specific options

				opt.addArguments("--start-maximized");

				opt.addArguments("--disable-gpu");

				opt.addArguments("--disable-dev-shm-usage");

				opt.addArguments("--remote-allow-origins=*");

				opt.setAcceptInsecureCerts(true);

				Map<String, Object> prefs = new HashMap<String, Object>();

				prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "Downloads");

				opt.setExperimentalOption("prefs", prefs);

				driver = new EdgeDriver(opt);
				
			} else if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("chrome") || browserName==null) {
				
				// Chrome 
				ChromeOptions opt = new ChromeOptions();
			
				opt.addArguments("--headless=new");
				opt.addArguments("--disable-gpu");
		
				opt.addArguments("--disable-dev-shm-usage");
		
				opt.addArguments("--remote-allow-origins=*");
			
				opt.setAcceptInsecureCerts(true);
			
				URL url = new URL("http://localhost:4444/wd/hub"); 
			
				Map<String, Object> prefs = new HashMap<String, Object>();
			
				prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "Downloads");
				
				opt.setExperimentalOption("prefs", prefs);
				
				if (performSecurityTest) {
					 opt = SecurityTester.setUpSecurityTest(opt);
				}
				
				driver = new ChromeDriver(opt);
				
			} else if(ThreadLocalManager.getBrowserName().equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
		        driver = new FirefoxDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().setSize(new Dimension(1920, 1080));
			driver.manage().window().setPosition(new Point(0, 0));
			driver.manage().window().maximize();
		
		}
		
		//After initializing browser it will enter the browser name in Extent html report
		extent.setSystemInfo("Browser Name", ThreadLocalManager.getBrowserName()+" - "+ThreadLocalManager.getScenarioName());
		
		return driver;
	}

	public void closeBrowser() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserNameLast = cap.getBrowserName().toLowerCase();
		driver.close();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
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

	public void reportInfo(String msg) {
		test.pass(msg);
	}

	public void reportPass(String title, String exp, String act) {
		String message = "<b>" + "Expected " + title + " : " + "</b>" + "<font color=" + "green>" + exp + "</font>"
				+ "\t" + "<b>" + "Actual " + title + " : " + "</b>" + "<font color=" + "green>" + act + "</font>";
		test.pass(message);
	}

	public void reportFail(String title, String exp, String act) {
		String message = "<b>" + "Expected " + title + " : " + "</b>" + "<font color=" + "red>" + exp + "</font>" + "\t"
				+ "<b>" + "Actual " + title + " : " + "</b>" + "<font color=" + "red>" + act + "</font>";
		test.fail(message);
//		softAssert.assertEquals(exp, act);
		softAssertThreadLocal.get().assertEquals(act, exp);
	}

	public void reportPayload(String msg) {
		Markup m = MarkupHelper.createCodeBlock(msg, CodeLanguage.JSON);
		test.info(m);
	}

	public void validateField(String title, String expected, String actual) {

		if (expected.equals(actual))
			reportPass(title, expected, actual);
		else
			reportFail(title, expected, actual);
	}

	public void validateNullValues(String title, String actual) {

		if (actual == null)
			reportPass(title, "shold have null ", "contain null");
		else
			reportFail(title, "shold have null ", "contain null");
	}

	public void reportScreenshot(String status, String exp, String act, WebDriver driver) throws IOException {

		String message = "<b>" + "Expected : " + "</font>" + "</b>" + exp + "\t" + "<b>" + "Actual : " + "</b>" + act;
		if (status.equals("PASS"))
			test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("FAIL"))
			test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("INFO"))
			test.info(message, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}

	public void reportScreenshot(WebDriver driver) throws IOException {

		test.info("<b>" + "<font color=" + "orange>" + "Screenshot" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}

	public String captureScreenShot(WebDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
//		String dest = System.getProperty("user.dir") + "\\"+"AutomationReports"+"\\"+folderName+ "\\" +"ScreenShots"+"\\"+ getcurrentdateandtime() + ".jpg";
		String dest = System.getProperty("user.dir") + "/"+"AutomationReports"+"/"+folderName+ "/" +"ScreenShots"+"/"+ getcurrentdateandtime() + ".jpg";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
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

	public void testStepHandle(String teststatus, WebDriver driver, ExtentTest extenttest, Throwable throwable) {
		switch (teststatus) {
		case "FAIL":
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
			extenttest.log(Status.FAIL, throwable.fillInStackTrace());

			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
			} catch (IOException e) {
				// e.printStackTrace();
			}
//			softAssert.fail();
			softAssertThreadLocal.get().fail();
			//VideoRecorder.stopRecording();
			break;

		case "PASS":
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;

		default:
			break;
		}
	}
	
	// testStepHandles For API 
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

	/* Validate GetFleetByDevice Response */
	public void validateGetFlletByDeviceResponse(Response response, Map<String, String> testData, String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);

		if (status.equalsIgnoreCase("Assigned")) {
			validateField("ID", testData.get("ID"), js.getString(ResponseTags.GetFleetByDevice_ID));
			validateField("Feet ID", testData.get("FLEETID"), js.getString(ResponseTags.GetFleetByDevice_FLEETID));
			validateField("Device Type", testData.get("DEVICETYPE"),
					js.getString(ResponseTags.GetFleetByDevice_DEVICETYPE));
		} else if (status.equalsIgnoreCase("Unassigned")) {

			if (resString.contains(testData.get("DEVICESTATUS"))) {
				reportPass("Device Status", testData.get("DEVICESTATUS"), resString);
			}

		}
		reportInfo("Response : " + response.asPrettyString());

	}

	/* Validate ActivateLicenses Response */
	public void validateActivateLicensesResponse(Response response, Map<String, String> testData, String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);
		validateField("License Id", "[" + testData.get("LICENSEIDS") + "]",
				js.getString(ResponseTags.ActivateLicense_LICENSEID));
		validateField("Status", "[" + testData.get("STATUS") + "]", js.getString(ResponseTags.ActivateLicense_STATUS));
		validateField("Status Comment", "[" + testData.get("STATUSCOMMENT") + "]",
				js.getString(ResponseTags.ActivateLicense_STATUSCOMMENT));

	}

	/* Validate DeactivateLicenses Response */
	public void validateDeactivateLicensesResponse(Response response, Map<String, String> testData, String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);
		validateField("License Id", "[" + testData.get("LICENSEIDS") + "]",
				js.getString(ResponseTags.ActivateLicense_LICENSEID));
		validateField("Status", "[" + testData.get("STATUS") + "]", js.getString(ResponseTags.ActivateLicense_STATUS));
		validateField("Status Comment", "[" + testData.get("STATUSCOMMENT") + "]",
				js.getString(ResponseTags.ActivateLicense_STATUSCOMMENT));

	}

	public void validateGetDeviceConfigResponse(Response response, Map<String, String> testData) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);
		validateField("Data", testData.get("Data"), js.getString(ResponseTags.GetDeviceConfig_DATA));

	}

	public void validateAvailableLicensesResponse(Response response, Map<String, String> testData, String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);

		if (status.equalsIgnoreCase("Available")) {
			validateField("ID", testData.get("ID"), js.getString(ResponseTags.GetAvailableLicense_ID));
			validateField("Fleet", testData.get("FLEET"), js.getString(ResponseTags.GetAvailableLicense_FLEET));
			validateField("Expiry Date", testData.get("EXPIRYDATE"),
					js.getString(ResponseTags.GetAvailableLicense__EXPIRYDATE));
			validateField("License Status", testData.get("LICENSESTATUS"),
					js.getString(ResponseTags.GetAvailableLicense_LICENSESTATUS));
			validateField("Slot Type", testData.get("SLOTTYPE"),
					js.getString(ResponseTags.GetAvailableLicense_SLOTTYPE));
			validateField("Slots", testData.get("SLOTS"), js.getString(ResponseTags.GetAvailableLicense_SLOTS));
			validateField("Slot Used", testData.get("SLOTUSED"),
					js.getString(ResponseTags.GetAvailableLicense_SLOTUSED));
		} else if (status.equalsIgnoreCase("Invalid")) {
			// validateField("Time Stamp", testData.get("TIMESTAMP"),
			// js.getString(ResponseTags.GetAvailableLicense_TIMESTAMP));
			validateField("Status", testData.get("STATUS"), js.getString(ResponseTags.GetAvailableLicense__STATUS));
			validateField("Error", testData.get("ERROR"), js.getString(ResponseTags.GetAvailableLicense_ERROR));
			validateField("Exception", testData.get("EXCEPTION"),
					js.getString(ResponseTags.GetAvailableLicense_EXCEPTION));
			validateField("Message", testData.get("MESSAGE"), js.getString(ResponseTags.GetAvailableLicense_MESSAGE));
			validateField("Path", testData.get("PATH"), js.getString(ResponseTags.GetAvailableLicense_PATH));

		}

		else if (status.equalsIgnoreCase("Unavailable")) {
			validateField("ID", "", js.getString(ResponseTags.GetAvailableLicense_ID));
			validateField("Fleet", "", js.getString(ResponseTags.GetAvailableLicense_FLEET));
			validateField("Expiry Date", "", js.getString(ResponseTags.GetAvailableLicense__EXPIRYDATE));
			validateField("License Status", "", js.getString(ResponseTags.GetAvailableLicense_LICENSESTATUS));
			validateField("Slot Type", "", js.getString(ResponseTags.GetAvailableLicense_SLOTTYPE));
			validateField("Slots", "", js.getString(ResponseTags.GetAvailableLicense_SLOTS));
			validateField("Slot Used", "", js.getString(ResponseTags.GetAvailableLicense_SLOTUSED));

		}

	}

	public void validateGetSoftwareTemplateAndSettingResponse(Response response, Map<String, String> testData,
			String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);

		if (status.equalsIgnoreCase("Assigned")) {
			validateField("Template ID", testData.get("TEMPLATEID"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_TEMPLATEID));
			validateField("Template Name", testData.get("TEMPLATENAME"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_TEMPLATENAME));
			validateField("Template Json: Name", testData.get("TEMPLATEJSON"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_TEMPLATEJSON));
			validateField("SW Setting: Settings ID", testData.get("SWSETTING.SETTINGSID"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_SWSETTING_SETTINGSID));
			validateField("SW Setting: Setting Json: ID", testData.get("SWSETTING.SETTINGSJSON"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_SWSETTING_SETTINGSJSON));
			validateField("SW Setting: Settings Name", testData.get("SWSETTING.SETTINGSNAME"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_SWSETTING_SETTINGSNAME));
			validateField("SW Settng: Current Setting", testData.get("SWSETTING.CURRENTSETTING"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_SWSETTING_CURRENTSETTING));
		} else if (status.equalsIgnoreCase("Unassigned")) {
			// validateField("Time Stamp", testData.get("TIMESTAMP"),
			// js.getString(ResponseTags.GetAvailableLicense_TIMESTAMP));
			validateField("Status", testData.get("STATUS"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting__STATUS));
			validateField("Error", testData.get("ERROR"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_ERROR));
			validateField("Exception", testData.get("EXCEPTION"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_EXCEPTION));
			validateField("Message", testData.get("MESSAGE"),
					js.getString(ResponseTags.getSoftwareTemplateAndSetting_MESSAGE));
			validateField("Path", testData.get("PATH"), js.getString(ResponseTags.getSoftwareTemplateAndSetting_PATH));

		}

	}

	/* Validate GetAssignedLicense Response */
	public void validateGetAssignedLicenseResponse(Response response, Map<String, String> testData, String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);

		if (status.equalsIgnoreCase("Assigned")) {
			validateField("ID", testData.get("ID"), js.getString(ResponseTags.GetAssignedLicense_ID));
			validateField("FLeet", testData.get("FLEET"), js.getString(ResponseTags.GetAssignedLicense_FLEET));
			validateField("EXPIRY DATE", testData.get("EXPIRYDATE"),
					js.getString(ResponseTags.GetAssignedLicense__EXPIRYDATE));
			validateField("lICENSE STATUS", testData.get("LICENSESTATUS"),
					js.getString(ResponseTags.GetAssignedLicense_LICENSESTATUS));
			validateField("SLOT TYPE", testData.get("SLOTTYPE"),
					js.getString(ResponseTags.GetAssignedLicense_SLOTTYPE));

		} else if (status.equalsIgnoreCase("Unassigned")) {

		}
	}

	/* Validate DeviceTemplateAndSettings Response */
	public void validateGetDeviceTemplateAndSettingsResponse(Response response, Map<String, String> testData,
			String status) {

		String resString = response.then().extract().asPrettyString();
		JsonPath js = new JsonPath(resString);

		if (status.equalsIgnoreCase("Assigned")) {
			validateField("Template ID", testData.get("Template_ID"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATEID));
			validateField("Template Name", testData.get("Template_Name"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATENAME));
			validateField("Template JSON", testData.get("TemplateJSON"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATEJSON));
			validateField("DeviceSettings SettingsID", testData.get("DeviceSettings.SettingsID"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSID));
			validateField("DeviceSettings SettingsJSON", testData.get("DeviceSettings.SettingsJSON"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSJSON));
			validateField("DeviceSettings SettingsName", testData.get("DeviceSettings.SettingsName"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSNAME));
			validateField("DeviceSettings CurrentSetting", testData.get("DeviceSettings.CurrentSetting"),
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_DEVICESETTINGS_CURRENTSETTING));

		} else if (status.equalsIgnoreCase("Unassigned")) {

			validateNullValues("Template JSON", js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATEJSON));
			validateNullValues("Template ID", js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATEID));
			validateNullValues("Template Name", js.getString(ResponseTags.GetDeviceTemplateAndSettings_TEMPLATENAME));
			validateNullValues("Device Settings",
					js.getString(ResponseTags.GetDeviceTemplateAndSettings_DEVICESETTINGS));

		}
	}
	
	//Random String Generator

	public String randomStringGenerator() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String timestamp = sdf.format(date);
		String randomString = "testauto" + timestamp;
		return randomString;
	}

	//Random Integer Generator
	public String randomIntGenerator() {

		Random rand = new Random();
        int num = rand.nextInt((int) Math.pow(10, 10));
        return String.valueOf(num);

	}

	//// Random Email Generator

	public String randomEmailGenerator() {

		String[] emailProviders = {"gmail.com", "yahoo.com", "hotmail.com", "aol.com", "outlook.com"};
		Random random = new Random();
        String randomString = "testauto";
        for (int i = 0; i < 4; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            randomString += c;
        }
        return randomString + "@" + emailProviders[random.nextInt(emailProviders.length)];

	}

	public static String getRamdomMAcAddress() {
		Random rand = new Random();
		return String.format("%02x-%02x-%02x-%02x-%02x", rand.nextInt(256), 0x5e, rand.nextInt(256), 0x53, 0xaf);
	}



	

	

}
