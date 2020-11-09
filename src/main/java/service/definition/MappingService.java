package service.definition;

import exception.InvalidVehicleTypeException;
import record.Car;
import record.Vehicle;

import java.util.List;

public interface MappingService {
    List<Vehicle> mapObjects(List<String> lines) throws InvalidVehicleTypeException;
    Car mapObject(String type, String[] fields, List<Vehicle> vehicles) throws InvalidVehicleTypeException;
}
