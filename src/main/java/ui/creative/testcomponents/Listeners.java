package ui.creative.testcomponents;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.zaproxy.clientapi.core.ClientApiException;

import com.aventstack.extentreports.ExtentReports;
import com.codoid.products.exception.FilloException;


public class Listeners extends BaseTest implements ITestListener{


	public Listeners() throws IOException {
		super();
		
	}

	private static ExtentReports extent;
	private List<ITestResult> passedTests = new ArrayList<>();
	private List<ITestResult> failedTests = new ArrayList<>();
	private List<ITestResult> skippedTests = new ArrayList<>();
	
	
	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(ThreadLocalManager.getScenarioName() +" : PASS");
		
		//For cross browser parallel execution------------------------------------------------
				int columnNumber = 0;
				
				rowCountForParallel=ReadXL.CheckScenarioInList(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","CrossBrowserParallelResult");
				if(rowCountForParallel==-1)
					rowCountForParallel=ReadXL.LastEmptyRowForCrossBrowserSummary(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","CrossBrowserParallelResult");
				rowCountForParallel=rowCountForParallel+1;
				
//				System.out.println(rowCountForParallel+" From LISTENER "+ThreadLocalManager.getBrowserName());
				
				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "CrossBrowserParallelResult", ThreadLocalManager.getScenarioName(), rowCountForParallel, 1);
				
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("firefox"))
					columnNumber = 2;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("edge"))
					columnNumber = 3;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("chrome"))
					columnNumber = 4;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("safari"))
					columnNumber = 5;
				
				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "CrossBrowserParallelResult", "PASS", rowCountForParallel, columnNumber);
				
//				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 6);
				
//                rowCountForParallel++;
		//-----------------------------------------------------------------------------------

		passedTests.add(result);
		
		rowcount=ReadXL.CheckBuildNum(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		if(rowcount==-1)
			rowcount=ReadXL.LastEmptyRow(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		rowcount=rowcount+1;
		
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 1);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getBuildNumber(), rowcount, 2);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", env.toUpperCase(), rowcount, 3);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getBrowserName(), rowcount, 5);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", folderName, rowcount, 6);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getScenarioName(), rowcount, 7);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", "PASS", rowcount, 8);

		try {
			ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getModuleName(ThreadLocalManager.getScenarioName()), rowcount, 4);
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
		
		
//		System.out.println("Checking Thread safty : "+scenarioName+"\n"+scenarioName.get());
		
		SuperTestNG.extent.flush();
		
		ThreadLocalManager.clear();
	}


	public void onTestFailure(ITestResult result) {
		System.out.println(ThreadLocalManager.getScenarioName() +" : FAIL");
		
		//For cross browser parallel execution------------------------------------------------
		int columnNumber = 0;
		
		rowCountForParallel=ReadXL.CheckScenarioInList(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","CrossBrowserParallelResult");
		if(rowCountForParallel==-1)
			rowCountForParallel=ReadXL.LastEmptyRowForCrossBrowserSummary(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","CrossBrowserParallelResult");
		rowCountForParallel=rowCountForParallel+1;
		
//		System.out.println(rowCountForParallel+" From LISTENER "+ThreadLocalManager.getBrowserName());
		
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "CrossBrowserParallelResult", ThreadLocalManager.getScenarioName(), rowCountForParallel, 1);
		
		if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("firefox"))
			columnNumber = 2;
		if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("edge"))
			columnNumber = 3;
		if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("chrome"))
			columnNumber = 4;
		if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("safari"))
			columnNumber = 5;
		
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "CrossBrowserParallelResult", "FAIL", rowCountForParallel, columnNumber);
		
//		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 6);
		
//        rowCountForParallel++;
//-----------------------------------------------------------------------------------
		
		failedTests.add(result);	
		
		rowcount=ReadXL.CheckBuildNum(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		if(rowcount==-1)
			rowcount=ReadXL.LastEmptyRow(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		rowcount=rowcount+1;
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 1);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getBuildNumber(), rowcount, 2);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", env.toUpperCase(), rowcount, 3);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getBrowserName(), rowcount, 5);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", folderName, rowcount, 6);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getScenarioName(), rowcount, 7);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", "FAIL", rowcount, 8);

		try {
			ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getModuleName(ThreadLocalManager.getScenarioName()), rowcount, 4);
		} catch (FilloException e) {
	
			e.printStackTrace();
		}
//
//		//rowcount++;
		
		
//		System.out.println("Checking Thread safty : "+scenarioName+"\n"+scenarioName.get());
		
		SuperTestNG.extent.flush();
		
		ThreadLocalManager.clear();
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(ThreadLocalManager.getScenarioName() +" : SKIPPED");
		
		//For cross browser parallel execution------------------------------------------------
				int columnNumber = 0;
				
				rowCountForParallel=ReadXL.CheckScenarioInList(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx","TESTRESULTS");
				if(rowCountForParallel==-1)
					rowCountForParallel=ReadXL.LastEmptyRowForCrossBrowserSummary(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx","TESTRESULTS");
				rowCountForParallel=rowCountForParallel+1;
				
//				System.out.println(rowCountForParallel+" From LISTENER "+ThreadLocalManager.getBrowserName());
				
				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx", "TESTRESULTS", ThreadLocalManager.getScenarioName(), rowCountForParallel, 1);
				
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("firefox"))
					columnNumber = 2;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("edge"))
					columnNumber = 3;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("chrome"))
					columnNumber = 4;
				if (ThreadLocalManager.getBrowserName().equalsIgnoreCase("safari"))
					columnNumber = 5;
				
				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx", "TESTRESULTS", "SKIPPED", rowCountForParallel, columnNumber);
				
//				ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummaryWithCrossBrowser.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 6);
				
//		        rowCountForParallel++;
		//-----------------------------------------------------------------------------------
		
		skippedTests.add(result);
		
		rowcount=ReadXL.CheckBuildNum(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		if(rowcount==-1)
			rowcount=ReadXL.LastEmptyRow(System.getProperty("user.dir")+"\\ResultsSummary.xlsx","TESTRESULTS");
		rowcount=rowcount+1;
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getTimeStamp(), rowcount, 1);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getBuildNumber(), rowcount, 2);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", env.toUpperCase(), rowcount, 3);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getBrowserName(), rowcount, 5);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", folderName, rowcount, 6);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", ThreadLocalManager.getScenarioName(), rowcount, 7);
		ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", "SKIPPED", rowcount, 8);

		try {
			ReadXL.writeCell(System.getProperty("user.dir")+"\\ResultsSummary.xlsx", "TESTRESULTS", getModuleName(ThreadLocalManager.getScenarioName()), rowcount, 4);
		} catch (FilloException e) {
			e.printStackTrace();
		}
		

		//rowcount++;
		
//		System.out.println("Checking Thread safty : "+scenarioName+"\n"+scenarioName.get());
		
		SuperTestNG.extent.flush();
				
		ThreadLocalManager.clear();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		

	}

	public void onStart(ITestContext context) {

		try {
			// to pass parameter
			String browser = System.getProperty("browser");
			String environment = System.getProperty("env");
			String BuildNumber = System.getProperty("buildNumber");

//			browserName = browser;
			ThreadLocalManager.setBrowserName(browser);
			buildNumber = BuildNumber;
			env = environment;

			setUpEnvironment();
			setUpTestDataEnvironment();
			getBuildNumber();

			startTime =  getTimeStamp();
			
		//Set up configuration for API
			setupConfiguration();
			
//			resetResultSummaryExcelSheet();

	// To setup report folder
			
			String testNameFromSuit = context.getCurrentXmlTest().getName();
//			System.out.println("testNameFromSuit : "+testNameFromSuit);
			
			if (!testNameFromSuit.equalsIgnoreCase("ResetResultSummary"))
				SuperTestNG.extent = SuperTestNG.setUp();
				
	// To setup Security Test
			performSecurityTest = false;
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		
		endTime =  getTimeStamp();

		try {
			duration = getDuration(startTime, endTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		//This methods used while executing parallel three different browser at a time
	//	TagSynchronization.notifyFirstTagComplete();
	//	Cross_Browser_Parallel_Property_Runner_01.setFirstTagCompleted();
    //    Cross_Browser_Parallel_Property_Runner_02.setFirstTagCompleted();
    //    Cross_Browser_Parallel_Property_Runner_03.setFirstTagCompleted();

		
		
		String testNameFromSuit = context.getCurrentXmlTest().getName();
//		System.out.println("testNameFromSuit : "+testNameFromSuit);
		
		if (!testNameFromSuit.equalsIgnoreCase("ResetResultSummary"))
			SuperTestNG.extent.flush();
		
		
		ThreadLocalManager.clear();
		

//		JsonFile.writeExecutionSummary(SuperTestNG.extent.getStats().getParentCount(), SuperTestNG.extent.getStats().getParentCountPass(),SuperTestNG.extent.getStats().getParentCountFail(), startTime,endTime,duration);
//		
//		System.out.println("Toal : "+SuperTestNG.extent.getStats().getParentCount()+" Pass : "+SuperTestNG.extent.getStats().getParentCountPass()+" Fail : "+SuperTestNG.extent.getStats().getParentCountFail()+" Skip : "+SuperTestNG.extent.getStats().getParentCountSkip());
		
		
		// To send notification regarding execution to microsoft team
		try {
			int totalTests = passedTests.size()+failedTests.size()+skippedTests.size();
			int passed = passedTests.size();
			int failed = failedTests.size();
			int skipped = skippedTests.size();

			String message = "Test Execution Summary:\n"
					+"Application Name: Demo WebShop"+ ",\n"
					+"Environment: " + prop.get("Environment") + ",\n"
			        + "Total Tests: " + totalTests + ",\n"
			        + "Passed: " + passed + ",\n"
			        + "Failed: " + failed + ",\n"
			        + "Skipped: " + skipped;

			TestExecutionNotifier.sendNotification(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
