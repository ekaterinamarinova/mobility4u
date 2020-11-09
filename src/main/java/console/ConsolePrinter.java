package console;

import record.CarType;

public final class ConsolePrinter {

    public static void printMessageBasedOnType(String type) {
        switch (type) {
            case CarType.GAS -> System.out.print("""
                    For gas car, enter the following properties on the next line:
                    brand, model, engine displacement(liters), power(KW),  price 
                    """);
            case CarType.ELECTRIC -> System.out.print("""
                    For electric car, enter the following properties on the next line:
                    brand, model, power(KW), battery power(Ah), price
                    """);
            case CarType.HYBRID -> System.out.print("""
                    For hybrid car, enter the following properties on the next line:
                    brand, model, displacement(l), power(KW), battery power(Ah), price
                    """);
            default -> System.out.println("Invalid vehicle type!");

        }
    }

    public static void printOptions() {
        System.out.println("""
                Please make your choice:
                1 - Show the entire Mobility4U catalogue;
                2 - Add a new electric car;
                3 - Add a new gas car;
                4 - Add a new hybrid car;
                5 - Print the catalogue sorted by car-type;
                6 - Print the catalogue sorted by brand (alphabetically)
                7 - Write to file;
                8 - Stop the program.
                """);
    }
}
