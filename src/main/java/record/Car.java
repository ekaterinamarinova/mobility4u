package record;

import java.io.Serializable;

public record Car(String type, String brand,
                  String model, String power,
                  String price, String... otherProperties) implements Serializable {

}
