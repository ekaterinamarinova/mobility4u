package service;

import record.Car;
import record.CarType;
import record.Vehicle;
import service.definition.MappingService;

import java.util.ArrayList;
import java.util.List;

public class MappingServiceImpl implements MappingService {

    private static final String COMMA = ",";
    private static final String WHITE_SPACE = " ";
    private static final String EMPTY_STR = "";
    private static final String POSTFIX = "_car";

    private List<Vehicle> vehicles;

    public MappingServiceImpl(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> mapToObject(List<String> lines) {
        var fields = new String[]{};

        for (String s : lines) {
            var type = s.toLowerCase().substring(0, s.toLowerCase().indexOf(WHITE_SPACE));

            fields = s.toLowerCase()
                    .replaceAll("\s++", EMPTY_STR)
                    .substring(
                            s.toLowerCase()
                                    .lastIndexOf(
                                            POSTFIX
                                    ) + POSTFIX.length()
                    ).split(COMMA);

            mapToSpecificObject(type, fields);
        }

        return vehicles;
    }

    private void mapToSpecificObject(String type,
                                     String[] fields) {
        switch (type) {
            case CarType.GAS:
                vehicles.add(
                        new Car(
                                type,
                                fields[0], //brand
                                fields[1], //model
                                fields[3], //power
                                fields[4], //price
                                fields[2]  //otherProps
                        )
                );
                break;
            case CarType.ELECTRIC:
                vehicles.add(
                        new Car(
                                type,
                                fields[1],
                                fields[2],
                                fields[4],
                                null
                        )
                );
                break;
            case CarType.HYBRID:
                vehicles.add(
                        new Car(
                                type,
                                fields[1],
                                fields[2],
                                fields[3],
                                fields[4]
                        )
                );
                break;
            default:
                break;
        }
    }

}
