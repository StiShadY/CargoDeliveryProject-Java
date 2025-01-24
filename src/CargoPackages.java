//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Represents a cargo package that can be stored in a DistributionCenter's stack.
//-----------------------------------------------------

public class CargoPackages {
    private int id; // Unique identifier for each cargo package
    private String content; // Content description of the package

    // Constructor to initialize a CargoPackages with an ID and content
    public CargoPackages(int id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getter for the package ID
    public int getId() {
        return id;
    }

    // Getter for the package content
    public String getContent() {
        return content;
    }

    // Returns a string representation of the CargoPackages
    @Override
    public String toString() {
        return "CargoPackages{id=" + id + ", content='" + content + "'}";
    }
}
