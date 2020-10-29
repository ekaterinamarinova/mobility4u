package reflection;

import annotation.Component;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class ComponentReflectionService {

    private final List<Object> components = new CopyOnWriteArrayList<>();

    void walk() {
        try(Stream<Path> pathStream = Files.walk(Path.of("/mobility4u"))) {
             pathStream
                    .filter(file -> file.endsWith(".java"))
                    .forEach(file -> {
                        if (file.getClass().isAnnotationPresent(Component.class))
                            components.add(file);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Object o:components) {
            var clazz = o.getClass();
            var constructors = clazz.getConstructors();

            for (Constructor constructor:constructors) {
                constructor.getParameterCount();
            }
        }

    }

}
