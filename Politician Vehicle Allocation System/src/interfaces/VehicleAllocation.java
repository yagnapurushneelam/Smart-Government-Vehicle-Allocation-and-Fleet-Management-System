
package interfaces;

import model.Politician;
import model.Vehicle;
import model.Allocation;
import exception.VehicleUnavailableException;

public interface VehicleAllocation {

    Allocation allocateVehicle(
        Politician politician,
        Vehicle vehicle
    ) throws VehicleUnavailableException;

    void returnVehicle(int allocationId);

    void displayAllocations();
}
