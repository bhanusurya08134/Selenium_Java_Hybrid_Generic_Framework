package ui.creative.componentgroups;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir")+"//src//test//resources//globalSettings.properties";
	FileOutputStream outputStrem;

	public ConfigFileReader(){
		FileInputStream reader;
		try {
			reader = new FileInputStream(propertyFilePath);
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("globalSettings.properties not found at " + propertyFilePath);
		}		
	}
	
	public void configFileWrite() throws IOException {
		 outputStrem = new FileOutputStream(propertyFilePath);
	      //Storing the properties file
		 properties.store(outputStrem, "Check");
	}
	


	public String getLMTestUrl() {
		String url = properties.getProperty("LM_Test");
		if(url != null) return url;
		else throw new RuntimeException("LM_Test not specified in the GlobalSettings.properties file.");
	}


	public String getBrowserName() {
		String browserName = properties.getProperty("BrowserName");
		if(browserName != null) return browserName;
		else throw new RuntimeException("BrowserName not specified in the GlobalSettings.properties file.");
	}


	public String getEnvName() {
		String envName = properties.getProperty("Environment");
		if(envName != null) return envName;
		else throw new RuntimeException("Environment not specified in the GlobalSettings.properties file.");
	}
	
	public void setEnvName(String env) {
		try {
			properties.setProperty("Environment",env);
			configFileWrite();
		}	
		catch(Exception e) 
		{
			e.getStackTrace();
		}
	}


	public String getLMDevUrl() {
		String url = properties.getProperty("LM_Dev");
		if(url != null) return url;
		else throw new RuntimeException("LM_Dev not specified in the GlobalSettings.properties file.");
	}

	public String getRunConfigFilePath() {
		String filePath = System.getProperty("user.dir")+"\\"+properties.getProperty("FileName");
		return filePath;
	}
	
	public String getRunConfigSheetname() {
		String runConfigSheetname = properties.getProperty("Run_Config_Sheetname");
		if(runConfigSheetname != null) return runConfigSheetname;
		else throw new RuntimeException("Run_Config_Sheetname not specified in the GlobalSettings.properties file.");
	}
	
	public void setScenarioName(String scenarioName) {
		try {
			properties.setProperty("Scenario_Name",scenarioName);
		}	
		catch(Exception e) 
		{
			e.getStackTrace();
		}
	}
	
	public String getScenarioName() {
		String scenarioName = properties.getProperty("Scenario_Name");
		if(scenarioName != null) return scenarioName;
		else throw new RuntimeException("Scenario_Name not specified in the GlobalSettings.properties file.");
	}
	
}
