package main;
import model.*;
import service.*;
import util.InputUtil;
import exception.VehicleUnavailableException;

public class Main {

    private static final PoliticianService ps =
            new PoliticianService();

    private static final VehicleService vs =
            new VehicleService();

    private static final AllocationService as =
            new AllocationService(ps, vs);

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            choice = InputUtil.readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        registerPolitician();
                        break;

                    case 2:
                        ps.displayPoliticians();
                        break;

                    case 3:
                        searchPolitician();
                        break;

                    case 4:
                        addVehicle();
                        break;

                    case 5:
                        vs.displayAllVehicles();
                        break;

                    case 6:
                        vs.displayAvailableVehicles();
                        break;

                    case 7:
                        searchVehicle();
                        break;

                    case 8:
                        removeVehicle();
                        break;

                    case 9:
                        submitRequest();
                        break;

                    case 10:
                        as.displayPendingRequests();
                        break;

                    case 11:
                        processRequest();
                        break;

                    case 12:
                        rejectRequest();
                        break;

                    case 13:
                        cancelRequest();
                        break;

                    case 14:
                        as.displayRequestHistory();
                        break;

                    case 15:
                        as.displayAllocations();
                        break;

                    case 16:
                        as.displayActiveAllocations();
                        break;

                    case 17:
                        returnVehicle();
                        break;

                    case 18:
                        vs.sortVehiclesByBrand();
                        break;

                    case 19:
                        vs.startMaintenance(
                            InputUtil.readInt("Vehicle ID: ")
                        );
                        break;

                    case 20:
                        vs.completeMaintenance(
                            InputUtil.readInt("Vehicle ID: ")
                        );
                        break;

                    case 0:
                        System.out.println(
                            "Thank you for using the system!"
                        );
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (IllegalArgumentException |
                     IllegalStateException e) {

                System.out.println("Error: " + e.getMessage());
            } catch (VehicleUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } while (choice != 0);
    }

    // Display menu
    public static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println(" POLITICIAN VEHICLE ALLOCATION SYSTEM");
        System.out.println("==========================================");

        System.out.println("1. Register Politician");
        System.out.println("2. View All Politicians");
        System.out.println("3. Search Politician");

        System.out.println("4. Add Government Vehicle");
        System.out.println("5. View All Vehicles");
        System.out.println("6. View Available Vehicles");
        System.out.println("7. Search Vehicle");
        System.out.println("8. Remove Vehicle");

        System.out.println("9. Submit Vehicle Request");
        System.out.println("10. View Pending Requests");
        System.out.println("11. Approve / Process Request");
        System.out.println("12. Reject Next Request");
        System.out.println("13. Cancel Request");
        System.out.println("14. Request History");

        System.out.println("15. Allocation History");
        System.out.println("16. Active Allocations");
        System.out.println("17. Return Vehicle");

        System.out.println("18. Sort Vehicles by Brand");
        System.out.println("19. Send Vehicle for Maintenance");
        System.out.println("20. Complete Maintenance");

        System.out.println("0. Exit");

        System.out.println("==========================================");
    }

    // Register politician
    public static void registerPolitician() {

        int id = InputUtil.readInt("Politician ID: ");

        String name = InputUtil.readString("Name: ");

        String constituency =
                InputUtil.readString("Constituency: ");

        System.out.println("1. MLA");
        System.out.println("2. MP");
        System.out.println("3. Minister");

        int type = InputUtil.readInt("Select designation: ");

        Politician politician;

        switch (type) {

            case 1:

                int assembly = InputUtil.readInt(
                    "Assembly Number: "
                );

                politician = new MLA(
                    id, name, constituency, assembly
                );

                break;

            case 2:

                String house = InputUtil.readString(
                    "House (Lok Sabha/Rajya Sabha): "
                );

                politician = new MP(
                    id, name, constituency, house
                );

                break;

            case 3:

                String ministry = InputUtil.readString(
                    "Ministry: "
                );

                politician = new Minister(
                    id, name, constituency, ministry
                );

                break;

            default:

                System.out.println("Invalid designation!");
                return;
        }

        ps.addPolitician(politician);
    }

    // Search politician
    public static void searchPolitician() {

        int id = InputUtil.readInt("Politician ID: ");

        Politician p = ps.searchPolitician(id);

        if (p != null) {
            p.displayDetails();
        } else {
            System.out.println("Politician not found!");
        }
    }

    // Add vehicle
    public static void addVehicle() {

        int id = InputUtil.readInt("Vehicle ID: ");

        String registration =
                InputUtil.readString("Registration Number: ");

        String brand =
                InputUtil.readString("Brand: ");

        String model =
                InputUtil.readString("Model: ");

        String type =
                InputUtil.readString("Vehicle Type: ");

        Vehicle vehicle = new Vehicle(
            id, registration, brand, model, type
        );

        vs.addVehicle(vehicle);
    }

    // Search vehicle
    public static void searchVehicle() {

        String registration =
                InputUtil.readString("Registration Number: ");

        Vehicle v = vs.searchVehicle(registration);

        if (v != null) {
            v.displayDetails();
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    // Remove vehicle
    public static void removeVehicle() {

        int id = InputUtil.readInt("Vehicle ID: ");

        vs.removeVehicle(id);
    }

    // Submit request
    public static void submitRequest() {

        int requestId =
                InputUtil.readInt("Request ID: ");

        int politicianId =
                InputUtil.readInt("Politician ID: ");

        String type =
                InputUtil.readString("Vehicle Type: ");

        String purpose =
                InputUtil.readString("Purpose: ");

        int priority =
                InputUtil.readInt("Priority (1-5): ");

        VehicleRequest request = new VehicleRequest(
            requestId,
            politicianId,
            type,
            purpose,
            priority
        );

        as.submitRequest(request);
    }

    // Process request
    public static void processRequest()
            throws VehicleUnavailableException {

        as.processNextRequest();
    }

    // Reject request
    public static void rejectRequest() {

        String reason =
                InputUtil.readString("Rejection Reason: ");

        as.rejectNextRequest(reason);
    }

    // Cancel request
    public static void cancelRequest() {

        int id = InputUtil.readInt("Request ID: ");

        as.cancelRequest(id);
    }

    
    // Return vehicle
    public static void returnVehicle() {

        int id = InputUtil.readInt("Allocation ID: ");

        as.returnVehicle(id);
    }
}
