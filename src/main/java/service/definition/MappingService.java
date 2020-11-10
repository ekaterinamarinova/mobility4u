package service.definition;

import exception.InvalidVehicleTypeException;
import record.Car;
import record.Vehicle;

import java.util.List;

/**
 * This service is responsible for
 * mapping the read lines from the catalog file
 * into {@code Vehicle} objects.
 */
public interface MappingService {
    /**
     * For each {@code String} line read from the catalog,
     * the mapObject method is called and a {@code Car} object is created
     * and added tto the shared vehicle list.
     * @param lines - all lines, read from the catalog, each representing a single object
     * @return - a list of mapped vehicle objects; currently supporting only {@code Car} ones.
     * @throws InvalidVehicleTypeException - self explanatory
     */
    List<Vehicle> mapObjects(List<String> lines) throws InvalidVehicleTypeException;

    /**
     * Maps all the fields to match a {@code Car} object.
     * @param type - the car type
     * @param fields - contains all fields that a Car object is supposed to have
     * @param vehicles - the shared vehicle list
     * @return - the {@code Car} obj created
     * @throws InvalidVehicleTypeException - self explanatory
     */
    Car mapObject(String type, String[] fields, List<Vehicle> vehicles) throws InvalidVehicleTypeException;
}
