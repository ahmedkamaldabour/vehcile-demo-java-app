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
 *
 * Uses Gson library for JSON serialization/deserialization
 * Much cleaner and more reliable than manual parsing!
 */
public class JsonVehicleRepository implements VehicleRepositoryInterface {

    private final String filePath;
    private final Gson gson;

    public JsonVehicleRepository(String filePath) {
        this.filePath = filePath;
        // Create Gson with pretty printing for readable JSON files
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
    public List<Vehicle> findAll() {
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }

            try (Reader reader = new FileReader(file)) {
                // Gson magic: One line to convert JSON to List<Vehicle>!
                // This is like Laravel's json_decode($json, true)
                Type vehicleListType = new TypeToken<ArrayList<Vehicle>>(){}.getType();
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
        // Implementation for finding by ID
        return null;
    }

    @Override
    public boolean delete(String id) {
        // Implementation for deletion
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

