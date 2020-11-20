package util;

import java.nio.file.Path;

public final class Constants {
    /**
     * paths
     */
    private static final String MAIN_RESOURCES = "/src/main/resources";
    private static final String TEST_RESOURCES = "/src/test/resources";

    public static final String ABSOLUTE_PATH = Path.of("").toAbsolutePath().toString();

    public static final String DEFAULT_PATH_TO_CATALOG = ABSOLUTE_PATH + MAIN_RESOURCES;
    public static final String PROPERTIES_PATH = DEFAULT_PATH_TO_CATALOG + "/application.properties";

    public static final String PATH_TO_TEST_SIMULATED_INPUT_FILE = ABSOLUTE_PATH + TEST_RESOURCES + "/example_user_input.txt";
    public static final String PATH_TO_TEST_CATALOG = ABSOLUTE_PATH + TEST_RESOURCES + "/mobility.txt";
    public static final String FILE_PATH_PROPERTY = "file_path";

    /**
     * model-related properties
     */
    public static final String SHARED_LIST_NAME = "sharedVehicleList";
    public static final String ENGINE_DISPLACEMENT = "engineDisplacement: ";
    public static final String BATTERY_POWER = "batteryPower: ";

    /**
     * symbols
     */
    public static final String WHITE_SPACE = " ";
    public static final String EMPTY_SPACE = "";
    public static final String COMMA = ",";

    /**
     * end of user stdin property
     */
    public static final String STOP_WRITING_TO_FILE_WORD = "!stop!";
}
