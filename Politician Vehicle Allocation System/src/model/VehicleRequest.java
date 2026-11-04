
package model;

import java.time.LocalDate;

public class VehicleRequest {

    private final int requestId;
    private final int politicianId;
    private final String requestedVehicleType;
    private final String purpose;
    private final int priority;
    private final LocalDate requestDate;

    private String status = "PENDING";

    public VehicleRequest(int requestId, int politicianId,
                          String requestedVehicleType,
                          String purpose, int priority) {

        if (requestId <= 0 || politicianId <= 0)
            throw new IllegalArgumentException("Invalid ID");

        if (requestedVehicleType == null ||
            requestedVehicleType.isBlank())
            throw new IllegalArgumentException("Vehicle type required");

        if (purpose == null || purpose.isBlank())
            throw new IllegalArgumentException("Purpose required");

        if (priority < 1 || priority > 5)
            throw new IllegalArgumentException("Priority must be 1 to 5");

        this.requestId = requestId;
        this.politicianId = politicianId;
        this.requestedVehicleType = requestedVehicleType.trim();
        this.purpose = purpose.trim();
        this.priority = priority;
        this.requestDate = LocalDate.now();
    }

    public int getRequestId() {
        return requestId;
    }

    public int getPoliticianId() {
        return politicianId;
    }

    public String getRequestedVehicleType() {
        return requestedVehicleType;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayDetails() {

        System.out.println("Request ID: " + requestId);
        System.out.println("Politician ID: " + politicianId);
        System.out.println("Vehicle Type: " + requestedVehicleType);
        System.out.println("Purpose: " + purpose);
        System.out.println("Priority: " + priority);
        System.out.println("Date: " + requestDate);
        System.out.println("Status: " + status);
    }
}
