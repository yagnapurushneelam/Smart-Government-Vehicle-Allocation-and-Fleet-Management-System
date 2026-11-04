
package model;

import java.time.LocalDate;

public class Allocation {

    private final int allocationId;
    private final int politicianId;
    private final int vehicleId;
    private final LocalDate allocationDate;

    private LocalDate returnDate;
    private boolean active = true;

    public Allocation(int allocationId, int politicianId, int vehicleId) {

        this.allocationId = allocationId;
        this.politicianId = politicianId;
        this.vehicleId = vehicleId;
        this.allocationDate = LocalDate.now();
    }

    public int getAllocationId() {
        return allocationId;
    }

    public int getPoliticianId() {
        return politicianId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return active;
    }

    public void markReturned() {

        if (!active)
            throw new IllegalStateException("Already returned");

        returnDate = LocalDate.now();
        active = false;
    }

    public void displayDetails() {

        System.out.println("Allocation ID: " + allocationId);
        System.out.println("Politician ID: " + politicianId);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Allocation Date: " + allocationDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Active: " + active);
    }
}
