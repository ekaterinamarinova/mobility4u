package service.definition;

import java.io.Serializable;
import java.util.List;

public interface MappingService {
    List<? super Serializable> mapToObject(List<String> lines);
}
