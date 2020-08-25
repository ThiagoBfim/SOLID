package solid.application;

import java.nio.file.Path;

public interface ParametrosExternos {
    String getNomeFormatEbook();

    Path getArquivoDeSaida();

    boolean isVerboso();

    Path getDiretorioDosMD();
}
