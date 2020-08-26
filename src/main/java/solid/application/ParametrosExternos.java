package solid.application;

import solid.domain.Formato;

import java.nio.file.Path;

public interface ParametrosExternos {
    Formato getFormatEbook();

    Path getArquivoDeSaida();

    boolean isVerboso();

    Path getDiretorioDosMD();
}
