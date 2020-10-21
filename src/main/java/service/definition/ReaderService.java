package service.definition;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReaderService {
    List<String> readFile(Path pathToFile) throws IOException;
}
