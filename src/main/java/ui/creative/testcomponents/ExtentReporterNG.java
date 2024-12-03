package ui.creative.testcomponents;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG extends BaseTest{

	
	public ExtentReporterNG() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static ExtentReports getReportObject()
	{
		String fileName = "Run_" +getTimeStamp();
		String path =System.getProperty("user.dir")+"//reports//"+fileName+".html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setEncoding("utf-8");
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Application", "LM Regression Testing");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");
		return extent;
	}
	
}
