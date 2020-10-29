package service;

import record.Vehicle;
import service.definition.CatalogueService;
import service.definition.MappingService;
import service.definition.ReaderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InitService {

    private static final Map<String, Object> container = new HashMap<>(5);

    private static final List<Vehicle> sharedVehicleList = new ArrayList<>();
    private static final MappingService mappingService = new MappingServiceImpl(sharedVehicleList);
    private static final ReaderService readerService = new ReaderServiceImpl();
    private static final CatalogueService catalogueService = new CatalogueServiceImpl(sharedVehicleList);

    private InitService(){}

    public static void init() {
        container.put(mappingService.getClass().getName(), mappingService);
        container.put(readerService.getClass().getName(), readerService);
        container.put(catalogueService.getClass().getName(), catalogueService);
        container.put("sharedVehicleList", sharedVehicleList);
    }

    public static Map<String, Object> getContainer() {
        return container;
    }
}
