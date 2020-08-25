package solid.application;

import solid.gerador.RenderizadorMDParaHTML;

public class RenderizadorFactory {

    public static Renderizador fabricar() {
        return new RenderizadorMDParaHTML();
    }
}
