
package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

import model.Vehicle;

public class VehicleService {

    private final List<Vehicle> vehicles = new ArrayList<>();

    private final Set<String> registrationNumbers = new HashSet<>();

    // Add vehicle
    public void addVehicle(Vehicle vehicle) {

        if (searchVehicle(vehicle.getVehicleId()) != null) {
            System.out.println("Vehicle ID already exists!");
            return;
        }

        if (!registrationNumbers.add(vehicle.getRegistrationNumber())) {
            System.out.println("Registration number already exists!");
            return;
        }

        vehicles.add(vehicle);

        System.out.println("Vehicle added successfully!");
    }

    // Search by ID
    public Vehicle searchVehicle(int id) {

        for (Vehicle v : vehicles) {

            if (v.getVehicleId() == id)
                return v;
        }

        return null;
    }

    // Overloaded search by registration
    public Vehicle searchVehicle(String registrationNumber) {

        if (registrationNumber == null)
            return null;

        for (Vehicle v : vehicles) {

            if (v.getRegistrationNumber()
                    .equalsIgnoreCase(registrationNumber.trim()))
                return v;
        }

        return null;
    }

    // Remove vehicle using Iterator
    public void removeVehicle(int id) {

        Iterator<Vehicle> iterator = vehicles.iterator();

        while (iterator.hasNext()) {

            Vehicle v = iterator.next();

            if (v.getVehicleId() == id) {

                if (!v.isAvailable()) {
                    System.out.println(
                        "Cannot remove allocated or maintenance vehicle."
                    );
                    return;
                }

                iterator.remove();

                registrationNumbers.remove(
                    v.getRegistrationNumber()
                );

                System.out.println("Vehicle removed successfully!");
                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

    // Display all vehicles
    public void displayAllVehicles() {

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("----------------------");
        }
    }

    // Available vehicles
    public List<Vehicle> getAvailableVehicles() {

        List<Vehicle> available = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v.isAvailable())
                available.add(v);
        }

        return available;
    }

    public Vehicle findAvailableVehicleByType(String type) {

        for (Vehicle v : vehicles) {

            if (v.isAvailable() &&
                v.getVehicleType().equalsIgnoreCase(type.trim())) {

                return v;
            }
        }

        return null;
    }

    public void displayAvailableVehicles() {

        List<Vehicle> available = getAvailableVehicles();

        if (available.isEmpty()) {
            System.out.println("No available vehicles.");
            return;
        }

        for (Vehicle v : available) {
            v.displayDetails();
            System.out.println("----------------------");
        }
    }

    // Sort using Comparator
    public void sortVehiclesByBrand() {

        List<Vehicle> sorted = new ArrayList<>(vehicles);

        sorted.sort(
            Comparator.comparing(
                Vehicle::getBrand,
                String.CASE_INSENSITIVE_ORDER
            )
        );
        
 
        
        
        for (Vehicle v : sorted) {
            System.out.println(
                v.getBrand() + " " +
                v.getModel() + " - " +
                v.getRegistrationNumber()
            );
        }
    }

    // Maintenance management
    public void startMaintenance(int id) {

        Vehicle v = searchVehicle(id);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        try {
            v.startMaintenance();
            System.out.println("Vehicle sent for maintenance.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void completeMaintenance(int id) {

        Vehicle v = searchVehicle(id);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        try {
            v.completeMaintenance();
            System.out.println("Maintenance completed.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
