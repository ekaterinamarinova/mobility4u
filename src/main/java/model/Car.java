package model;

import java.util.Arrays;
import java.util.Objects;

public record Car(String type,
                  String brand,
                  String model,
                  Integer power,
                  Integer price,
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
                ", " + Arrays.toString(otherProperties);
    }
}
