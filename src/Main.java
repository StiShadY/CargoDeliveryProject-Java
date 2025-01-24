//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Acts as the entry point for the program. It loads data from files,
// processes missions, and writes the final output to result.txt.
//-----------------------------------------------------

import java.io.*;
import java.util.*;

public class Main {
    private static Map<String, DistributionCenter> centers = new HashMap<>();

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java Main cities.txt packages.txt vehicles.txt missions.txt result.txt");
            return;
        }

        // Dosya yollarını kontrol etmek için yazdırma
        System.out.println("Loading cities from: " + args[0]);
        System.out.println("Loading packages from: " + args[1]);
        System.out.println("Loading vehicles from: " + args[2]);
        System.out.println("Loading missions from: " + args[3]);
        System.out.println("Writing result to: " + args[4]);

        // Check if each file exists before loading
        if (!checkFileExists(args[0]) || !checkFileExists(args[1]) || !checkFileExists(args[2]) || !checkFileExists(args[3])) {
            return;
        }

        // Load data from files
        loadCities(args[0]);
        loadPackages(args[1]);
        loadVehicles(args[2]);
        List<Mission> missions = loadMissions(args[3]);

        // Execute all missions
        for (Mission mission : missions) {
            mission.executeMission();
        }

        // Write final state to result.txt
        writeResult(args[4]);
    }

    // Helper method to check if a file exists
    public static boolean checkFileExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Error: The file " + fileName + " does not exist.");
            return false;
        }
        return true;
    }

    private static void loadCities(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Loaded city: " + line.trim());  // Dosya içeriğini görmek için
                centers.put(line.trim(), new DistributionCenter(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading cities file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadPackages(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 2) { // Beklenen format "ID Şehir"
                    System.out.println("Warning: Incorrect format in packages file line: " + line);
                    continue;
                }

                int packageId = Integer.parseInt(parts[0].substring(1)); // "P1" -> 1
                String cityName = parts[1].trim();
                String content = "Default Content"; // İçerik için varsayılan değer

                CargoPackages cargo = new CargoPackages(packageId, content);
                DistributionCenter center = centers.get(cityName);
                if (center != null) {
                    center.addCargoPackage(cargo);
                    System.out.println("Loaded package: " + cargo + " for city: " + cityName);
                } else {
                    System.out.println("Warning: City " + cityName + " not found for package " + packageId);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading packages file: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing package ID in packages file.");
            e.printStackTrace();
        }
    }



    private static void loadVehicles(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 2) { // Beklenen format "ID Şehir"
                    System.out.println("Warning: Incorrect format in vehicles file line: " + line);
                    continue;
                }

                int vehicleId = Integer.parseInt(parts[0].substring(1)); // "V1" -> 1
                String cityName = parts[1].trim();
                String type = "Default Type"; // Tür için varsayılan değer

                Vehicle vehicle = new Vehicle(vehicleId, type);
                DistributionCenter center = centers.get(cityName);
                if (center != null) {
                    center.addVehicle(vehicle);
                    System.out.println("Loaded vehicle: " + vehicle + " for city: " + cityName);
                } else {
                    System.out.println("Warning: City " + cityName + " not found for vehicle " + vehicleId);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading vehicles file: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing vehicle ID in vehicles file.");
            e.printStackTrace();
        }
    }


    private static List<Mission> loadMissions(String fileName) {
        List<Mission> missions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                DistributionCenter source = centers.get(parts[0].trim());
                DistributionCenter middle = centers.get(parts[1].trim());
                DistributionCenter destination = centers.get(parts[2].trim());
                int sourcePackagesCount = Integer.parseInt(parts[3].trim());
                int middlePackagesCount = Integer.parseInt(parts[4].trim());
                int[] dropOffIndices = Arrays.stream(parts[5].split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (source != null && middle != null && destination != null) {
                    Mission mission = new Mission(source, middle, destination, sourcePackagesCount, middlePackagesCount, dropOffIndices);
                    missions.add(mission);
                    System.out.println("Loaded mission: " + mission);
                } else {
                    System.out.println("Warning: Invalid mission entry with missing cities.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading missions file: " + e.getMessage());
            e.printStackTrace();
        }
        return missions;
    }

    private static void writeResult(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (DistributionCenter center : centers.values()) {
                bw.write(center.toString());
                bw.newLine();
            }
            System.out.println("Result written to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing result file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
