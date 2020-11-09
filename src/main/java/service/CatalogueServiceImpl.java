package service;

import console.ConsolePrinter;
import exception.InvalidVehicleTypeException;
import record.Car;
import record.Vehicle;
import service.definition.CatalogueService;
import service.definition.MappingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static util.Constants.*;

public class CatalogueServiceImpl implements CatalogueService {

    private List<Vehicle> vehicles;
    private final MappingService mappingService;

    public CatalogueServiceImpl(List<Vehicle> vehicles, MappingService mappingService) {
        this.vehicles = vehicles;
        this.mappingService = mappingService;
    }

    @Override
    public void showCatalogue() {
        for (Vehicle v : vehicles) {
            System.out.println(v.toString());
        }
    }

    @Override
    public void sortByCarType() {
        vehicles = mapToCarList().stream()
                .sorted(
                        Comparator.comparing(
                                Car::type
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public void sortByBrand() {
        vehicles = mapToCarList().stream()
                .sorted(
                        Comparator.comparing(
                                Car::brand
                        )
                )
                .collect(Collectors.toList());
    }

//    public <T extends > void sortBy(T param) {
//        vehicles = mapToCarList().stream()
//                .sorted(
//                        Comparator.comparing(
//                                param
//                        )
//                )
//                .collect(Collectors.toList());
//    }

    @Override
    public void addNewCarFromSTDIN(Scanner scanner, String carType) throws InvalidVehicleTypeException {
        ConsolePrinter.printMessageBasedOnType(carType);

        List<String> properties = new ArrayList<>();

        while (true) {
            String current = scanner.next();
            if (STOP_WRITING_TO_FILE_WORD.equals(current)) break;
            properties.add(current);
        }

        properties.forEach(
                word -> word = word.toLowerCase()
                        .replaceAll(WHITE_SPACE, EMPTY_SPACE)
        );

        var car = mappingService.mapObject(
                carType,
                properties.toArray(String[]::new),
                vehicles
        );

        System.out.println("Car object successfully created: " + car.toString());
    }

    @Override
    public void writeToFileFromSTDIN(Scanner scanner) throws IOException {
        System.out.println("Please enter file name and extension (example test.txt). " +
                "To signal that you want to stop writing to the current file use !close! as a stop word.");
        var fileName = scanner.next();
        if (STOP_WRITING_TO_FILE_WORD.equals(fileName)) return;

        var fullPath = Path.of(PATH_TO_NEW_FILE + fileName);
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

    private List<Car> mapToCarList() {
        return vehicles.stream()
                .map(v -> (Car) v)
                .collect(Collectors.toList());
    }

}
