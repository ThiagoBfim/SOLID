package solid.application;

import solid.domain.Formato;
import solid.domain.GeradorEbook;

public class GeradorEbookFactory {

    public static GeradorEbook fabricar(Formato formato) {
        return formato.getGeradorEbook();
    }
}
