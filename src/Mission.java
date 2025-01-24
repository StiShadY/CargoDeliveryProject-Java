//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Represents a mission to transport packages between distribution centers according to
// specified instructions.
//-----------------------------------------------------

import java.util.Arrays;

public class Mission {
    private DistributionCenter source;
    private DistributionCenter middle;
    private DistributionCenter destination;
    private int sourcePackagesCount;
    private int middlePackagesCount;
    private int[] dropOffIndices;

    // Constructor to initialize a Mission with the required parameters
    public Mission(DistributionCenter source, DistributionCenter middle, DistributionCenter destination,
                   int sourcePackagesCount, int middlePackagesCount, int[] dropOffIndices) {
        this.source = source;
        this.middle = middle;
        this.destination = destination;
        this.sourcePackagesCount = sourcePackagesCount;
        this.middlePackagesCount = middlePackagesCount;
        this.dropOffIndices = dropOffIndices;
    }

    // Executes the mission based on the defined steps
    public void executeMission() {
        // Step 1: Assemble a vehicle at the source distribution center
        Vehicle vehicle = source.removeVehicle(); // Get the first available vehicle
        if (vehicle == null) {
            System.out.println("No vehicles available at source center: " + source.getCityName());
            return;
        }

        // Load cargo packages from the source distribution center
        for (int i = 0; i < sourcePackagesCount; i++) {
            CargoPackages cargo = source.removeCargoPackage();
            if (cargo != null) {
                vehicle.addCargo(cargo);
            }
        }

        // Step 2: Add the middle distribution center cargo packages
        for (int i = 0; i < middlePackagesCount; i++) {
            CargoPackages cargo = middle.removeCargoPackage();
            if (cargo != null) {
                vehicle.addCargo(cargo);
            }
        }

        // Step 3: Drop off specific cargo packages at the middle distribution center
        for (int index : dropOffIndices) {
            CargoPackages droppedCargo = vehicle.removeCargo(index);
            if (droppedCargo != null) {
                middle.addCargoPackage(droppedCargo);
            }
        }

        // Step 4: Complete the mission by moving the vehicle to the destination distribution center
        destination.addVehicle(vehicle); // Place the vehicle at the destination
    }

    // Returns a string representation of the Mission
    @Override
    public String toString() {
        return "Mission{" +
                "source=" + source.getCityName() +
                ", middle=" + middle.getCityName() +
                ", destination=" + destination.getCityName() +
                ", sourcePackagesCount=" + sourcePackagesCount +
                ", middlePackagesCount=" + middlePackagesCount +
                ", dropOffIndices=" + Arrays.toString(dropOffIndices) +
                '}';
    }
}
