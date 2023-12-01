//process data from api responses

package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    private List<City> cities;
    private List<Airport> airports;
    private List<Aircraft> aircraftList;
    private List<Passenger> passengers;

    public DataProcessor(List<City> cities, List<Airport> airports, List<Aircraft> aircraftList, List<Passenger> passengers) {
        this.cities = cities;
        this.airports = airports;
        this.aircraftList = aircraftList;
        this.passengers = passengers;
    }

    public void processRoutes() {
        // Implement logic to process routes and establish relationships
        for (Passenger passenger : passengers) {
            System.out.println(passenger.getName() + " is in " + passenger.getCity().getName() +
                    " and flies on " + passenger.getAircraft().getType() +
                    " from " + passenger.getAircraft().getAirport());
        }
    }

    public List<Airport> getAirportsInCity(String cityName) {
        List<Airport> airportsInCity = new ArrayList<>();
        for (Airport airport : airports) {
            if (airport.getCity().getName().equalsIgnoreCase(cityName)) {
                airportsInCity.add(airport);
            }
        }
        return airportsInCity;
    }

    public List<Aircraft> getAircraftForPassengers() {
        List<Aircraft> aircraftForPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            aircraftForPassengers.add(passenger.getAircraft());
        }
        return aircraftForPassengers;
    }

    public List<Airport> getAirportsForPassengers() {
        List<Airport> airportsForPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            airportsForPassengers.add(passenger.getAircraft().getAirport());
        }
        return airportsForPassengers;
    }
}
