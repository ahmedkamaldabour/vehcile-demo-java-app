import Manager.AddVehicle;
import Manager.ListAllVehicle;
import Repository.JsonVehicleRepository;
import Repository.VehicleRepositoryInterface;
import Services.VehicleInputService;
import Services.VehicleService;
import Validators.VehicleValidator;

import java.util.Scanner;

/**
 * The Entry point for the application
 * This is like Laravel's routes file - it wires everything together
 */
public class ScanData {

    private final Scanner scanner = new Scanner(System.in);
    private boolean hasScanned = true;

    // Dependency Injection - create all dependencies once
    private final VehicleRepositoryInterface repository;
    private final VehicleValidator validator;
    private final VehicleService vehicleService;
    private final VehicleInputService inputService;
    private final AddVehicle addVehicle;
    private final ListAllVehicle listAllVehicle;

    /**
     * Constructor - Bootstrap all dependencies
     * Like Laravel's Service Container binding
     */
    public ScanData() {
        // Create dependencies (bottom-up)
        this.repository = new JsonVehicleRepository("src/vehicles.json");
        this.validator = new VehicleValidator();
        this.vehicleService = new VehicleService(repository, validator);
        this.inputService = new VehicleInputService(scanner);

        // Create controllers/managers
        this.addVehicle = new AddVehicle(vehicleService, inputService);
        this.listAllVehicle = new ListAllVehicle(repository);
    }

    public void start() {
        while (hasScanned) {
            vehicleUI();

            int choice = scanner.nextInt();
            System.out.println("You selected option: " + choice);

            switch (choice) {
                case 1:
                    System.out.println("Listing all vehicles...");
                    listAllVehicle.listVehicles();
                    break;
                case 2:
                    System.out.println("Adding a new vehicle...");
                    addVehicle.addVehicle();
                    break;
                case 3:
                    System.out.println("Removing a vehicle...");
                    break;
                case 4:
                    System.out.println("Renting a vehicle...");
                    break;
                case 5:
                    System.out.println("Returning a vehicle...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (choice == 6 || choice < 1 || choice > 6) {
                hasScanned = false;
            }
        }
    }
    
    private void vehicleUI() {
        System.out.println("\n======================");
        System.out.println("Vehicle Rental System");
        System.out.println("======================");
        System.out.println("1. List Vehicles");
        System.out.println("2. Add a Vehicle");
        System.out.println("3. Remove a Vehicle");
        System.out.println("4. Rent a Vehicle");
        System.out.println("5. Return a Vehicle");
        System.out.println("6. Exit");

        System.out.print("Enter your choice: ");
    }
}
