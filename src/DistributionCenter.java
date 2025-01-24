//-----------------------------------------------------
// Title: DistributionCenter Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Represents a distribution center in a city, holding cargo packages and vehicles,
// and organizing them based on LIFO (Last In, First Out) for packages and FIFO (First In, First Out) for vehicles.
//-----------------------------------------------------

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class DistributionCenter {
    private String cityName;
    private Stack<CargoPackages> cargoStack;  // Holds packages in a stack structure (LIFO - Last In, First Out)
    private Queue<Vehicle> vehicleQueue;      // Holds vehicles in a queue structure (FIFO - First In, First Out)

    public DistributionCenter(String cityName) {
        this.cityName = cityName;
        this.cargoStack = new Stack<>(); // Initializes the cargo stack
        this.vehicleQueue = new LinkedList<>(); // Initializes the vehicle queue (using LinkedList as Queue implementation)
    }

    // Getter method for cityName
    public String getCityName() {
        return cityName;
    }

    // Adds a cargo package
    public void addCargoPackage(CargoPackages cargo) {
        cargoStack.push(cargo);
    }

    // Removes a cargo package
    public CargoPackages removeCargoPackage() {
        if (!cargoStack.isEmpty()) {
            return cargoStack.pop();
        }
        return null;
    }

    // Adds a vehicle
    public void addVehicle(Vehicle vehicle) {
        vehicleQueue.add(vehicle);
    }

    // Removes a vehicle
    public Vehicle removeVehicle() {
        return vehicleQueue.poll();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(cityName).append("\n");

        // Cargo packages
        result.append("Packages:\n");
        for (int i = cargoStack.size() - 1; i >= 0; i--) { // Print packages in reverse as they are managed in LIFO order
            CargoPackages packageItem = cargoStack.get(i);
            result.append("P").append(packageItem.getId()).append("\n");
        }

        // Vehicles
        result.append("Vehicles:\n");
        for (Vehicle vehicle : vehicleQueue) { // Queue is directly Iterable, so we can use for-each
            result.append("V").append(vehicle.getId()).append("\n");
        }

        result.append("-------------\n");
        return result.toString();
    }
}
