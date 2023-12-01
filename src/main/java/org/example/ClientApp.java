package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientApp {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();

            // Replace these URLs with your actual Postman URLs
            String citiesUrl = "http://localhost:8080/locations/cities";
            String airportsUrl = "http://localhost:8080/locations/airports";
            String aircraftUrl = "http://localhost:8080/locations/aircraft";
            String passengersUrl = "http://localhost:8080/locations/passengers";

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

            // Create instances of City, Airport, Aircraft, and Passenger based on responses
            ObjectMapper objectMapper = new ObjectMapper();

            List<City> cities = Arrays.asList(objectMapper.readValue(citiesResponse, City[].class));
            List<Airport> airports = Arrays.asList(objectMapper.readValue(airportsResponse, Airport[].class));
            List<Aircraft> aircraftList = Arrays.asList(objectMapper.readValue(aircraftResponse, Aircraft[].class));
            List<Passenger> passengers = Arrays.asList(objectMapper.readValue(passengersResponse, Passenger[].class));

            // Now you have relationships on the client side
            City city1 = cities.get(0);
            City city2 = cities.get(1);

            Airport airport1 = airports.get(0);
            Airport airport2 = airports.get(1);

            Aircraft aircraft1 = aircraftList.get(0);
            Aircraft aircraft2 = aircraftList.get(1);

            Passenger passenger1 = passengers.get(0);
            Passenger passenger2 = passengers.get(1);

            System.out.println("Passenger1 is in " + passenger1.getCity().getName() + " and flies " + passenger1.getAircraft().getType());
            System.out.println("Passenger2 is in " + passenger2.getCity().getName() + " and flies " + passenger2.getAircraft().getType());

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

