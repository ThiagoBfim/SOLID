package solid.application;

import solid.cli.LeitorOpcoesCLI;
import solid.gerador.EPUBGenerator;
import solid.gerador.PDFGenerator;

import java.nio.file.Path;

public class Gerador {

    public void gerar(LeitorOpcoesCLI leitorOpcoesCLI) {
        final Path diretorioDosMD = leitorOpcoesCLI.getDiretorioDosMD();
        final String formato = leitorOpcoesCLI.getNomeFormatEbook();
        final Path arquivoDeSaida = leitorOpcoesCLI.getArquivoDeSaida();

        if ("pdf".equals(formato)) {
            new PDFGenerator().gerar(arquivoDeSaida, diretorioDosMD);
        } else if ("epub".equals(formato)) {
            new EPUBGenerator().gerar(arquivoDeSaida, diretorioDosMD);
        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }

        System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

    }
}
