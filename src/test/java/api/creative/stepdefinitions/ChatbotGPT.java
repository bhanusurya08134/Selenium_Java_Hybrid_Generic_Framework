package api.creative.stepdefinitions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatbotGPT {

    public static String queryChatbotGPT(String inputText) {
        // API endpoint for sending requests to the Chatbot GPT service
        String apiEndpoint = "https://chatbot-gpt-service.com/api/chat";

        try {
            // Create the URL object
            URL url = new URL(apiEndpoint);

            // Create the HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set the content type
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable input and output streams
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Create the request payload
            String payload = "{\"input\": \"" + inputText + "\"}";

            // Write the payload to the request body
            connection.getOutputStream().write(payload.getBytes("UTF-8"));

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the response JSON and extract the generated response
                String generatedResponse = response.toString();

                // Return the generated response
                return generatedResponse;
            } else {
                // Handle error cases
                System.out.println("Request failed with response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }

    // Usage example in your automation framework
    public static void main(String[] args) {
        // Test step 1: Send input to Chatbot GPT and receive response
        String inputText = "Hello, how are you?";
        String response = queryChatbotGPT(inputText);

        // Test step 2: Validate the response
        String expectedResponse = "I'm good, thank you!";
        if (response != null && response.equals(expectedResponse)) {
            System.out.println("Response matches the expected value");
        } else {
            System.out.println("Response doesn't match the expected value");
        }

        // Continue with the rest of your test scenario
    }
}
