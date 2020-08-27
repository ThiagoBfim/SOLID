import org.junit.Before;
import org.junit.Test;
import solid.application.Gerador;
import solid.cli.LeitorOpcoesCLI;
import java.io.File;

public class GeradorTest {

    private String pathWithMdFiles;

    @Before
    public void setUp() {
        String projectPath = new File(System.getProperty("user.dir")).getParent();
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
