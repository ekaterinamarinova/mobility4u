package service;

import exception.InvalidVehicleTypeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import model.Car;
import model.CarType;
import service.definition.MappingService;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class) public class MappingServiceImplTest {
    private MappingService mappingService;
    private List<String> lines;

    @Before public void setUp() {
        lines = new ArrayList<>();
        mappingService = new MappingServiceImpl(new ArrayList<>());
    }

    @Test public void testMapGasCarToObject() throws InvalidVehicleTypeException {
        //arrange
        lines.add("GAS_CAR Honda, Civic, 1.5L, 80KW, 18000 euro");
        var cars = mappingService.mapObjects(lines);
        //act
        Car car = new Car(CarType.GAS, "honda", "civic", "80kw", "18000euro", "1.5l");
        //assert
        Assert.assertEquals(car, cars.get(0));
    }
}
