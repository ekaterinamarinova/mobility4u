package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.definition.ReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class ReaderServiceImplTest {

    private static final String MOBILITY_TXT_PATH = "/src/test/resources/mobility.txt";
    private static final String EMPTY_STR = "";

    private ReaderService readerService;
    private Path pathToFile;

    @Before
    public void setUp() {
        readerService = new ReaderServiceImpl();
        var absoluteStringToFile = Paths.get(EMPTY_STR).toAbsolutePath().toString() + MOBILITY_TXT_PATH;
        pathToFile = Path.of(absoluteStringToFile);
    }

    @Test
    public void testReadFile() throws IOException {
        var lineCount = Files.readAllLines(pathToFile).stream().count();
        var result = readerService.readFile(pathToFile);

        Assert.assertNotNull(result);
        Assert.assertEquals(lineCount, result.size());
    }

    @Test(expected = IOException.class)
    public void testReadFile_withInvalidPath() throws IOException {
        readerService.readFile(Path.of(""));
    }

}
