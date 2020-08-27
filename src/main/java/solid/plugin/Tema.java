package solid.plugin;


import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

public interface Tema {

    String cssDoTema();

    static List<String> listaDeTemas() {
        Reflections reflections = new Reflections("solid.tema");
        Set<Class<? extends Tema>> temas = reflections.getSubTypesOf(Tema.class);

        temas.addAll(ServiceLoader.load(Tema.class)
                .stream()
                .map(ServiceLoader.Provider::type)
                .collect(Collectors.toList()));

        return temas.stream()
                .filter(s -> s.getAnnotation(TemaConfig.class) != null)
                .map(Tema::getTema)
                .filter(Objects::nonNull)
                .map(Tema::cssDoTema)
                .collect(Collectors.toList());
    }

    static Tema getTema(Class<? extends Tema> s) {
        try {
            return s.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
