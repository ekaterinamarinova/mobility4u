package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.definition.FileOpsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static util.Constants.PATH_TO_TEST_CATALOG;

@RunWith(JUnit4.class) public class FileOpsServiceImplTest {
    private FileOpsService fileOpsService;
    private Path pathToFile;

    @Before public void setUp() {
        fileOpsService = new FileOpsServiceImpl();
        pathToFile = Path.of(PATH_TO_TEST_CATALOG);
    }

    @Test public void testReadFile() throws IOException {
        //arrange
        var lineCount = Files.readAllLines(pathToFile).stream().count();
        //act
        var result = fileOpsService.readFile(pathToFile);
        //assert
        Assert.assertNotNull(result);
        Assert.assertEquals(lineCount, result.size());
    }

    @Test(expected = IOException.class)
    public void testReadFile_withInvalidPath() throws IOException {
        fileOpsService.readFile(Path.of(""));
    }
}
