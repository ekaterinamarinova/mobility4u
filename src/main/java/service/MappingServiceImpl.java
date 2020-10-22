package service;

import record.Car;
import record.CarType;
import service.definition.MappingService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MappingServiceImpl implements MappingService{

    private static final String COMMA = ",";
    private static final String WHITE_SPACE = " ";
    private static final String EMPTY_STR = "";
    private static final String POSTFIX = "_car";

    public List<? super Serializable> mapToObject(List<String> lines) {
        var objList = new ArrayList<>();
        var fields = new String[]{};

        for (String s:lines) {
            var type = s.toLowerCase().substring(0, s.toLowerCase().indexOf(WHITE_SPACE));

            fields = s.toLowerCase()
                    .replaceAll("\s++", EMPTY_STR)
                    .substring(
                            s.toLowerCase()
                                .lastIndexOf(
                                        POSTFIX
                                ) + POSTFIX.length()
                    ).split(COMMA);

            mapToSpecificObject(type, objList, fields);
        }

        return objList;
    }

    private void mapToSpecificObject(String type,
                                     List<? super Serializable> objList,
                                     String[] fields) {
        switch (type) {
            case CarType.GAS: objList.add(new Car(
                    type,
                    fields[1],
                    fields[2],
                    fields[4],
                    fields[3]
            )); break;
            case CarType.ELECTRIC: objList.add(new Car(type, fields[1],
                    fields[2],
                    fields[4],
                    null
            )); break;
            case CarType.HYBRID: break;
            default:break;
        }
    }

}
