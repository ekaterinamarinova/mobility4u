package service;

import exception.InvalidVehicleTypeException;
import model.Car;
import model.CarType;
import model.Vehicle;
import service.definition.MappingService;

import java.util.Arrays;
import java.util.List;

import static util.Constants.*;

public class MappingServiceImpl implements MappingService {

    private static final String POSTFIX = "_car";
    private final List<Vehicle> vehicles;

    public MappingServiceImpl(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * {@inheritDoc}
     * @param lines
     * @return
     * @throws InvalidVehicleTypeException
     */
    @Override public List<Vehicle> mapObjects(List<String> lines) throws InvalidVehicleTypeException {
        var fields = new String[]{};

        for (String s : lines) {
            var type = s.toLowerCase().substring(0, s.toLowerCase().indexOf(WHITE_SPACE));

            fields = s.toLowerCase()
                    .replaceAll("\s++", EMPTY_SPACE)
                    .substring(
                            s.toLowerCase()
                                    .lastIndexOf(POSTFIX) + POSTFIX.length()
                    ).split(COMMA);

            mapObject(type, fields, vehicles);
        }

        return vehicles;
    }

    /**
     * {@inheritDoc}
     * @param type - {@code CarType}
     * @param fields - fields read from catalog file
     * @param vehicles - list with mapped vehicle objects
     * @return the created car object
     * @throws InvalidVehicleTypeException
     */
    @Override public Car mapObject(String type,
                         String[] fields,
                         List<Vehicle> vehicles) throws InvalidVehicleTypeException {
        Car car = switch (type) {
                    case CarType.GAS -> new Car(type,
                            fields[BRAND],
                            fields[MODEL],
                            fields[GAS_POWER],
                            fields[GAS_PRICE],
                            fields[ENGINE_DISPLACEMENT]
                    );
                    case CarType.ELECTRIC -> new Car(type,
                            fields[BRAND],
                            fields[MODEL],
                            fields[ELECTRIC_POWER],
                            fields[ELECTRIC_PRICE],
                            fields[ELECTRIC_BATTERY_POWER]
                    );
                    case CarType.HYBRID -> new Car(type,
                            fields[BRAND],
                            fields[MODEL],
                            fields[HYBRID_POWER],
                            fields[HYBRID_PRICE],
                            fields[ENGINE_DISPLACEMENT],
                            fields[HYBRID_BATTERY_POWER]
                    );
                    default -> throw new InvalidVehicleTypeException(
                            "The type received is invalid. Currently supported types are: "
                                    + Arrays.toString(CarType.values()) + " case insensitive."
                    );
                };

        vehicles.add(car);
        return car;
    }

}
