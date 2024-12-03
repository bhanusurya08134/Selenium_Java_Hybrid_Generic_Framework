package ui.creative.testcomponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
/**
 * @author RaveendR
 *
 */
public class SendEMail{

	@Test
	public static void SendEmailRpt() throws Exception
	{
		//try {
			//String toaddress = "Rajitha.Muni@unisys.com,Debadatta.Kathar@unisys.com,Miraj.Uddin@unisys.com,ramidi.reddy@in.unisys.com,Mahantesh.Hubballi@unisys.com,Prakash.Shankarshetty@in.unisys.com";			
			
			String toaddress = "mmravi99@gmail.com";				
			String fromaddress = "mmravi99@gmail.com";
			String hostdetails = "smtp.gmail.com";			
			String[] to = {};	
			if(toaddress.length() != 0){
				to = toaddress.trim().split(",");
			}
			String attachmentPath = "C:\\Users\\ravimann\\git\\SmartTest-AI\\AutomationReports\\PdfReport";
			String attachmentfile = "";
			System.out.println("hostdetails"+hostdetails);
			System.out.println("fromaddress"+fromaddress);
			System.out.println("to"+to);
			System.out.println("attachmentPath"+attachmentPath);
			System.out.println("attachmentfile"+attachmentfile);
	        //AssertJUnit.assertTrue(sendEmailReport(hostdetails, fromaddress, to, attachmentPath, attachmentfile));
			
			SendEMail.sendEmailReport(hostdetails, fromaddress, to, attachmentPath, attachmentfile);
			
			
		//} catch (IOException e) {
		//	e.printStackTrace();
	//	}
	}


	public static boolean sendEmailReport(String host, String from, String[] to, String attachmentPath, String attachmentfilename) throws IOException, MessagingException, InterruptedException 
	{
		Boolean sent = false;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
	//	try {			
			/*System.out.println("build::"+buildNumber);	
			System.out.println("server::"+server);			
			String BuildDetails = buildNumber;		*/		
			//Assert.assertTrue(!BuildDetails.isEmpty());
			System.out.println("4");
			//Assert.assertTrue(!BuildDetails.isEmpty());
			System.out.println("5");
			MimeMessage message = new MimeMessage(session);
			for(int i=0;i<to.length;i++){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setFrom(new InternetAddress(from));
			message.setSubject("***Test Mail***Automation Selenium Execution Status");
			String FolderName = getRecentFolder(attachmentPath);			
			System.out.println("FolderName"+FolderName);			
			String FinalFile = attachmentPath+"\\"+FolderName+"\\"+attachmentfilename+FolderName;
			System.out.println("FinalFile is "+FinalFile);		
			File IPfile = new File(FinalFile);			
			IPfile=IPfile.getAbsoluteFile();
			System.out.println("IPfile"+IPfile);
			Thread.sleep(8000);			
			File theDir = new File(attachmentPath+"\\"+FolderName);			
			System.out.println("theDir"+theDir);			
			if(theDir.exists()){							
				String ZipFile =FinalFile;					
				//zipFile(IPfile, ZipFile);
				Multipart multipart = new MimeMultipart();
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(ZipFile);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName("SanityReport.xlsx");
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
				// message body part (the html)
				BodyPart messageBody = new MimeBodyPart();
				//String htmlText = "<p>Hi All, </p><p>Please find attached Automation Sanity Report for Latest Build and Environment.</p><p>Analysis of Failed Sceanarios are In Progress...</p><br><p><b>Server : </p>"+server+"</b></p><br><br><p><b>Build : </p>"+BuildDetails+"</b></p><br><br><br><br><p>**This is an Auto Generated Mail Report after Selenium Product Automation Execution</p><br><p>Regards,</p><p>Selenium Automation Team</p>";
				
				String htmlText = "<p>Hi All, </p><p>Please find attached Automation ExecutionReport for Latest Build and Environment.</p><p>Analysis of Failed Sceanarios are In Progress...</p><br><p><b> </p>"+""+"</b></p><br><br><p><b> </p>"+""+"</b></p><br><br><br><br><p>**This is an Auto Generated Mail Report after Selenium Product Automation Execution</p><br><p>Regards,</p><p>Selenium Automation Team</p>";
				
				
				messageBody.setContent(htmlText, "text/html");
				//add it
				multipart.addBodyPart(messageBody);
			//	message.saveChanges();
				Transport.send(message, message.getAllRecipients());
				sent = true;
			}
			else{
				System.out.println("File Not Found");
			}
		/*}
		catch(MessagingException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return sent;
	}
	public static String getRecentFolder(String Folderpath)
	{
		File directory = new File(Folderpath);
		File[] directories = directory.listFiles();
		File lastModifiedFile = directories[0];
		for(int i=0; i<directories.length; i++){
			if(directories[i].isDirectory()){
				if (lastModifiedFile.lastModified() < directories[i].lastModified() ) {
					lastModifiedFile = directories[i];
				}
			}
		}
		System.out.println(lastModifiedFile.getName());
		return lastModifiedFile.getName();
	}


	public static void zipFile(File inputFile, String zipFilePath) {
		/*try{
			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = new ZipEntry(inputFile.getName());
			zipOutputStream.putNextEntry(zipEntry);
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(inputFile);            
			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = fileInputStream.read(buf)) > 0) {
				zipOutputStream.write(buf, 0, bytesRead);
			}
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileOutputStream.close();
			System.out.println("Regular file :" + inputFile.getCanonicalPath()+" is zipped to archive :"+zipFilePath);
		} 
		catch (IOException e) { 
			e.printStackTrace();  
		}*/

	}

}
