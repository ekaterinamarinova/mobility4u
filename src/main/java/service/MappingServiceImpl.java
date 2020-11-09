package service;

import exception.InvalidVehicleTypeException;
import record.Car;
import record.CarType;
import record.Vehicle;
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

    public List<Vehicle> mapObjects(List<String> lines) throws InvalidVehicleTypeException {
        var fields = new String[]{};

        for (String s : lines) {
            var type = s.toLowerCase().substring(0, s.toLowerCase().indexOf(WHITE_SPACE));

            fields = s.toLowerCase()
                    .replaceAll("\s++", EMPTY_SPACE)
                    .substring(
                            s.toLowerCase()
                                    .lastIndexOf(
                                            POSTFIX
                                    ) + POSTFIX.length()
                    ).split(COMMA);

            mapObject(type, fields, vehicles);
        }

        return vehicles;
    }

    /**
     *
     * @param type
     * @param fields
     * @param vehicles
     * @return the created car object
     * @throws InvalidVehicleTypeException
     */
    public Car mapObject(String type,
                          String[] fields,
                          List<Vehicle> vehicles) throws InvalidVehicleTypeException {
        Car car;
        switch (type) {
            /*
            I would like to point out that I am initially against storing Integer values
            in the Car record simply because of the over complication of calling replace
            and valueOf functions, and when stored those values are later displayed as strings anyway.
            But assuming we give up processor power over memory, this micro optimization would be valid.
             */

            case CarType.GAS:
                car = new Car(
                        type,
                        fields[0], //brand
                        fields[1], //model
                        Integer.valueOf(fields[3].replaceAll(REGEX, EMPTY_SPACE)), //power
                        Integer.valueOf(fields[4].replaceAll(REGEX, EMPTY_SPACE)), //price
                        ENGINE_DISPLACEMENT + fields[2]  //otherProps
                );
                vehicles.add(car);break;
            case CarType.ELECTRIC:
                car = new Car(
                        type,
                        fields[0],
                        fields[1],
                        Integer.valueOf(fields[2].replaceAll(REGEX, EMPTY_SPACE)), //power
                        Integer.valueOf(fields[4].replaceAll(REGEX, EMPTY_SPACE)),
                        BATTERY_POWER + fields[3]
                );
                vehicles.add(car);break;
            case CarType.HYBRID:
                car = new Car(
                        type,
                        fields[0],
                        fields[1],
                        Integer.valueOf(fields[3].replaceAll(REGEX, EMPTY_SPACE)), //power
                        Integer.valueOf(fields[5].replaceAll(REGEX, EMPTY_SPACE)),
                        ENGINE_DISPLACEMENT + fields[2],
                        BATTERY_POWER + fields[4]
                );
                vehicles.add(car);break;
            default:
                throw new InvalidVehicleTypeException("The type received is invalid. Currently supported types are: " +
                        Arrays.toString(CarType.values()) +
                        " case insensitive.");

        }

        return car;
    }

}
