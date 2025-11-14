package Services;

import Vehicles.Vehicle;
import java.util.Scanner;

/**
 * Single Responsibility Principle: Handle user input ONLY
 * Think of this like Laravel's Request handling
 */
public class VehicleInputService {

    private final Scanner scanner;

    public VehicleInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gets vehicle details from user input
     * Similar to collecting form data in Laravel
     */
    public Vehicle getVehicleFromUser() {
        // Clear any leftover newline
        scanner.nextLine();

        System.out.print("Enter vehicle name: ");
        String vehicleName = scanner.nextLine().trim();

        System.out.print("Enter vehicle brand: ");
        String vehicleBrand = scanner.nextLine().trim();

        System.out.print("Enter vehicle price: ");
        double vehiclePrice = 0;

        try {
            vehiclePrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Setting price to 0.");
        }

        return new Vehicle(vehicleName, vehicleBrand, vehiclePrice);
    }
}

