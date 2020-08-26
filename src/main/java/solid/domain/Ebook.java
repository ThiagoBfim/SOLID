package solid.domain;

import java.nio.file.Path;
import java.util.List;

public class Ebook {
    private Formato formato;
    private Path arquivoSaida;
    private List<Capitulo> capitulos;

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public Path getArquivoSaida() {
        return arquivoSaida;
    }

    public void setArquivoSaida(Path arquivoSaida) {
        this.arquivoSaida = arquivoSaida;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public boolean isLastCapitulo(Capitulo capitulo) {
        return capitulos.get(capitulos.size() -1).equals(capitulo);
    }

    public boolean isNotLastCapitulo(Capitulo capitulo) {
        return !isLastCapitulo(capitulo);
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

}
