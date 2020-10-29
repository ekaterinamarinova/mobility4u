package service.definition;

import exception.InvalidVehicleTypeException;
import record.Vehicle;

import java.util.List;

public interface MappingService {
    List<Vehicle> mapObjects(List<String> lines) throws InvalidVehicleTypeException;
    void mapObject(String type, String[] fields, List<Vehicle> vehicles) throws InvalidVehicleTypeException;
}
