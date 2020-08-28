package br.com.servico;

import solid.domain.Capitulo;
import solid.plugin.Tema;
import solid.plugin.TemaConfig;

import java.util.List;

@TemaConfig
public class TemaUsuario implements Tema {

    public void acaoAntesGeracaoPDF(List<Capitulo> capitulos) {
        var assinatura =
                "<div style=\"background-color: #e0ebeb; padding:10px\">\n" +
                        "<p>Joseph</p>" +
                        "<p style='text-align:right'>Brasília, 1 de Setembro de 2020</p>\n" +
                        "</div>";
        long count = capitulos.size();
        capitulos.stream().skip(count - 1).findFirst()
                .ifPresent(c -> c.setConteudoHtml(c.getConteudoHtml() + assinatura));
    }

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
