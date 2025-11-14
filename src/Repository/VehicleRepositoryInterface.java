package Repository;

import Vehicles.Vehicle;
import java.util.List;

/**
 * Interface Segregation & Dependency Inversion Principles
 * This is like Laravel's Repository Pattern interface
 * Classes depend on abstractions (this interface) not concrete implementations
 */
public interface VehicleRepositoryInterface {
    boolean save(Vehicle vehicle);
    List<Vehicle> findAll();
    Vehicle findById(String id);
    boolean delete(String id);
}

