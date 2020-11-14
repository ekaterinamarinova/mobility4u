package service;

import console.ConsolePrinter;
import exception.InvalidVehicleTypeException;
import model.Car;
import model.Vehicle;
import service.definition.CatalogueService;
import service.definition.FileOpsService;
import service.definition.MappingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static util.Constants.*;

public class CatalogueServiceImpl implements CatalogueService {

    private List<Vehicle> vehicles;
    private final MappingService mappingService;
    private final FileOpsService fileOpsService;
    private Path pathToUserCatalog;

    public CatalogueServiceImpl(List<Vehicle> vehicles,
            MappingService mappingService,
            FileOpsService fileOpsService) {
        this.vehicles = vehicles;
        this.mappingService = mappingService;
        this.fileOpsService = fileOpsService;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void showCatalogue() {
        for (Vehicle v : vehicles) {
            System.out.println(v.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public void sortByCarType() throws IOException {
        vehicles = mapToCarList().stream()
                .sorted(
                        Comparator.comparing(
                                Car::type
                        )
                )
                .collect(Collectors.toList());

        fileOpsService.writeToFile(pathToUserCatalog,
                mapToCarList().stream()
                        .map(Car::toString)
                        .collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override public void sortByBrand() throws IOException {
        vehicles = mapToCarList().stream()
                .sorted(
                        Comparator.comparing(
                                Car::brand
                        )
                )
                .collect(Collectors.toList());

        fileOpsService.writeToFile(pathToUserCatalog,
                mapToCarList().stream()
                        .map(Car::toString)
                .collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override public void addNewCarFromSTDIN(Scanner scanner, String carType) throws InvalidVehicleTypeException, IOException {
        ConsolePrinter.printMessageBasedOnType(carType);
        StringBuilder carProps = new StringBuilder();

        while (true) {
            String current = scanner.next();
            if (STOP_WRITING_TO_FILE_WORD.equals(current)) break;
            carProps.append(current);
        }

        fileOpsService.writeToFile(pathToUserCatalog, Collections.singletonList(carProps.toString()));

        var car = mappingService.mapObject(
                carType,
                carProps.toString()
                        .replaceAll(WHITE_SPACE, EMPTY_SPACE)
                        .split(COMMA),
                vehicles
        );

        System.out.println("Car object successfully created: " + car.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override public void writeToFileFromSTDIN(Scanner scanner) throws IOException {
        System.out.println("Please enter file name and extension (example test.txt). " +
                "To signal that you want to stop writing to the current file use " + STOP_WRITING_TO_FILE_WORD + " as a stop word.");
        var fileName = scanner.next();
        if (STOP_WRITING_TO_FILE_WORD.equals(fileName)) return;

        var fullPath = Path.of(DEFAULT_PATH_TO_CATALOG + fileName);
        var words = new StringBuilder();

        if (Files.notExists(fullPath))
            Files.createFile(fullPath);

        while (true) {
            var current = scanner.next();
            if (STOP_WRITING_TO_FILE_WORD.equals(current)) break;
            words.append(current).append(WHITE_SPACE);
        }

        Files.writeString(fullPath, words);
        System.out.println("Contents of file " + fileName + " include: ");
        System.out.println(Files.readAllLines(fullPath));
    }

    @Override public void setPathToUserCatalog(Path pathToUserCatalog) {
        this.pathToUserCatalog = pathToUserCatalog;
    }

    private List<Car> mapToCarList() {
        return vehicles.stream()
                .map(v -> (Car) v)
                .collect(Collectors.toList());
    }
}
