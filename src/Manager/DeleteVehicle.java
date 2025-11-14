package Manager;

import Services.VehicleInputService;
import Services.VehicleService;

public class DeleteVehicle {

    private final VehicleService vehicleService;
    private final VehicleInputService vehicleInputService;

    public DeleteVehicle(VehicleService vehicleService, VehicleInputService vehicleInputService) {
        this.vehicleService = vehicleService;
        this.vehicleInputService = vehicleInputService;
    }

    public void deleteVehicle() {
        System.out.println("\n=== Delete Vehicle ===");

        String vehicleId = vehicleInputService.getVehicleId();
        vehicleService.deleteVehicleById(vehicleId);
    }
}
