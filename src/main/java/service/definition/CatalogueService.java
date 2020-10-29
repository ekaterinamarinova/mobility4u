package service.definition;

import exception.InvalidVehicleTypeException;

import java.util.Scanner;

public interface CatalogueService {
    void showCatalogue();
    void sortByCarType();
    void sortByBrand();
    void addNewCar(Scanner scanner) throws InvalidVehicleTypeException;
}
