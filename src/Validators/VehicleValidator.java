package Validators;

import Vehicles.Vehicle;

/**
 * Single Responsibility Principle (SRP): This class has ONE job - validate vehicle data
 * Think of this like Laravel's Form Request validation
 */
public class VehicleValidator {

    /**
     * Validates a vehicle object
     * Similar to Laravel's $request->validate()
     */
    public ValidationResult validate(Vehicle vehicle) {
        ValidationResult result = new ValidationResult();

        if (vehicle == null) {
            result.addError("Vehicle cannot be null.");
            return result;
        }

        if (vehicle.getName() == null || vehicle.getName().trim().isEmpty()) {
            result.addError("Vehicle name cannot be empty.");
        }

        if (vehicle.getBrand() == null || vehicle.getBrand().trim().isEmpty()) {
            result.addError("Vehicle brand cannot be empty.");
        }

        if (vehicle.getPrice() <= 0) {
            result.addError("Vehicle price must be greater than zero.");
        }

        return result;
    }
}

