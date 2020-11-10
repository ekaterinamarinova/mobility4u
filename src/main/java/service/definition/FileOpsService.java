package service.definition;

import model.Vehicle;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * A service responsible for the reading and writing to the catalog file.
 */
public interface FileOpsService {
    /**
     * Reads the file from the given path, if exists,
     * saving its contents in a list of strings,
     * each string representing the contents of a new line from the file,
     * which by protocol is supposed to be equal to one {@link model.Vehicle} object
     *
     * @param pathToFile - the absolute path to the file
     * @return - {@link List<String>} list containing the fields of the file
     * @throws IOException - if invalid path, etc.
     */
    List<String> readFile(Path pathToFile) throws IOException;

    /**
     * Writes to the catalog.
     *
     * @param pathToFile
     * @param content
     * @throws IOException
     */
    void writeToFile(Path pathToFile, List<String> content) throws IOException;
}
