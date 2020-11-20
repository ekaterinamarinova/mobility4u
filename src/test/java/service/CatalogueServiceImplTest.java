package service;

import exception.InvalidVehicleTypeException;
import model.CarType;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.Constants.PATH_TO_TEST_CATALOG;
import static util.Constants.PATH_TO_TEST_SIMULATED_INPUT_FILE;

@RunWith(JUnit4.class) public class CatalogueServiceImplTest {
    private List<Vehicle> sharedList;
    private CatalogueServiceImpl catalogueService;
    private Scanner scanner;

    @Before public void setUp() throws IOException {
        scanner = new Scanner(Path.of(PATH_TO_TEST_SIMULATED_INPUT_FILE));
        sharedList = new ArrayList<>();
        catalogueService = new CatalogueServiceImpl(sharedList, new MappingServiceImpl(sharedList), new FileOpsServiceImpl());
    }

    @Test public void testAddNewCarFromSTDIN() throws InvalidVehicleTypeException, IOException {
        //arrange
        int initialVehicleSize = sharedList.size();
        catalogueService.setPathToUserCatalog(Path.of(PATH_TO_TEST_CATALOG));
        //act
        catalogueService.addNewCarFromSTDIN(scanner, CarType.ELECTRIC);
        //assert
        Assert.assertNotEquals(initialVehicleSize, sharedList.size());
    }
}
