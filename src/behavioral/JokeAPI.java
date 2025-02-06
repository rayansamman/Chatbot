package behavioral;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokeAPI {

    private static final String API_URL = "https://official-joke-api.appspot.com/random_joke";

    public String fetchJoke() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return raw JSON for the adapter to parse
            return response.toString();
        } catch (Exception e) {
            return "Error fetching joke: " + e.getMessage();
        }
    }
}
