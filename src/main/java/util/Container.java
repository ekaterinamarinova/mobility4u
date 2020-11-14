package util;

import model.Vehicle;
import service.CatalogueServiceImpl;
import service.FileOpsServiceImpl;
import service.MappingServiceImpl;
import service.definition.CatalogueService;
import service.definition.FileOpsService;
import service.definition.MappingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Constants.SHARED_LIST_NAME;

/**
 * This is a utility class, used as an
 * aliexpress version of an actual IOC container.
 * Here we instantiate all the needed services and other classes
 * and make the relationships between them, so we can use the
 * same shared instances runtime.
 *
 * P.S I was originally thinking of adding them as beans in an xml file
 * with a custom schema, but then i realized that for the means of the current
 * project this actually is a much cleaner implementation.
 *
 * P.S.S Also in the future I'm thinking of generifying this,
 * exporting it as a different module/project and using reflection and
 * some config classes to make it an actual IOC DI framework.
 *
 * The main thing is that, in order to be generified, beans and services must have something in common.
 * That thing could be either a common interface which they implement, or they can be marked with an annotation.
 */
public final class Container {

    private static final Map<String, Object> container = new HashMap<>(5);

    private static final List<Vehicle> sharedVehicleList = new ArrayList<>();
    private static final MappingService mappingService = new MappingServiceImpl(sharedVehicleList);
    private static final FileOpsService fileOps = new FileOpsServiceImpl();
    private static final CatalogueService catalogueService = new CatalogueServiceImpl(sharedVehicleList, mappingService, fileOps);

    private Container(){}

    /**
     * Store all the instances in the shared map.
     */
    public static void init() {
        container.put(mappingService.getClass().getName(), mappingService);
        container.put(fileOps.getClass().getName(), fileOps);
        container.put(catalogueService.getClass().getName(), catalogueService);
        container.put(SHARED_LIST_NAME, sharedVehicleList);
    }

    /**
     * @return - A reference to the shared container, from which beans can be fetched during runtime.
     */
    public static Map<String, Object> getContainer() {
        return container;
    }
}
