package Services;

import Repository.VehicleRepositoryInterface;
import Validators.ValidationResult;
import Validators.VehicleValidator;
import Vehicles.Vehicle;

import java.util.List;

/**
 * Single Responsibility: Business logic for adding vehicles
 * Open/Closed Principle: Open for extension (can add more features) but closed for modification
 * Dependency Inversion: Depends on VehicleRepositoryInterface (abstraction) not concrete class
 *
 * This is like Laravel's Service class that handles business logic
 */
public class VehicleService {

    private final VehicleRepositoryInterface repository;
    private final VehicleValidator validator;

    /**
     * Dependency Injection through constructor
     * Just like Laravel's constructor injection: __construct(VehicleRepository $repository)
     */
    public VehicleService(VehicleRepositoryInterface repository, VehicleValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    public void listVehicles() {
        List<Vehicle> vehicles = repository.findAll();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found in the system.");
            return;
        }

        System.out.println("Total vehicles: " + vehicles.size());
        System.out.println("-------------------");

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.println((i + 1) + ". [" + vehicle.getUuid() + "] ");
            System.out.println(("   name") + ". " + vehicle.getName());
            System.out.println("   Brand: " + vehicle.getBrand());
            System.out.println("   Price: $" + String.format("%.2f", vehicle.getPrice()));
            System.out.println();
        }
    }
    /**
     * Add a vehicle with validation
     * Returns true if successful, false otherwise
     */
    public void addVehicle(Vehicle vehicle) {
        // Validate the vehicle
        ValidationResult validationResult = validator.validate(vehicle);

        if (!validationResult.isValid()) {
            System.out.println("Validation failed:");
            for (String error : validationResult.getErrors()) {
                System.out.println("  - " + error);
            }
            return;
        }

        // Save to repository
        boolean saved = repository.save(vehicle);

        if (saved) {
            System.out.println("✓ Vehicle added successfully!");
            System.out.println("  Name: " + vehicle.getName());
            System.out.println("  Brand: " + vehicle.getBrand());
            System.out.println("  Price: $" + String.format("%.2f", vehicle.getPrice()));
        } else {
            System.out.println("✗ Failed to save vehicle to storage.");
        }
    }



    public void updateVehicle(Vehicle vehicle) {
        // Validate the vehicle
        ValidationResult validationResult = validator.validate(vehicle);

        if (!validationResult.isValid()) {
            System.out.println("Validation failed:");
            for (String error : validationResult.getErrors()) {
                System.out.println("  - " + error);
            }
            return;
        }

        // Update in repository
        boolean updated = repository.update(vehicle);

        if (updated) {
            System.out.println("✓ Vehicle updated successfully!");
            System.out.println("  Name: " + vehicle.getName());
            System.out.println("  Brand: " + vehicle.getBrand());
            System.out.println("  Price: $" + String.format("%.2f", vehicle.getPrice()));
        } else {
            System.out.println("✗ Failed to update vehicle in storage.");
        }
    }

    public boolean deleteVehicleById(String vehicleId) {
        boolean deleted = repository.delete(vehicleId);

        if (deleted) {
            System.out.println("✓ Vehicle deleted successfully!");
        } else {
            System.out.println("✗ Vehicle with ID " + vehicleId + " not found.");
        }

        return deleted;
    }
}

