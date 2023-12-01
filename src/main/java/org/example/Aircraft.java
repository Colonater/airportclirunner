//to model info about different types of aircraft with a type

package org.example;

public class Aircraft {
    private String type;
    private Airport airport;

    public Aircraft(String type, Airport airport) {
        this.type = type;
        this.airport = airport;
    }

    public String getType() {
        return type;
    }

    public Airport getAirport() {
        return airport;
    }
}
