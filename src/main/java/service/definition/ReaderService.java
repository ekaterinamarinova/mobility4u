package service.definition;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * A service responsible for the reading of the catalog file.
 */
public interface ReaderService {
    /**
     * Reads the file from the given path, if exists,
     * saving its contents in a list of strings,
     * each string representing the contents of a new line from the file,
     * which by protocol is supposed to be equal to one {@link record.Vehicle} object
     *
     * @param pathToFile - the absolute path to the file
     * @return - {@link List<String>} list containing the fields of the file
     * @throws IOException - if invalid path, etc.
     */
    List<String> readFile(Path pathToFile) throws IOException;
}
