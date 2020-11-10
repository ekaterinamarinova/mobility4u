package service.definition;

import exception.InvalidVehicleTypeException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Service, responsible for handling the user requests
 * to be performed on the catalog.
 */
public interface CatalogueService {
    /**
     * Shows the current catalog and its contents.
     */
    void showCatalogue();

    /**
     * Sorts the contents based on the car type - alphabetically.
     */
    void sortByCarType() throws IOException;

    /**
     * Sorts by brand - alphabetically.
     */
    void sortByBrand() throws IOException;

    /**
     * Allows the user to add a new car to the catalog.
     * @param scanner - to handle user input real time;
     * @param carType - the car type;
     * @throws InvalidVehicleTypeException - self explanatory
     */
    void addNewCarFromSTDIN(Scanner scanner, String carType) throws InvalidVehicleTypeException, IOException;

    /**
     * Allows the user the ability to write contents to a newly created file.
     * @param scanner - to handle user input real time;
     * @throws IOException - if writing go wrong - no permission, etc.
     */
    void writeToFileFromSTDIN(Scanner scanner) throws IOException;

    void setPathToUserCatalog(Path pathToUserCatalog);
}
