package ui.creative.testcomponents;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class JsonFile {
	public static void writeExecutionSummary(int tot,int pass,int fail, String starttime, String endtime,String duration) {
        // Create a JSONObject to store key-value pairs
        JSONObject jsonObject = new JSONObject();
        double percentage = ((double)pass / tot) * 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        
        
        jsonObject.put("total", tot);
        jsonObject.put("pass", pass);
        jsonObject.put("fail", fail);
        jsonObject.put("percentage", df.format(percentage)+"%");
        jsonObject.put("start", starttime);
        jsonObject.put("end", endtime);
        jsonObject.put("duration", duration);

        // Create a JSONArray to store multiple JSONObjects
        //JSONArray jsonArray = new JSONArray();
        //jsonArray.put(jsonObject);

        try {
            // Write the JSON array to a file
            FileWriter writer = new FileWriter("ExecutionSummary.json");
            writer.write(jsonObject.toString());
            writer.close();
            System.out.println("Successfully wrote JSON object to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void writeDirectoryName(String dirname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("folder", dirname);
		try {
            // Write the JSON array to a file
            FileWriter writer = new FileWriter("Folder.json");
            writer.write(jsonObject.toString());
            writer.close();
            System.out.println("Successfully wrote JSON object to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
