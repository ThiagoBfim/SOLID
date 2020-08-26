package solid.tema;

import solid.plugin.Tema;
import solid.plugin.TemaConfig;

@TemaConfig
public class TemaNeox implements Tema {

    @Override
    public String cssDoTema() {
        return "h1\t{\n" +
                "    border-bottom: 1px\tdashed\tblack;\n" +
                "    font-size: 3em;\n" +
                "}\n" +
                "h2\t{\n" +
                "    border-left: 1px\tsolid\tblack;\n" +
                "    padding-left: 5px;\n" +
                "    border-bottom: 1px\tsolid\tblack;\n" +
                "}\n" +
                "\n" +
                "blockquote {\n" +
                "    border: 1px solid black;\n" +
                "    padding: 5px;\n" +
                "}";
    }
}
