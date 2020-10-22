package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import record.Car;
import record.CarType;
import service.definition.MappingService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class MappingServiceImplTest {

    private MappingService mappingService;
    private List<String> lines;

    @Before
    public void setUp() {
        mappingService = new MappingServiceImpl();
        lines = new ArrayList<>();
    }

    @Test
    public void testMapToObject() {
        lines.add("GAS_CAR Honda, Civic, 1.5L, 80KW, 18000 euro");

        var cars = mappingService.mapToObject(lines);

        Car car = new Car(CarType.GAS, "honda", "civic", "80kw",
                "18000 euro", "1.5l");

        Assert.assertEquals(car, cars.get(0));
    }

}