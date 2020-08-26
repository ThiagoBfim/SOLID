package solid.tema;

import solid.plugin.Tema;

public class TemaParadize implements Tema {

    @Override
    public String cssDoTema() {
        return "li\t{\n" +
                "    border-bottom: 0.5px\tdashed\tred;\n" +
                "}\n" +
                "\n";
    }
}
