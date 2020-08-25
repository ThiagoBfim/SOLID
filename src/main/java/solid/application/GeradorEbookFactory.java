package solid.application;

import solid.domain.GeradorEbook;
import solid.gerador.GeradorEPUB;
import solid.gerador.GeradorPDF;

public class GeradorEbookFactory {

    public static GeradorEbook fabricar(String type) {
        if ("pdf".equals(type)) {
            return new GeradorPDF();
        } else if ("epub".equals(type)) {
            return new GeradorEPUB();
        } else {
            throw new RuntimeException("Formato do ebook inválido: " + type);
        }
    }
}
