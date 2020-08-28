package solid.application;

import solid.domain.Capitulo;
import solid.domain.Ebook;
import solid.domain.Formato;
import solid.gerador.RenderizadorMDParaHTML;
import solid.plugin.Tema;

import java.nio.file.Path;
import java.util.List;

public class Gerador {

    public void gerar(ParametrosExternos parametrosExternos) {
        final Path diretorioDosMD = parametrosExternos.getDiretorioDosMD();
        final Formato formato = parametrosExternos.getFormatEbook();
        final Path arquivoDeSaida = parametrosExternos.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderizarHtml(diretorioDosMD);
        Tema.doActionBeforeGeneratePdf(capitulos);
        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbookFactory
                .fabricar(formato)
                .gerarEbook(ebook);

        System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

    }
}
