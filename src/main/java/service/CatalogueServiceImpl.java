package service;

import record.Car;
import record.Vehicle;
import service.definition.CatalogueService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogueServiceImpl implements CatalogueService {

    private final List<Vehicle> vehicles;

    public CatalogueServiceImpl(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void showCatalogue() {
        for (Vehicle v:vehicles) {
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

    private List<Car> mapToCarList() {
        return vehicles.stream()
                .map(v -> (Car) v)
                .collect(Collectors.toList());
    }
}
