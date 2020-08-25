package solid.application;


import org.junit.Before;
import org.junit.Test;
import solid.cli.LeitorOpcoesCLI;

public class GeradorTest {

    private String pathWithMdFiles;

    @Before
    public void setUp() {
        String projectPath = System.getProperty("user.dir");
        pathWithMdFiles = projectPath + "\\exemplo";
    }

    @Test
    public void testGeracaoEpub() {
        LeitorOpcoesCLI leitorOpcoesCLI = new LeitorOpcoesCLI("-d", pathWithMdFiles, "-f", "epub");
        new Gerador().gerar(leitorOpcoesCLI);
    }

    @Test
    public void testGeracaoPDF() {
        LeitorOpcoesCLI leitorOpcoesCLI = new LeitorOpcoesCLI("-d", pathWithMdFiles);
        new Gerador().gerar(leitorOpcoesCLI);
    }

}
