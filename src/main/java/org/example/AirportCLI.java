package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AirportCLI {
    private final String originalWorkingDirectory;
    private final Map<String, City> cities = new HashMap<>();
    private final Map<String, Airport> airports = new HashMap<>();
    private final Map<String, Aircraft> aircrafts = new HashMap<>();
    private final Map<String, Passenger> passengers = new HashMap<>();

    public AirportCLI() {
        // Store the original working directory
        originalWorkingDirectory = System.getProperty("user.dir");

        // Change the working directory to src/main/java/com/example/finalsprintjavaapp
        String currentDir = originalWorkingDirectory;
        String newDir = currentDir + "/src/main/java/com/example/finalsprintjavaapp";
        System.setProperty("user.dir", newDir);

        // Print the new working directory for verification
        System.out.println("New working directory: " + System.getProperty("user.dir"));

        // Now you can proceed with your existing code
        loadData();
    }

    private void restoreOriginalWorkingDirectory() {
        System.setProperty("user.dir", originalWorkingDirectory);
    }

    private void loadData() {
        try {
            System.out.println("Current working directory: " + System.getProperty("user.dir"));

            // Load cities
            List<String> cityLines = Files.readAllLines(Paths.get("src/main/java/com/example/finalsprintjavaapp/cities.txt"));

            for (String line : cityLines) {
                cities.put(line, new City(line));
            }

            // Load airports
            List<String> airportLines = Files.readAllLines(Paths.get("src/main/java/com/example/finalsprintjavaapp/airports.txt"));
            for (String line : airportLines) {
                String[] data = line.split(",");
                airports.put(data[0], new Airport(data[0], cities.get(data[1])));
            }

            // Load aircrafts
            List<String> aircraftLines = Files.readAllLines(Paths.get("src/main/java/com/example/finalsprintjavaapp/aircraft.txt"));
            for (String line : aircraftLines) {
                String[] data = line.split(",");
                Aircraft aircraft = new Aircraft(data[0], airports.get(data[1]), null);
                aircrafts.put(data[0], aircraft);
            }

            // Load passengers

            List<String> passengerLines = Files.readAllLines(Paths.get("src/main/java/com/example/finalsprintjavaapp/passengers.txt"));
            for (String line : passengerLines) {
                String[] data = line.split(",");
                Passenger passenger = new Passenger(data[0], cities.get(data[1]));
                passengers.put(data[0], passenger);

                if (data.length > 2 && aircrafts.containsKey(data[2])) {
                    aircrafts.get(data[2]).setPassenger(passenger);
                    passenger.setAircraft(aircrafts.get(data[2]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Restore the original working directory
            restoreOriginalWorkingDirectory();
        }
    }

    public void printAirportsInCities() {
        System.out.println("Airports in Cities:");
        for (City city : cities.values()) {
            System.out.println(city.getName() + ": " + city.getAirports());
        }
    }

    public void printAircraftPassengers() {
        System.out.println("Aircraft Passengers:");
        for (Aircraft aircraft : aircrafts.values()) {
            System.out.println(aircraft.getType() + ": " + aircraft.getPassenger());
        }
    }

    public void printAirportsForAircraft() {
        System.out.println("Airports for Aircraft:");
        for (Aircraft aircraft : aircrafts.values()) {
            System.out.println(aircraft.getType() + ": " + aircraft.getAirport());
        }
    }

    public void printPassengerAirports() {
        System.out.println("Airports used by Passengers:");
        for (Passenger passenger : passengers.values()) {
            System.out.println(passenger.getName() + ": " + passenger.getCity().getAirports());
        }
    }

    public static void main(String[] args) {
        AirportCLI airportCLI = new AirportCLI();
        airportCLI.runCommandLoop();
    }

    private void runCommandLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please provide a command. Use one of: airportsInCities, aircraftPassengers, airportsForAircraft, passengerAirports, exit");
            String input = scanner.nextLine();

            switch (input) {
                case "airportsInCities":
                    printAirportsInCities();
                    break;
                case "aircraftPassengers":
                    printAircraftPassengers();
                    break;
                case "airportsForAircraft":
                    printAirportsForAircraft();
                    break;
                case "passengerAirports":
                    printPassengerAirports();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    public static class Passenger {

        private final String name;
        private final City city;
        private Aircraft aircraft;

        public Passenger(String name, City city) {
            this.name = name;
            this.city = city;
            city.addHabitant(this);
        }

        public String getName() {
            return name;
        }

        public City getCity() {
            return city;
        }

        public Aircraft getAircraft() {
            return aircraft;
        }

        public void setAircraft(Aircraft aircraft) {
            this.aircraft = aircraft;
        }
    }

    public static class City {

        private final String name;
        private final List<Airport> airports = new ArrayList<>();
        private final List<Passenger> habitants = new ArrayList<>(); // Added habitants list

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Airport> getAirports() {
            return airports;
        }

        public void addAirport(Airport airport) {
            airports.add(airport);
        }

        public List<Passenger> getHabitants() {
            return habitants;
        }

        public void addHabitant(Passenger passenger) {
            habitants.add(passenger);
        }
    }

    public class Airport {

        private final String name;
        private final City city;
        private final List<Aircraft> aircraftList = new ArrayList<>();

        public Airport(String name, City city) {
            this.name = name;
            this.city = city;
            city.addAirport(this);
        }

        public String getName() {
            return name;
        }

        public City getCity() {
            return city;
        }

        public List<Aircraft> getAircraft() {
            return aircraftList;
        }

        public void addAircraft(Aircraft aircraft) {
            aircraftList.add(aircraft);
        }
    }

    public static class Aircraft {

        private final String type;
        private final Airport airport;
        private Passenger passenger;

        public Aircraft(String type, Airport airport, Passenger passenger) {
            this.type = type;
            this.airport = airport;
            this.passenger = passenger;
            airport.addAircraft(this);
        }

        public String getType() {
            return type;
        }

        public Airport getAirport() {
            return airport;
        }

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }
    }
}