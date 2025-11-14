package Vehicles;

import java.util.UUID;

public class Vehicle {

    private final String uuid;
    private String name;
    private String brand;
    private double price;

    // saving new Vehicle, UUID is generated automatically
    public Vehicle(String name, String brand, double price) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    // loading existing Vehicle with known UUID
    public Vehicle(String uuid, String name, String brand, double price) {
        this.uuid = uuid;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
