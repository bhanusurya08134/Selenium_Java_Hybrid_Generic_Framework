package ui.creative.testcomponents;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class TestExecutionNotifier extends BaseTest{

	public TestExecutionNotifier() throws IOException {
		super();

	}



	public static void sendNotification(String message) {
		try {

			List<String> columnValues = new ArrayList<>();

			try {

				Fillo fillo = new Fillo();

//				Connection connection = fillo.getConnection(".\\src\\test\\java\\testdata\\TEST\\TestData.xlsx");
				Connection connection = fillo.getConnection(System.getProperty("user.dir") + TestDataPath);
				String query = String.format("SELECT %s FROM %s", "Team_Name", "TeamNotification");
				Recordset recordset = connection.executeQuery(query);

				while (recordset.next()) {
					String cellValue = recordset.getField("Team_Name");
					columnValues.add(cellValue);
				}

				recordset.close();
				connection.close();

			} catch (FilloException e) {

				e.printStackTrace();

			}



			int lastRowNum = 0;
			for(int i=0; i<columnValues.size();i++) {

				if(columnValues.get(i).isEmpty())
				{
					break;
				}

				lastRowNum++;

			}

			//            System.out.println("lastRowNum : "+lastRowNum);


			for (int i=0; i<columnValues.size();i++) {

				Map<String, String> testData2 = getDataInMap("TeamNotification", columnValues.get(i));

				if(testData2.get("Notify_Team").equalsIgnoreCase("YES")) {

					URL url = new URL(testData2.get("Teams_Webhook_URL"));
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");

					String jsonMessage = "{ \"text\": \"" + message + "\" }";

					try (OutputStream outputStream = conn.getOutputStream()) {
						byte[] input = jsonMessage.getBytes(StandardCharsets.UTF_8);
						outputStream.write(input, 0, input.length);
					}

					int httpResponseCode = conn.getResponseCode();
					if (httpResponseCode == HttpURLConnection.HTTP_OK) {
						// Notification sent successfully
//						System.out.println("Notification sent to \"" + columnValues.get(i) + "\" Team channel");
					} else {
						// Handle notification sending failure
//						System.out.println("Failed to send notification to Teams channel.");
					}

				}
				else {
//					System.out.println("Notification not sent to \"" + columnValues.get(i) + "\" Team channel");

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getDataInMap(String SheetName, String teamName) throws Exception {

		Map<String, String> TestDatainMap = new TreeMap<String, String>();

		// String testDataFile=TestDataPath;
		String query = null;

		if (SheetName.equals("Login"))
			query = String.format("SELECT * from %s where ENVIRONMENT = '%s'", SheetName, teamName);
		else
			query = String.format("SELECT * from %s where Team_Name = '%s'", SheetName, teamName);

		Fillo fillo = new Fillo();
		Connection conn = null;
		Recordset recodset = null;
		try {
			conn = fillo.getConnection(System.getProperty("user.dir") + TestDataPath);
			recodset = conn.executeQuery(query);
			while (recodset.next()) {
				for (String field : recodset.getFieldNames()) {
					TestDatainMap.put(field, recodset.getField(field));

				}
			}
		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data can't found");
		}
		conn.close();

		return TestDatainMap;

	}



}
