// represents an aircraft with a type to model with associated citys



package org.example;

public class Airport {
    private String code;
    private City city;

    public Airport(String code, City city) {
        this.code = code;
        this.city = city;
    }

    // Add this method for deserialization from a String
    public static Airport fromString(String airportString, City city) {
        String[] parts = airportString.split(",");
        String code = parts[0];
        // Update this line to create an Airport instance using the constructor
        return new Airport(code, city);
    }

    public String getCode() {
        return code;
    }

    public City getCity() {
        return city;
    }

}
