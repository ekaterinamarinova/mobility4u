package record;

import java.util.Arrays;

public final class CarType {
    public static final String HYBRID = "hybrid_car";
    public static final String GAS = "gas_car";
    public static final String ELECTRIC = "electric_car";

    /**
     * Using Reflection, obtain currently declared fields' values.
     * @return - an object array with the declared values
     */
    public static Object[] values() {
        return Arrays.stream(CarType.class.getFields())
                .map(field -> {
                    try {
                        return field.get(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return field.toString();
                }
        ).toArray();
    }
}
