package ui.creative.testcomponents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestNGXMLGenerator extends TestRunnerJavaGenerator{

    public static void main(String[] args){
    	
    	TestRunnerJavaGenerator.main(args);
    	
        generateXmlFile();
        
        String directoryPath = "src/test/java/ui/creative/dynamictestrunners";
        refreshDirectory(directoryPath);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        TestNG testng = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();
        XmlSuite suite = new XmlSuite();
        suite.setName("MyTestSuite");
        suite.setSuiteFiles(Arrays.asList("UI_testSuites/testng.xml"));
        suites.add(suite);
        testng.setXmlSuites(suites);

        testng.run();
    }

    public static void generateXmlFile() {
        try {
            FileWriter writer = new FileWriter("UI_testSuites/testng.xml");

            //XML header
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE suite SYSTEM \"https://testng.org/testng-1.0.dtd\">\n");
            writer.write("<suite name=\"MyTestSuite\" thread-count=\"10\" parallel=\"classes\">\n");

            // This tag execute for clear Results summary previous data
            writer.write("<test name=\"ResetResultSummary\"><classes><class name=\"ui.creative.testcomponents.SuperTestNG\"><methods><include name=\"resetResultSummaryExcelSheet\"></include></methods></class></classes></test>\n");

            writer.write("    <listeners>\n");
            writer.write("        <listener class-name=\"ui.creative.testcomponents.Listeners\"/>\n");
            writer.write("    </listeners>\n");
            
            // List of browsers
            String[] browsers = {"chrome", "firefox", "edge"};

            for (String browser : browsers) {
                writeTestTag(writer, browser);
            }

            // Close the suite tag
            writer.write("</suite>");

            writer.close();
            System.out.println("TestNG XML file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTestTag(FileWriter writer, String browser) throws IOException {
        writer.write("    <test name=\"" + browser + "_test\">\n");
        writer.write("        <parameter name=\"BrowserType\" value=\"" + browser + "\"/>\n");
        writer.write("        <classes>\n");

        // List of your runner classes for this browser
//        String[] runnerClasses = {
//            "DemoWebShop.TestRunners.Cross_Browser_Parallel_Property_Runner_01_1",
//            "DemoWebShop.TestRunners.Cross_Browser_Parallel_Property_Runner_01_2",
//        };
        
        String[] runnerClasses = getRunnerClassBasedOnTagsFromExcel();

        for (String runnerClass : runnerClasses) {
            writer.write("            <class name=\"" + runnerClass + "\"/>\n");
        }

        writer.write("        </classes>\n");
        writer.write("    </test>\n");
    }

	private static String[] getRunnerClassBasedOnTagsFromExcel() {
		
//		int numberOfTtags = 3;
		int numberOfTtags = TestRunnerJavaGenerator.numberOfTags;
		
//		System.out.println("Number of tags from TestRunnerGenerator : "+numberOfTtags);
		
		String[] runnerClasses = new String[numberOfTtags];
		
		for (int i = 0; i<numberOfTtags; i++) {
			runnerClasses[i]= "ui.creative.dynamictestrunners.Cross_Browser_Parallel_Property_Runner_01_"+(i+1);
//			System.out.println(runnerClasses[i]);
		}
		return runnerClasses;
	}
	
	public static void refreshDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        System.out.println(directory.exists());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.setLastModified(System.currentTimeMillis());
                }
            }
        }
    }
}
