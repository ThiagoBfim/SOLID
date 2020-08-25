package solid.application;

import solid.domain.Capitulo;
import solid.domain.Ebook;
import solid.gerador.RenderizadorMDParaHTML;

import java.nio.file.Path;
import java.util.List;

public class Gerador {

    public void gerar(ParametrosExternos parametrosExternos) {
        final Path diretorioDosMD = parametrosExternos.getDiretorioDosMD();
        final String formato = parametrosExternos.getNomeFormatEbook();
        final Path arquivoDeSaida = parametrosExternos.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderizarHtml(diretorioDosMD);
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
