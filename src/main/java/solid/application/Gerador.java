package solid.application;

import solid.cli.LeitorOpcoesCLI;
import solid.domain.Capitulo;
import solid.domain.Ebook;
import solid.gerador.GeradorEPUB;
import solid.gerador.GeradorPDF;
import solid.gerador.RenderizadorMDParaHTML;

import java.nio.file.Path;
import java.util.List;

public class Gerador {

    public void gerar(LeitorOpcoesCLI leitorOpcoesCLI) {
        final Path diretorioDosMD = leitorOpcoesCLI.getDiretorioDosMD();
        final String formato = leitorOpcoesCLI.getNomeFormatEbook();
        final Path arquivoDeSaida = leitorOpcoesCLI.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderizarHtml(diretorioDosMD);
        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            GeradorPDF geradorPDF = new GeradorPDF();
            geradorPDF.gerarEbook(ebook);
        } else if ("epub".equals(formato)) {
            GeradorEPUB geradorEPUB = new GeradorEPUB();
            geradorEPUB.gerarEbook(ebook);
        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }

        System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

    }
}
