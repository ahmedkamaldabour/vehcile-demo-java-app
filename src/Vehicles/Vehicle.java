package Vehicles;

import java.util.UUID;

public class Vehicle {

    private final String uuid;
    private final String name;
    private final String brand;
    private final double price;

    public Vehicle(String name, String brand, double price) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }


    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
}
