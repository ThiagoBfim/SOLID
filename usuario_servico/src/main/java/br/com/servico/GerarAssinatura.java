package br.com.servico;

import solid.domain.Capitulo;
import solid.plugin.AcaoAntesGeracaoPDF;
import solid.plugin.PluginConfig;

import java.util.List;

@PluginConfig
public class GerarAssinatura implements AcaoAntesGeracaoPDF {

    @Override
    public void action(List<Capitulo> capitulos) {
        var assinatura =
                "<div style=\"background-color: #e0ebeb; padding:10px\">\n" +
                        "<p>Joseph</p>" +
                        "<p style='text-align:right'>Brasília, 1 de Setembro de 2020</p>\n" +
                        "</div>";
        long count = capitulos.size();
        capitulos.stream().skip(count - 1).findFirst()
                .ifPresent(c -> c.setConteudoHtml(c.getConteudoHtml() + assinatura));
    }
}
