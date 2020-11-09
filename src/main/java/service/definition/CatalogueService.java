package service.definition;

import exception.InvalidVehicleTypeException;
import record.CarType;

import java.util.Scanner;

public interface CatalogueService {
    void showCatalogue();
    void sortByCarType();
    void sortByBrand();
    void addNewCarFromSTDIN(Scanner scanner, String carType) throws InvalidVehicleTypeException;
}
