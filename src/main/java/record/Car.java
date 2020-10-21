package record;

import java.io.Serializable;

public record Car(String type, String brand,
                  String model, String power,
                  Double price, String... otherProperties) implements Serializable {

}
