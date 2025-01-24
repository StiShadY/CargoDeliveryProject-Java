//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Represents a delivery vehicle, which is stored in a DistributionCenter's
// queue and can carry a list of cargo packages.
//-----------------------------------------------------

public class Vehicle extends CargoPackages {
    private DoublyLinkedList<CargoPackages> cargoList;

    public Vehicle(int id, String content) {
        super(id, content);
        this.cargoList = new DoublyLinkedList<>();
    }

    public void addCargo(CargoPackages cargo) {
        cargoList.addLast(cargo);
    }

    public CargoPackages removeCargo(int index) {
        return cargoList.removeAt(index); // Uses removeAt instead of accessing Node directly
    }

    @Override
    public String toString() {
        return "Vehicle{id=" + getId() + ", cargoList=" + cargoList + "}";
    }
}
