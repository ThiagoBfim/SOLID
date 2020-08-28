package solid.tema;

import solid.domain.Capitulo;
import solid.plugin.Tema;

import java.util.List;

public class TemaParadize implements Tema {

    @Override
    public String cssDoTema() {
        return "li\t{\n" +
                "    border-bottom: 0.5px\tdashed\tred;\n" +
                "}\n" +
                "\n";
    }

    @Override
    public void actionBeforeGeneratePdf(List<Capitulo> capitulos) {

    }
}
