package ui.creative.testcomponents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class TestRunnerJavaGenerator {
	
	static int numberOfTags ;

    public static void main(String[] args) {
        
        ArrayList<String> ExecutableScenarios = getNumberOfScenariosReadyToExecute();
        
        numberOfTags = Integer.parseInt(ExecutableScenarios.get(ExecutableScenarios.size()-1));
        
        for (int i = 1; i <= numberOfTags; i++) {
            String className = "Cross_Browser_Parallel_Property_Runner_01_" + i;
            String code = generateJavaCode(className, ExecutableScenarios.get(i-1));

//            String filePath = "src/DemoWebShop/DynamicTestRunners/" + className + ".java";
//            String filePath = "src\\test\\java\\ui\\creative\\dynamictestrunners\\" + className + ".java";
            String filePath = "src/test/java/ui/creative/dynamictestrunners/" + className + ".java";
            
            try {
                File file = new File(filePath);
                FileWriter writer = new FileWriter(file);
                writer.write(code);
                writer.close();
                System.out.println("Java file " + className + " created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> getNumberOfScenariosReadyToExecute() {
    	
    	String filePath = System.getProperty("user.dir")+"/ResultsSummary.xlsx";
        String query = "SELECT * FROM SetUpParallelSuit"; // Replace with the actual sheet name
        
        Fillo fillo = new Fillo();
        Connection connection = null;

        try {
            connection = fillo.getConnection(filePath);
            Recordset recordset = connection.executeQuery(query);

            String[] column1Values = new String[recordset.getCount()];
            String[] column2Values = new String[recordset.getCount()];

            int rowIndex = 0;
            while (recordset.next()) {
                column1Values[rowIndex] = recordset.getField("ScenarioTags");
                column2Values[rowIndex] = recordset.getField("Execute");
                rowIndex++;
            }
            
       //Required values
            int tempCountOfYes = 0;
            ArrayList<String> ExecutableScenarios = new ArrayList<>();
            
            for(int i=0; i<column1Values.length; i++) {
            	if(column2Values[i].equalsIgnoreCase("YES")) {
            		ExecutableScenarios.add(column1Values[i]);
            		tempCountOfYes++;
            	}
            }
            
            System.out.println("Number of scenarios : "+tempCountOfYes);
            System.out.println("Scenarios : "+ExecutableScenarios);
            
      //*****Adding number of yes at the end of Array List*****
            ExecutableScenarios.add(tempCountOfYes+"");

            
            return ExecutableScenarios;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    	
		return null;
	}

	private static String generateJavaCode(String className, String ScenarioTag) {
        return "package ui.creative.dynamictestrunners;\n" +
                "\n" +
                "// Import necessary libraries here\n" +
                "\n" +
                "import java.awt.AWTException;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "\n" +
                "import org.testng.annotations.AfterClass;\n" +
                "\n" +
                "import org.testng.annotations.BeforeClass;\n" +
                "\n" +
                "import org.testng.annotations.Optional;\n" +
                "\n" +
                "import org.testng.annotations.Parameters;\n" +
                "\n" +
                "\n" +
                "import ui.creative.testcomponents.ThreadLocalManager;\n" +
                "\n" +
                "import io.cucumber.testng.AbstractTestNGCucumberTests;\n" +
                "\n" +
                "import io.cucumber.testng.CucumberOptions;\n" +
                "\n" +
                "@CucumberOptions(features = \"src/test/java/ui/creative/features\", tags = \""+"@"+ScenarioTag+"\", glue = \"ui/creative/stepdefinitions\", monochrome = true, plugin = { \"html:target/cucumber.html\",\"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:\" })\n" +
                "public class " + className + " extends AbstractTestNGCucumberTests {\n" +
                "\n" +
                "    @Parameters(\"BrowserType\")\n" +
                "    @BeforeClass\n" +
                "    public void beforeClass(@Optional String browser) throws AWTException, IOException, InterruptedException {\n" +
                "        Thread.sleep(0);\n" +
                "        ThreadLocalManager.setBrowserName(browser);\n" +
                "    }\n" +
                "\n" +
                "    @AfterClass\n" +
                "    public void afterClass() {\n" +
                "    }\n" +
                "}\n";
    }
}
