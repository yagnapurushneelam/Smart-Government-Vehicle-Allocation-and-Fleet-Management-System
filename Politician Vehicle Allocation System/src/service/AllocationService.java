
package service;

import java.util.ArrayList;
import java.util.Comparator;
import java. util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import model.*;
import interfaces.VehicleAllocation;
import exception.VehicleUnavailableException;

public class AllocationService implements VehicleAllocation {

    private final PoliticianService politicianService;
    private final VehicleService vehicleService;

    private final Queue<VehicleRequest> pendingRequests =
        new PriorityQueue<>(
            Comparator.comparingInt(
                VehicleRequest::getPriority
            ).reversed()
            .thenComparing(VehicleRequest::getRequestDate)
            .thenComparingInt(VehicleRequest::getRequestId)
        );

    private final List<VehicleRequest> requestHistory =
            new ArrayList<>();

    private final TreeMap<Integer, Allocation> allocations =
            new TreeMap<>();

    private int nextAllocationId = 1;

    public AllocationService(
            PoliticianService politicianService,
            VehicleService vehicleService) {

        this.politicianService = politicianService;
        this.vehicleService = vehicleService;
    }

    // Submit request
    public void submitRequest(VehicleRequest request) {

        if (!politicianService.exists(request.getPoliticianId())) {
            System.out.println("Politician not registered!");
            return;
        }

        for (VehicleRequest r : requestHistory) {

            if (r.getRequestId() == request.getRequestId()) {
                System.out.println("Duplicate request ID!");
                return;
            }
        }

        if (hasActiveAllocation(request.getPoliticianId())) {
            System.out.println(
                "Politician already has an active allocation!"
            );
            return;
        }

        pendingRequests.offer(request);
        requestHistory.add(request);

        System.out.println("Request submitted successfully!");
    }

    // Cancel request
    public void cancelRequest(int requestId) {

        for (VehicleRequest request : pendingRequests) {

            if (request.getRequestId() == requestId) {

                pendingRequests.remove(request);
                request.setStatus("CANCELLED");

                System.out.println("Request cancelled.");
                return;
            }
        }

        System.out.println("Pending request not found.");
    }

    // Reject highest-priority pending request
    public void rejectNextRequest(String reason) {

        VehicleRequest request = pendingRequests.poll();

        if (request == null) {
            System.out.println("No pending requests.");
            return;
        }

        request.setStatus("REJECTED: " + reason);

        System.out.println("Request rejected.");
    }

    // Process request
    public void processNextRequest()
            throws VehicleUnavailableException {

        VehicleRequest request = pendingRequests.peek();

        if (request == null) {
            System.out.println("No pending requests.");
            return;
        }

        Politician politician =
            politicianService.searchPolitician(
                request.getPoliticianId()
            );

        if (politician == null) {
            pendingRequests.poll();
            request.setStatus("REJECTED");
            System.out.println("Politician not found.");
            return;
        }

        if (hasActiveAllocation(request.getPoliticianId())) {
            pendingRequests.poll();
            request.setStatus("REJECTED");
            System.out.println("Politician already has a vehicle.");
            return;
        }

        Vehicle vehicle =
            vehicleService.findAvailableVehicleByType(
                request.getRequestedVehicleType()
            );

        if (vehicle == null) {
            throw new VehicleUnavailableException(
                "No matching vehicle available. Request remains pending."
            );
        }

        allocateVehicle(politician, vehicle);

        request.setStatus("APPROVED");
        pendingRequests.poll();

        System.out.println("Request approved and vehicle allocated!");
    }

    // Direct allocation
    @Override
    public Allocation allocateVehicle(
            Politician politician,
            Vehicle vehicle)
            throws VehicleUnavailableException {

        if (politician == null || vehicle == null)
            throw new IllegalArgumentException("Invalid details.");

        if (!politicianService.exists(politician.getPoliticianId()))
            throw new IllegalArgumentException("Politician not registered.");

        if (hasActiveAllocation(politician.getPoliticianId()))
            throw new IllegalStateException(
                "Politician already has an active allocation."
            );

        if (!vehicle.isAvailable())
            throw new VehicleUnavailableException("Vehicle unavailable.");

        vehicle.setAvailable(false);

        Allocation allocation = new Allocation(
            nextAllocationId++,
            politician.getPoliticianId(),
            vehicle.getVehicleId()
        );

        allocations.put(
            allocation.getAllocationId(),
            allocation
        );

        return allocation;
    }

    // Check active allocation
    public boolean hasActiveAllocation(int politicianId) {

        for (Allocation a : allocations.values()) {

            if (a.getPoliticianId() == politicianId &&
                a.isActive()) {

                return true;
            }
        }

        return false;
    }

    // Return vehicle
    @Override
    public void returnVehicle(int allocationId) {

        Allocation allocation = allocations.get(allocationId);

        if (allocation == null || !allocation.isActive()) {
            System.out.println("Active allocation not found.");
            return;
        }

        Vehicle vehicle =
            vehicleService.searchVehicle(allocation.getVehicleId());

        if (vehicle == null) {
            System.out.println("Vehicle record not found.");
            return;
        }

        allocation.markReturned();
        vehicle.setAvailable(true);

        System.out.println("Vehicle returned successfully!");
    }

    // Display pending requests
    public void displayPendingRequests() {

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        List<VehicleRequest> sorted =
                new ArrayList<>(pendingRequests);

        sorted.sort((Comparator<? super VehicleRequest>) pendingRequests.iterator());

        for (VehicleRequest request : sorted) {
            request.displayDetails();
        }
    }

    // Request history
    public void displayRequestHistory() {

        for (VehicleRequest request : requestHistory) {
            request.displayDetails();
        }

        if (requestHistory.isEmpty())
            System.out.println("No request history.");
    }

    // Allocation history
    @Override
    public void displayAllocations() {

        if (allocations.isEmpty()) {
            System.out.println("No allocation records.");
            return;
        }

        for (Allocation a : allocations.values()) {
            a.displayDetails();
        }
    }

    public void displayActiveAllocations() {

        boolean found = false;

        for (Allocation a : allocations.values()) {

            if (a.isActive()) {
                a.displayDetails();
                found = true;
            }
        }

        if (!found)
            System.out.println("No active allocations.");
    }
}
