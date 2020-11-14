package service;

import exception.InvalidPathException;
import service.definition.FileOpsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileOpsServiceImpl implements FileOpsService {
    /**
     * {@inheritDoc}
     * @param pathToFile - the absolute path to the file
     * @return
     * @throws IOException
     */
    @Override public List<String> readFile(Path pathToFile) throws IOException {
        if (Files.notExists(pathToFile)) throw new InvalidPathException("The path <" + pathToFile + "> is invalid.");
        return Files.readAllLines(pathToFile);
    }

    /**
     * {@inheritDoc}
     * @param pathToFile
     * @param content
     * @throws IOException
     */
    @Override public void writeToFile(Path pathToFile, List<String> content) throws IOException {
        if (Files.notExists(pathToFile)) throw new InvalidPathException("The path <" + pathToFile + "> is invalid.");
        Files.write(pathToFile, content);
    }
}
