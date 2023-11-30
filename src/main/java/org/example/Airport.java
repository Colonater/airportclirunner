package org.example;

public class Airport {
    private String code;
    private City city;

    public Airport(String code, City city) {
        this.code = code;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public City getCity() {
        return city;
    }
}
