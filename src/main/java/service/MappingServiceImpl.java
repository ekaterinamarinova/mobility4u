package service;

import record.Car;
import record.CarType;

import java.util.ArrayList;
import java.util.List;

public class MappingServiceImpl {

    private static final String WHITE_SPACE = " ";

    public List<Object> mapToObject(List<String> lines) {
        List<Object> objList = new ArrayList<>();
        var fields = new String[]{};

        for (String s:lines) {
            var type = s.toLowerCase().substring(0, s.indexOf("_car"));
            fields = s.toLowerCase()
                    .substring(s.indexOf("_car"))
                    .split(",");


            switch (type) {
                case CarType.GAS: objList.add(new Car(type, fields[1],
                        fields[2],
                        fields[4],
                        Double.valueOf(fields[5]),
                        fields[3]
                )); break;
                case CarType.ELECTRIC: ; break;
                case CarType.HYBRID: break;
                default:break;
            }

        }

        return objList;
    }

    private Car mapToGasCar(String[] f, String type) {
        return new Car(type, f[1], f[2], f[4], Double.valueOf(f[5]), f[3]);
    }


//    @Override
//    public <T extends Car> List<T> mapToObject(List<String> lines) {
//        return null;
//    }
}
