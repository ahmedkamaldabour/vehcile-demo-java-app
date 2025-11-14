package Manager;

import Repository.VehicleRepositoryInterface;
import Vehicles.Vehicle;

import java.util.List;

/**
 * REFACTORED using SOLID Principles
 * Single Responsibility: Display all vehicles
 * Dependency Inversion: Depends on VehicleRepositoryInterface (abstraction)
 *
 * Think of this like a Laravel Controller action
 */
public class ListAllVehicle {

    private final VehicleRepositoryInterface repository;

    /**
     * Constructor with dependency injection
     * Like Laravel: public function __construct(VehicleRepository $repository)
     */
    public ListAllVehicle(VehicleRepositoryInterface repository) {
        this.repository = repository;
    }

    /**
     * Display all vehicles
     * Like Laravel's index() method in a controller
     */
    public void listVehicles() {
        System.out.println("\n=== All Vehicles ===");

        List<Vehicle> vehicles = repository.findAll();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found in the system.");
            return;
        }

        System.out.println("Total vehicles: " + vehicles.size());
        System.out.println("-------------------");

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.println((i + 1) + ". " + vehicle.getName());
            System.out.println("   Brand: " + vehicle.getBrand());
            System.out.println("   Price: $" + String.format("%.2f", vehicle.getPrice()));
            System.out.println();
        }
    }
}
