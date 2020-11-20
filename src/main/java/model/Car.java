package model;

import java.util.Arrays;
import java.util.Objects;

import static util.Constants.*;

public record Car(String type,
                  String brand,
                  String model,
                  String power,
                  String price,
                  String... otherProperties) implements Vehicle {

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(type, car.type) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(power, car.power) &&
                Objects.equals(price, car.price) &&
                Arrays.equals(otherProperties, car.otherProperties);
    }

    @Override public int hashCode() {
        int result = Objects.hash(type, brand, model, power, price);
        result = 31 * result + Arrays.hashCode(otherProperties);
        return result;
    }

    @Override public String toString() {
        return type.toUpperCase() +
                " " + brand +
                ", " + model +
                ", " + power +
                ", " + price +
                ", " + otherPropsToString();
    }

    private String otherPropsToString() {
        StringBuilder props = new StringBuilder();
        for (String p: otherProperties) {
            if (p.contains(ENGINE_DISPLACEMENT))
                props.append(p.replaceAll(ENGINE_DISPLACEMENT, EMPTY_SPACE));
            if (p.contains(BATTERY_POWER))
                props.append(p.replaceAll(BATTERY_POWER, EMPTY_SPACE));
        }
        return props.toString();
    }
}
