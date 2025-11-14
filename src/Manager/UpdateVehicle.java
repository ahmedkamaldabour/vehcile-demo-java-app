package Manager;

import Services.VehicleInputService;
import Services.VehicleService;
import Vehicles.Vehicle;

public class UpdateVehicle {

    private final VehicleService vehicleService;
    private final VehicleInputService inputService;

    public UpdateVehicle(VehicleService vehicleService, VehicleInputService inputService) {
        this.vehicleService = vehicleService;
        this.inputService = inputService;
    }

    public void updateVehicle() {
        System.out.println("\n=== Update Vehicle ===");

        // Get vehicle data from user
        Vehicle vehicle = inputService.getVehicleFromUserWithId();

        // Use service to update vehicle (handles validation and updating)
        vehicleService.updateVehicle(vehicle);
    }
}
