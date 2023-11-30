import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PostmanCLI {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();

            // Replace these URLs with your actual Postman URLs
            String citiesUrl = "http://localhost:8080/cities";
            String airportsUrl = "http://localhost:8080/airports";
            String aircraftUrl = "http://localhost:8080/aircraft";
            String passengersUrl = "http://localhost:8080/passengers";

            // Make HTTP requests and print the responses
            String citiesResponse = makeRequest(httpClient, citiesUrl);
            String airportsResponse = makeRequest(httpClient, airportsUrl);
            String aircraftResponse = makeRequest(httpClient, aircraftUrl);
            String passengersResponse = makeRequest(httpClient, passengersUrl);

            // Process and print the responses as needed
            System.out.println("Cities: " + citiesResponse);
            System.out.println("Airports: " + airportsResponse);
            System.out.println("Aircraft: " + aircraftResponse);
            System.out.println("Passengers: " + passengersResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String makeRequest(HttpClient httpClient, String url) throws Exception {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }
}
