package ui.playarea;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZAPSecurityTest {
	static final String ZAP_PROXY_ADDRESS="localhost";
	static final int ZAP_PROXY_PORT=8080;
	static final String ZAP_API_KEY ="9r647qjva0b453bukekv5str3f";
	
	private static ClientApi api;
	@BeforeMethod
	public static void setupSecurityTest(ChromeOptions opt) {
		String proxyServerurl = ZAP_PROXY_ADDRESS+":"+ZAP_PROXY_PORT;
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(proxyServerurl);
		proxy.setSslProxy(proxyServerurl);
		opt.setProxy(proxy);
		api= new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT,ZAP_API_KEY);
	}
	@AfterMethod
	public static void tearDown() throws ClientApiException {
		if(api!=null) {
			ApiResponse response = api.reports.generate("LM", "traditional-html", null, "LM Security Test",null,null,null,null,null,"security-report.html",null,System.getProperty("user.dir"),null);
			System.out.println(response.toString());
		}
	}
	

}
