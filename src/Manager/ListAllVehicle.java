package Manager;

import Repository.VehicleRepositoryInterface;
import Services.VehicleService;
import Vehicles.Vehicle;

import java.util.List;

/**
 * REFACTORED using SOLID Principles
 * Single Responsibility: Display all vehicles
 * Dependency Inversion: Depends on VehicleRepositoryInterface (abstraction)
 * <p>
 * Think of this like a Laravel Controller action
 */
public class ListAllVehicle {

    private final VehicleService vehicleService;

    /**
     * Constructor with dependency injection
     * Like Laravel: public function __construct(VehicleRepository $repository)
     */
    public ListAllVehicle(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Display all vehicles
     * Like Laravel's index() method in a controller
     */
    public void listVehicles() {
        System.out.println("\n=== All Vehicles ===");
        vehicleService.listVehicles();
    }
}
