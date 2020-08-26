package solid.cli;

import org.apache.commons.cli.*;
import solid.application.ParametrosExternos;
import solid.domain.Formato;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeitorOpcoesCLI implements ParametrosExternos {
    private String[] args;
    private CommandLine cmd;

    public LeitorOpcoesCLI(String... args) {
        this.args = args;
        invoke();
    }

    private void invoke() {
        Options options = new Options();

        Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
                "Diretório que contem os arquivos md. Default: diretório atual.");
        options.addOption(opcaoDeDiretorioDosMD);

        Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
                "Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
        options.addOption(opcaoDeFormatoDoEbook);

        Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
                "Arquivo de saída do ebook. Default: book.{formato}.");
        options.addOption(opcaoDeArquivoDeSaida);

        Option opcaoModoVerboso = new Option("v", "verbose", false,
                "Habilita modo verboso.");
        options.addOption(opcaoModoVerboso);

        CommandLineParser cmdParser = new DefaultParser();

        try {
            cmd = cmdParser.parse(options, args);
        } catch (ParseException e) {
            HelpFormatter ajuda = new HelpFormatter();
            ajuda.printHelp("solid", options);
            throw new RuntimeException("Opção inválida", e);

        }
    }

    @Override
    public Path getDiretorioDosMD() {
        Path diretorioDosMD;
        String nomeDoDiretorioDosMD = getNomeDiretorioMD();
        if (nomeDoDiretorioDosMD != null) {
            diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
            if (!Files.isDirectory(diretorioDosMD)) {
                throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
            }
        } else {
            diretorioDosMD = Paths.get("");
        }
        return diretorioDosMD;

    }

    @Override
    public Formato getFormatEbook() {
        return Formato.valueOf(getNomeFormatEbook().toUpperCase());
    }

    public String getNomeFormatEbook() {
        String nomeDoFormatoDoEbook = getFormat();
        if (nomeDoFormatoDoEbook != null) {
            return nomeDoFormatoDoEbook.toLowerCase();
        } else {
            return "pdf";
        }
    }

    @Override
    public Path getArquivoDeSaida() {
        Path arquivoDeSaida;
        String nomeDoArquivoDeSaidaDoEbook = getNomeArquivoSaida();
        if (nomeDoArquivoDeSaidaDoEbook != null) {
            arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
            if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
                throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
            }
        } else {
            arquivoDeSaida = Paths.get("book." + getNomeFormatEbook().toLowerCase());
        }
        return arquivoDeSaida;

    }

    @Override
    public boolean isVerboso() {
        return cmd.hasOption("verbose");
    }

    private String getFormat() {
        return cmd.getOptionValue("format");
    }

    private String getNomeDiretorioMD() {
        return cmd.getOptionValue("dir");
    }

    public String getNomeArquivoSaida() {
        return cmd.getOptionValue("output");
    }

}
