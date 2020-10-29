package util;

import java.nio.file.Path;

public final class Constants {
    public static final String ABSOLUTE_PATH = Path.of("").toAbsolutePath().toString();
    public static final String PROPERTIES_PATH = ABSOLUTE_PATH + "/src/main/resources/application.properties";
    public static final String FILE_PATH_PROPERTY = "file_path";
    public static final String SHARED_LIST_NAME = "sharedVehicleList";
    public static final String WHITE_SPACE = " ";
    public static final String EMPTY_SPACE = "";
    public static final String COMMA = ",";
}
