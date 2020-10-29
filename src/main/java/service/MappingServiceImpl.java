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

    public void mapObject(String type,
                          String[] fields,
                          List<Vehicle> vehicles) throws InvalidVehicleTypeException {
        switch (type) {
            /*
            I would like to point out that I am initially against storing Integer values
            in the Car record simply because of the over complication of calling replace
            and valueOf functions, and when stored those values are later displayed as strings anyway.
            But assuming we give up processor power over memory, this micro optimization would be valid.
             */

            case CarType.GAS:
                vehicles.add(
                        new Car(
                                type,
                                fields[0], //brand
                                fields[1], //model
                                Integer.valueOf(fields[3].replaceAll("[a-z]", "")), //power
                                Integer.valueOf(fields[4].replaceAll("[a-z]", "")), //price
                                "engineDisplacement: " + fields[2]  //otherProps
                        )
                );
                break;
            case CarType.ELECTRIC:
                vehicles.add(
                        new Car(
                                type,
                                fields[0],
                                fields[1],
                                Integer.valueOf(fields[2].replaceAll("[a-z]", "")), //power
                                Integer.valueOf(fields[4].replaceAll("[a-z]", "")),
                                "batteryPower: " + fields[3]
                        )
                );
                break;
            case CarType.HYBRID:
                vehicles.add(
                        new Car(
                                type,
                                fields[0],
                                fields[1],
                                Integer.valueOf(fields[3].replaceAll("[a-z]", "")), //power
                                Integer.valueOf(fields[5].replaceAll("[a-z]", "")),
                                "engineDisplacement: " + fields[2],
                                "batteryPower: " + fields[4]
                        )
                );
                break;
            default:
                throw new InvalidVehicleTypeException("The type received is invalid. Currently supported types are: " +
                        Arrays.toString(CarType.values()) +
                        " case insensitive.");

        }
    }

}
