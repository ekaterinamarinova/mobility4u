import console.ConsolePrinter;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import static util.Constants.*;

public class Start  {
    private static final InputStream CONSOLE_IN = System.in;
    private static final Scanner SCANNER = new Scanner(CONSOLE_IN);
    private static final Properties PROPERTIES = new Properties();

    private static final String NEGATIVE_ANSWER = "n";
    private static final String POSITIVE_ANSWER = "y";

    public static void main(String[] args) throws Exception {
        ConsolePrinter.printLoadFileOptions();
        loadCatalogBasedOnUserInput(SCANNER.nextInt());

        while (true) {
            ConsolePrinter.printOptions();
            callFunctionBasedOnChoice(SCANNER.nextInt());
            System.out.println("Would you like to make another choice? Y/N");
            if (NEGATIVE_ANSWER.equals(SCANNER.next().toLowerCase())) break;
            if (POSITIVE_ANSWER.equals(SCANNER.next().toLowerCase())) continue;
            else throw new IllegalArgumentException("Invalid choice.");
        }
    }

    private static void loadCatalogBasedOnUserInput(int choice) throws IOException, InvalidVehicleTypeException {
        if (choice == 1) {
            //load properties
            PROPERTIES.load(new FileInputStream(PROPERTIES_PATH));
            //load catalogue from properties path
            loadCatalogue(ABSOLUTE_PATH + PROPERTIES.getProperty(FILE_PATH_PROPERTY));
        }

        if (choice == 2) {
            System.out.println("Enter the full file path on the next line, example C:/User/Desktop/mobility.txt: ");
            var pathToFile = Paths.get(SCANNER.next());
            if (Files.notExists(pathToFile))
                throw new FileNotFoundException("File in path <" + pathToFile + "> was not found.");
            loadCatalogue(pathToFile.toString());
        }
    }

    private static void loadCatalogue(String property) throws IOException, InvalidVehicleTypeException {
        //instantiate the components and their dependencies
        Container.init();
        //get the loadingService instance from the component map
        var mappingService = (MappingService) Container.getContainer().get(MappingServiceImpl.class.getName());
        var readerService = (ReaderService) Container.getContainer().get(ReaderServiceImpl.class.getName());

        //map the read file to application-specific objects - Vehicles
        mappingService.mapObjects(
                //read the given file
                readerService.readFile(
                        Path.of(property)
                )
        );
    }

    private static void callFunctionBasedOnChoice(int choice) throws InvalidVehicleTypeException, IOException {
        var catalogueService =
                (CatalogueService) Container.getContainer().get(CatalogueServiceImpl.class.getName());
        switch (choice) {
            case 1 -> catalogueService.showCatalogue();
            case 2 -> catalogueService.addNewCarFromSTDIN(SCANNER, CarType.ELECTRIC);
            case 3 -> catalogueService.addNewCarFromSTDIN(SCANNER, CarType.GAS);
            case 4 -> catalogueService.addNewCarFromSTDIN(SCANNER, CarType.HYBRID);
            case 5 -> {
                catalogueService.sortByCarType();
                catalogueService.showCatalogue();
            }
            case 6 -> {
                catalogueService.sortByBrand();
                catalogueService.showCatalogue();
            }
            case 7 -> catalogueService.writeToFileFromSTDIN(SCANNER);
            case 8 -> System.exit(1);
            default -> System.out.println("Invalid choice, please try again.");
        }
    }

}
