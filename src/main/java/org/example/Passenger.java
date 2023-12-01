package org.example;

public class Passenger {
    private String name;
    private City city;
    private Aircraft aircraft;

    public Passenger(String name, City city, Aircraft aircraft) {
        this.name = name;
        this.city = city;
        this.aircraft = aircraft;
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
}
