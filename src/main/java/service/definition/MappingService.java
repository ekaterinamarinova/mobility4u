package service.definition;

import record.Vehicle;

import java.util.List;

public interface MappingService {
    List<Vehicle> mapToObject(List<String> lines);
}
