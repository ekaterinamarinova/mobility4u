package console;

import exception.InvalidVehicleTypeException;
import record.CarType;
import service.CatalogueServiceImpl;
import service.MappingServiceImpl;
import service.ReaderServiceImpl;
import service.definition.CatalogueService;
import service.definition.MappingService;
import service.definition.ReaderService;
import util.Container;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

import static util.Constants.*;

public class Start  {
    private static final PrintStream CONSOLE_OUT = System.out;
    private static final InputStream CONSOLE_IN = System.in;
    private static final Scanner SCANNER = new Scanner(CONSOLE_IN);
    private static final Properties PROPERTIES = new Properties();

    public static void main(String[] args) throws IOException, InvalidVehicleTypeException {
        //load properties
        PROPERTIES.load(new FileInputStream(PROPERTIES_PATH));
        //load catalogue from properties path
        loadCatalogue(PROPERTIES.getProperty(FILE_PATH_PROPERTY));

        while (true) {
            printOptions();
            callFunctionBasedOnChoice(SCANNER.nextInt());
        }

    }

    public static void loadCatalogue(String property) throws IOException, InvalidVehicleTypeException {
        //instantiate the components and their dependencies
        Container.init();
        //get the loadingService instance from the component map
        var mappingService = (MappingService) Container.getContainer().get(MappingServiceImpl.class.getName());
        var readerService = (ReaderService) Container.getContainer().get(ReaderServiceImpl.class.getName());

        //map the read file to application-specific objects - Vehicles
        mappingService.mapObjects(
                //read the given file
                readerService.readFile(
                        Path.of(ABSOLUTE_PATH + property)
                )
        );
    }

    public static void printOptions() {
        CONSOLE_OUT.println("""
                Please make your choice:
                1 - Show the entire Mobility4U catalogue;
                2 - Add a new electric car;
                3 - Add a new gas car;
                4 - Add a new hybrid car;
                5 - Print the catalogue sorted by car-type;
                6 - Print the catalogue sorted by brand (alphabetically)
                7 - Write to file;
                8 - Stop the program.
                """);
    }

    public static void callFunctionBasedOnChoice(int choice) throws InvalidVehicleTypeException {
        var catalogueService = (CatalogueService) Container.getContainer().get(CatalogueServiceImpl.class.getName());
        switch (choice) {
            case 1:
                catalogueService.showCatalogue();
                break;
            case 2:
                catalogueService.addNewCarFromSTDIN(SCANNER, CarType.ELECTRIC);break;
            case 3:
                catalogueService.addNewCarFromSTDIN(SCANNER, CarType.GAS);break;
            case 4:
                catalogueService.addNewCarFromSTDIN(SCANNER, CarType.HYBRID);break;
            case 5:
                catalogueService.sortByCarType();
                catalogueService.showCatalogue();
                break;
            case 6:
                catalogueService.sortByBrand();
                catalogueService.showCatalogue();
                break;
            case 7: break;
            case 8: System.exit(1);break;

            default:
                System.out.println("Invalid choice, please try again.");break;
        }
    }

}
