package ui.playarea;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Worksheet;

import ui.creative.testcomponents.BaseTest;
import ui.creative.testcomponents.DBConnection;
import ui.creative.testcomponents.ExcelFileReader;
import ui.creative.testcomponents.JsonFile;
import ui.creative.testcomponents.ReadXL;
import ui.creative.testcomponents.SuperTestNG;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.mail.*;


public class Play  extends SuperTestNG{

	public Play() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		String loc ="C:\\Users\\ravimann\\Creative Synergies Consulting India Pvt. Ltd\\DT - Engineering Team - CT_QaAnt - CT_QaAnt\\SafeFleet_LM_AutomationTesting\\Downloads\\settings-12152022022000.xlsx"; 
//		com.spire.xls.Workbook workbook = new com.spire.xls.Workbook();
//        workbook.loadFromFile("C:\\Users\\ravimann\\Creative Synergies Consulting India Pvt. Ltd\\DT - Engineering Team - CT_QaAnt - CT_QaAnt\\SafeFleet_LM_AutomationTesting\\Downloads\\settings-12152022042741.xlsx");
//        Worksheet worksheet = workbook.getWorksheets().get(0);
//        worksheet.setName("Settings1");
//        workbook.saveToFile("C:\\Users\\ravimann\\Creative Synergies Consulting India Pvt. Ltd\\DT - Engineering Team - CT_QaAnt - CT_QaAnt\\SafeFleet_LM_AutomationTesting\\Downloads\\settings-12152022042741.xlsx", ExcelVersion.Version2010);

//		WebDriverManager.chromedriver().setup();
//		ChromeOptions opt = new ChromeOptions();
//		WebDriver driver =new ChromeDriver(opt);
//		driver.manage().window().maximize();
//		driver.get("https://test-license-manager.safefleetcloud.com/");
//		Thread.sleep(8000);
//		
//		driver.findElement(By.xpath("//input[@id='Input_Email']")).sendKeys("ravi.manne@global-csg.com");
//		driver.findElement(By.xpath("//input[@id='Input_Password']")).sendKeys("LMtest@123");
//		driver.findElement(By.cssSelector(".btn-primary")).click();
//		Thread.sleep(8000);
//		driver.findElement(By.xpath("//li[@id='lifecycle']//a[@class='mm-next mm-fullsubopen']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//span[@id='lifecycle-customer']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@aria-label='Filter for column']")).sendKeys("Test01");
//		Thread.sleep(2000);
//       List<Map<String, ?>> res = DBConnection.getData("SELECT * FROM test_license_manager.device where sales_order='SFD'");
		
//		List<Map<String, ?>> res = DBConnection.getData("select status, data_status, settings_instance_id from test_license_manager.device where device_id='100'");
//		System.out.println(res);
//		System.out.println(res.get(0).get("STATUS"));
//		System.out.println(res.get(0).get("DATA_STATUS"));
//		System.out.println(res.get(0).get("SETTINGS_INSTANCE_ID"));
//		
//		List<Map<String, ?>> res2 = DBConnection.getData("SELECT name FROM test_license_manager.settings_instance where id='"+res.get(0).get("SETTINGS_INSTANCE_ID")+"';");
//		System.out.println(res2);
//		System.out.println(res2.get(0).get("NAME"));
//		BaseTest.TestDataPath = "\\src\\test\\java\\testdata\\TEST\\TestData.xlsx";
		
		//Map<String, String> testData = ExcelFileReader.getDataInMap("LicensePage", "VMM-3411_1");
		//System.out.println(testData.get("ExpiryDate"));
//		Random rand = new Random();
        //for (int i = 0; i < 100; i++) {
//            System.out.println(String.format("%02x-%02x-%02x-%02x-%02x",
//                    rand.nextInt(256),
//                    0x5e,
//                    rand.nextInt(256),
//                    0x53,
//                    0xaf));
        //}
		//System.out.println(BaseTest.getRamdomMAcAddress());
		
		// Create the attachment
//		  EmailAttachment attachment = new EmailAttachment();
//		  attachment.setPath("C:\\Users\\ravimann\\Creative Synergies Consulting India Pvt. Ltd\\DT - Engineering Team - CT_QaAnt - CT_QaAnt\\SafeFleet_LM_AutomationTesting\\AutomationReports\\ExtentReportCucumber 18-Feb-23 12-00-36\\PdfReport\\ExtentPdf.pdf");
//		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		  attachment.setDescription("Picture of John");
//		  attachment.setName("John");
//
//		  // Create the email message
//		  MultiPartEmail email = new MultiPartEmail();
//		  email.setHostName("smtp.googlemail.com");
//		  email.addTo("mmravi99@gmail.com", "John Doe");
//		  email.setFrom("mmravi99@gmail.com", "Me");
//		  email.setSubject("Test Execution");
//		  email.setMsg("Regression Test Results");
//
//		  // add the attachment
//		  email.attach(attachment);
//
//		  // send the email
//		  email.send();
//		
		
		
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.gmail.com");
//		email.setSmtpPort(465);
//		email.setAuthenticator(new DefaultAuthenticator("mmravi99@gmail.com", "rilwsxfomplgloie"));
//		email.setSSLOnConnect(true);
//		email.setFrom("mmravi99@gmail.com");
//		email.setSubject("TestMail");
//		email.setMsg("This is a test mail ... :-)");
//		email.addTo("mmravi99@gmail.com");
//		email.send();
		
		
		
		//JsonFile.writeExecutionSummary("12", "12345", "2.4");
		
		System.out.println(setFolderName());

	}

}
