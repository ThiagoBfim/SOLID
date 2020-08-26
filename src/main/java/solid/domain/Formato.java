package solid.domain;

import solid.gerador.GeradorEPUB;
import solid.gerador.GeradorPDF;

public enum Formato {

    PDF(new GeradorPDF()),
    EPUB(new GeradorEPUB());

    private GeradorEbook geradorEbook;

    Formato(GeradorEbook geradorEbook) {
        this.geradorEbook = geradorEbook;
    }

    public GeradorEbook getGeradorEbook() {
        return geradorEbook;
    }
}
