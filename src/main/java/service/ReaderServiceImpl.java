package service;

import service.definition.ReaderService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    @Override
    public List<String> readFile(Path pathToFile) throws IOException {
        if (Files.notExists(pathToFile)) throw new FileNotFoundException("The path <" + pathToFile + "> is invalid.");
        return Files.readAllLines(pathToFile);
    }
}
