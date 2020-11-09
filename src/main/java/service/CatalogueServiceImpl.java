package service;

import exception.InvalidVehicleTypeException;
import record.Car;
import record.CarType;
import record.Vehicle;
import service.definition.CatalogueService;
import service.definition.MappingService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
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
    public void addNewCarFromSTDIN(Scanner scanner, String carType) throws InvalidVehicleTypeException {
        System.out.println("Please input a car type - gas_car, electric_car or hybrid_car:");

        printMessageBasedOnType(carType);

        String[] properties = new String[5];

        var car = mappingService.mapObject(
                carType,
                properties.toString().toLowerCase()
                        .replace(WHITE_SPACE, EMPTY_SPACE)
                        .split(COMMA),
                vehicles
        );

        System.out.println("Car object successfully created: " + car.toString());
    }

    private List<Car> mapToCarList() {
        return vehicles.stream()
                .map(v -> (Car) v)
                .collect(Collectors.toList());
    }

    private void printMessageBasedOnType(String type) {
        switch (type) {
            case CarType.GAS -> System.out.print("""
                    For gas car, enter the following properties on the next line:
                    brand, model, power(KW), engine displacement(liters), price 
                    """);
            case CarType.ELECTRIC -> System.out.print("""
                    For electric car, enter the following properties on the next line:
                    brand, model, power(KW), battery power(Ah), price
                    """);
            case CarType.HYBRID -> System.out.print("""
                    For hybrid car, enter the following properties on the next line:
                    brand, model, displacement(l), power(KW), battery power(Ah), price
                    """);
            default -> System.out.println("Invalid vehicle type!");

        }
    }

}
