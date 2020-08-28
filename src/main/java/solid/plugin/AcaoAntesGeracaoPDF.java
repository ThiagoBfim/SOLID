package solid.plugin;


import org.reflections.Reflections;
import solid.domain.Capitulo;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

public interface AcaoAntesGeracaoPDF {

    void action(Iterator<Capitulo> capitulos);

    static void doActionBeforeGeneratePdf(Iterator<Capitulo> capitulos) {
        Reflections reflections = new Reflections("solid.tema");
        Set<Class<? extends AcaoAntesGeracaoPDF>> temas = reflections.getSubTypesOf(AcaoAntesGeracaoPDF.class);

        temas.addAll(ServiceLoader.load(AcaoAntesGeracaoPDF.class)
                .stream()
                .map(ServiceLoader.Provider::type)
                .collect(Collectors.toList()));

        temas.stream()
                .filter(s -> s.getAnnotation(PluginConfig.class) != null)
                .map(AcaoAntesGeracaoPDF::getAcaoAntesGeracaoPDF)
                .filter(Objects::nonNull)
                .forEach(s -> s.action(capitulos));
    }

    static AcaoAntesGeracaoPDF getAcaoAntesGeracaoPDF(Class<? extends AcaoAntesGeracaoPDF> s) {
        try {
            return s.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
