package br.com.servico;

import solid.domain.Capitulo;
import solid.plugin.AcaoAntesGeracaoPDF;
import solid.plugin.PluginConfig;

import java.util.Iterator;
import java.util.List;

@PluginConfig
public class GerarAssinatura implements AcaoAntesGeracaoPDF {

    @Override
    public void action(Iterator<Capitulo> capitulos) {
        var assinatura =
                "<div style=\"background-color: #e0ebeb; padding:10px\">\n" +
                        "<p>Joseph</p>" +
                        "<p style='text-align:right'>Brasília, 1 de Setembro de 2020</p>\n" +
                        "</div>";

        getLastElement(capitulos).addConteudo(assinatura);
    }

    public static <T> T getLastElement(final Iterator<T> elements) {
        T lastElement = null;
        while (elements.hasNext()) {
            lastElement = elements.next();
        }
        return lastElement;
    }
}
