package com.example.kawiarenka;

public class CafeLocation {
    private final int id;
    private final String name;
    private final String address;
    private final String openingHours;

    public CafeLocation(int id, String name, String address, String openingHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getOpeningHours() { return openingHours; }
}
