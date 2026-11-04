
package model;

public class Vehicle {

    private final int vehicleId;
    private final String registrationNumber;
    private String brand;
    private String model;
    private String vehicleType;
    private boolean available = true;
    private boolean underMaintenance;

    public Vehicle(int vehicleId, String registrationNumber,
                   String brand, String model, String vehicleType) {

        if (vehicleId <= 0)
            throw new IllegalArgumentException("Invalid vehicle ID");

        if (registrationNumber == null || registrationNumber.isBlank())
            throw new IllegalArgumentException("Invalid registration number");

        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber.trim().toUpperCase();

        setBrand(brand);
        setModel(model);
        setVehicleType(vehicleType);
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return available && !underMaintenance;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank())
            throw new IllegalArgumentException("Invalid brand");

        this.brand = brand.trim();
    }

    public void setModel(String model) {
        if (model == null || model.isBlank())
            throw new IllegalArgumentException("Invalid model");

        this.model = model.trim();
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null || vehicleType.isBlank())
            throw new IllegalArgumentException("Invalid vehicle type");

        this.vehicleType = vehicleType.trim();
    }

    public void setAvailable(boolean available) {

        if (underMaintenance && available)
            throw new IllegalStateException("Vehicle is under maintenance");

        this.available = available;
    }

    public void startMaintenance() {

        if (!isAvailable())
            throw new IllegalStateException(
                "Only an available vehicle can enter maintenance"
            );

        underMaintenance = true;
        available = false;
    }

    public void completeMaintenance() {

        if (!underMaintenance)
            throw new IllegalStateException("Vehicle is not under maintenance");

        underMaintenance = false;
        available = true;
    }

    public void displayDetails() {

        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Registration: " + registrationNumber);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Available: " + isAvailable());
        System.out.println("Under Maintenance: " + underMaintenance);
    }
}
