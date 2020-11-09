package util;

import java.nio.file.Path;

public final class Constants {
    /**
     * paths
     */
    public static final String ABSOLUTE_PATH = Path.of("").toAbsolutePath().toString();
    public static final String PROPERTIES_PATH = ABSOLUTE_PATH + "/src/main/resources/application.properties";
    public static final String PATH_TO_NEW_FILE = ABSOLUTE_PATH + "/src/main/resources/";
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
    public static final String REGEX = "[a-z,.;-]";

    /**
     * end of user stdin property
     */
    public static final String STOP_WRITING_TO_FILE_WORD = "!close!";
}
