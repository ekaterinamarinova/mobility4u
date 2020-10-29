package service;

import exception.InvalidVehicleTypeException;
import record.Car;
import record.CarType;
import record.Vehicle;
import service.definition.CatalogueService;
import service.definition.MappingService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static util.Constants.*;

public class CatalogueServiceImpl implements CatalogueService {

    private final List<Vehicle> vehicles;
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
        mapToCarList().sort(
                Comparator.comparing(
                        Car::type
                )
        );
    }

    @Override
    public void sortByBrand() {
        mapToCarList().sort(
                Comparator.comparing(
                        Car::brand
                )
        );
    }

    @Override
    public void addNewCar(Scanner scanner) throws InvalidVehicleTypeException {
        System.out.println("Please input a car type - gas, electric or hybrid:");
        var type = scanner.nextLine();

        printMessageBasedOnType(type);

        var properties = scanner.nextLine()
                .toLowerCase()
                .replace(WHITE_SPACE, EMPTY_SPACE)
                .split(COMMA);
        mappingService.mapObject(type, properties, vehicles);
    }

    private List<Car> mapToCarList() {
        return vehicles.stream()
                .map(v -> (Car) v)
                .collect(Collectors.toList());
    }

    private void printMessageBasedOnType(String type) {
        switch (type) {
            case CarType.GAS:
                System.out.println("""
                        For gas car, enter the following properties on the next line:
                        brand, model, power(KW), engine displacement(liters), price 
                        """);
                break;
            case CarType.ELECTRIC:
                System.out.println("""
                        For electric car, enter the following properties on the next line:
                        brand, model, power(KW), battery power(Ah), price
                        """);
                break;
            case CarType.HYBRID:
                System.out.println("""
                        For hybrid car, enter the following properties on the next line:
                        brand, model, displacement(l), power(KW), battery power(Ah), price
                        """);
        }
    }
}
