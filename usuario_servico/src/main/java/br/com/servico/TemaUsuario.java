package br.com.servico;

import solid.plugin.Tema;
import solid.plugin.TemaConfig;

@TemaConfig
public class TemaUsuario implements Tema {

    @Override
    public String cssDoTema() {
        return "h1\t{\n" +
                "    border-bottom: 1px\tdashed\tred;\n" +
                "    font-size: 3em;\n" +
                "}\n" +
                "h2\t{\n" +
                "    border-left: 1px\tsolid\tred;\n" +
                "    padding-left: 5px;\n" +
                "    border-bottom: 1px\tsolid\tred;\n" +
                "}\n" +
                "\n" +
                "blockquote {\n" +
                "    border: 1px solid red;\n" +
                "    padding: 5px;\n" +
                "}";
    }
}
