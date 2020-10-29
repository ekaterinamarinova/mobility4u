package util;

import record.Vehicle;
import service.CatalogueServiceImpl;
import service.MappingServiceImpl;
import service.ReaderServiceImpl;
import service.definition.CatalogueService;
import service.definition.MappingService;
import service.definition.ReaderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Constants.SHARED_LIST_NAME;

public final class Container {

    private static final Map<String, Object> container = new HashMap<>(5);

    private static final List<Vehicle> sharedVehicleList = new ArrayList<>();
    private static final MappingService mappingService = new MappingServiceImpl(sharedVehicleList);
    private static final ReaderService readerService = new ReaderServiceImpl();
    private static final CatalogueService catalogueService = new CatalogueServiceImpl(sharedVehicleList, mappingService);

    private Container(){}

    public static void init() {
        container.put(mappingService.getClass().getName(), mappingService);
        container.put(readerService.getClass().getName(), readerService);
        container.put(catalogueService.getClass().getName(), catalogueService);
        container.put(SHARED_LIST_NAME, sharedVehicleList);
    }

    public static Map<String, Object> getContainer() {
        return container;
    }
}
