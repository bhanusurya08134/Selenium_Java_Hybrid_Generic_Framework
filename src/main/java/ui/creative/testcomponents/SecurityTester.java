package ui.creative.testcomponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SecurityTester extends BaseTest{
	
	public SecurityTester() throws IOException {
		super();
	}

	static final String ZAP_PROXY_ADDRESS = "localhost";
	static final int ZAP_PROXY_PORT = 8081;
	static final String ZAP_API_KEY = "basuqc9hnhcfirab7tavvccfb5";
	
	
	private WebDriver driver;
	private static ClientApi api;
	
//	@BeforeMethod
	public static ChromeOptions setUpSecurityTest(ChromeOptions opt) {
		String proxyServerUrl = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
		
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(proxyServerUrl);
		proxy.setSslProxy(proxyServerUrl);
		
//		ChromeOptions chrome = new ChromeOptions();
		
		opt.setAcceptInsecureCerts(true);
		opt.setProxy(proxy);
		
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver(chrome);
		
		api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
		
		return opt;
	}
	

//	@AfterMethod
	public static void generateSecurityTestReport() throws ClientApiException {
		
//		String reportFileName = "Demo-zap-security-test-report"+getCurrentDateAndTime().replace(":","-")+".html";
		String reportFileName = ThreadLocalManager.getScenarioName()+"-zap-security-test-report-"+getCurrentDateAndTime().replace(":","-")+".html";
		
//		System.out.println(reportFileName);
		
		if(api != null) {
			
			String title = ThreadLocalManager.getScenarioName()+" - Security Test Report";
			String tamplate = "traditional-html";
			String description = "This is Demo Zap security test report";
//			String reportFileName = "Demo-zap-security-test-report.html";
			String targetFolder = System.getProperty("user.dir")+"//ZapSecurityTestReports";
			
			File folder = new File(targetFolder);

			if (!folder.exists()) {
			    if (folder.mkdirs()) {
			        System.out.println("Directory created successfully.");
			    }}
			
			ApiResponse response = api.reports.generate(title, tamplate, null, description, null, null, null, null, null, reportFileName, null, targetFolder, null);
			
			System.out.println("ZAP report generated : " + response.toString());
		}
	}
	
	 public static String getCurrentDateAndTime() {
	        Date currentDate = new Date(); 

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        String formattedDateTime = dateFormat.format(currentDate);

//	        System.out.println("Current Date and Time: " + formattedDateTime);
	        
	        return formattedDateTime;
	    }
	
	

}
