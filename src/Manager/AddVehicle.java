package Manager;

import Services.VehicleInputService;
import Services.VehicleService;
import Vehicles.Vehicle;

/**
 * REFACTORED using SOLID Principles,
 * This class is now a Controller/Manager that coordinates between services
 * Think of this like a Laravel Controller
 *
 * Single Responsibility: Coordinate the add vehicle flow
 * Dependency Inversion: Depends on abstractions (services) not concrete implementations
 */
public class AddVehicle {

    private final VehicleService vehicleService;
    private final VehicleInputService inputService;

    /**
     * Dependency Injection through constructor
     * Like Laravel: public function __construct(VehicleService $service)
     */
    public AddVehicle(VehicleService vehicleService, VehicleInputService inputService) {
        this.vehicleService = vehicleService;
        this.inputService = inputService;
    }

    /**
     * Main method to add a vehicle
     * This coordinates the entire flow: input -> validate -> save
     */
    public void addVehicle() {
        System.out.println("\n=== Add New Vehicle ===");

        // Get vehicle data from user
        Vehicle vehicle = inputService.getVehicleFromUser();

        // Use service to add vehicle (handles validation and saving)
        vehicleService.addVehicle(vehicle);
    }
}
