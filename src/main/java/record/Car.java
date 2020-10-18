package record;

import annotation.TransportationDevice;

import java.lang.annotation.Annotation;
import java.util.Map;


public record Car(String type, String brand,
                  String model, String power,
                  Double price, String... otherProperties) implements TransportationDevice{

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
