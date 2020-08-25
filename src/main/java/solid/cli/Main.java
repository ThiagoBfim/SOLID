package solid.cli;

import solid.application.Gerador;
import solid.application.ParametrosExternos;

public class Main {

    public static void main(String[] args) {
        ParametrosExternos leitorOpcoesCLI = new LeitorOpcoesCLI(args);
        boolean modoVerboso = leitorOpcoesCLI.isVerboso();
        try {
            new Gerador().gerar(leitorOpcoesCLI);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
    }

}
