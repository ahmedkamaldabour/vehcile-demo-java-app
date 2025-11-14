package Repository;

import Vehicles.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of VehicleRepositoryInterface
 * Single Responsibility: Handle JSON file operations
 * This is like Laravel's Eloquent Model or a custom Repository class
 * <p>
 * Uses Gson library for JSON serialization/deserialization
 * Much cleaner and more reliable than manual parsing!
 */
public class JsonVehicleRepository implements VehicleRepositoryInterface {

    private final String filePath;
    private final Gson gson;

    public JsonVehicleRepository(String filePath) {
        this.filePath = filePath;
        // Check if a file exists, if not, create it with empty array
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                // Create parent directories if they don't exist
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    if (!parentDir.mkdirs()) {
                        System.err.println("Warning: Could not create parent directories for: " + filePath);
                    }
                }
                // Create empty JSON file with empty array
                if (file.createNewFile()) {
                    try (Writer writer = new FileWriter(file)) {
                        writer.write("[]");
                    }
                } else {
                    System.err.println("Warning: File already exists or could not be created: " + filePath);
                }
            } catch (IOException e) {
                System.err.println("Warning: Could not create vehicles file: " + e.getMessage());
            }
        }
        // Create Gson with pretty printing for readable JSON files
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Vehicle> findAll() {
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }

            try (Reader reader = new FileReader(file)) {
                // Gson magic: One line to convert JSON to List<Vehicle>!
                // This is like Laravel's json_decode($json, true)
                Type vehicleListType = new TypeToken<ArrayList<Vehicle>>() {
                }.getType();
                List<Vehicle> vehicles = gson.fromJson(reader, vehicleListType);
                return vehicles != null ? vehicles : new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println("Error reading vehicles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Vehicle findById(String id) {
        List<Vehicle> vehicles = findAll();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getUuid().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public boolean save(Vehicle vehicle) {
        try {
            List<Vehicle> vehicles = findAll();
            vehicles.add(vehicle);
            writeToFile(vehicles);
            return true;
        } catch (Exception e) {
            System.err.println("Error saving vehicle: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Vehicle vehicle) {
        try {
            Vehicle vehicleData = findById(vehicle.getUuid());
            if (vehicleData != null) {
                // update fields
                vehicleData.setName(vehicle.getName());
                vehicleData.setBrand(vehicle.getBrand());
                vehicleData.setPrice(vehicle.getPrice());

                List<Vehicle> vehicles = findAll();
                // replace the old vehicle with updated one without changing list reference and uuid
                for (int i = 0; i < vehicles.size(); i++) {
                    if (vehicles.get(i).getUuid().equals(vehicle.getUuid())) {
                        vehicles.set(i, vehicleData);
                        break;
                    }
                }
                writeToFile(vehicles);
                return true;
            } else {
                System.err.println("Vehicle with ID " + vehicle.getUuid() + " not found.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean delete(String id) {
        try {
            List<Vehicle> vehicles = findAll();
            boolean removed = vehicles.removeIf(vehicle -> vehicle.getUuid().equals(id));
            if (removed) {
                writeToFile(vehicles);
                return true;
            } else {
                System.err.println("Vehicle with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
        }
        return false;
    }

    /**
     * Write vehicles to JSON file using Gson
     * One line instead of 100+ lines of manual string building!
     */
    private void writeToFile(List<Vehicle> vehicles) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            // Gson magic: One line to convert List<Vehicle> to JSON!
            // This is like Laravel's json_encode($vehicles)
            gson.toJson(vehicles, writer);
        }
    }
}

