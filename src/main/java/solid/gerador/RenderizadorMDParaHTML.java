package solid.gerador;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import solid.application.Renderizador;
import solid.domain.Capitulo;
import solid.domain.builder.CapituloBuilder;
import solid.plugin.AcaoAntesGeracaoPDF;
import solid.plugin.AplicadorTema;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RenderizadorMDParaHTML implements Renderizador {

    private List<Capitulo> capitulos = new ArrayList<>();

    @Override
    public List<Capitulo> renderizarHtml(Path diretorioDosMD) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
            arquivosMD
                    .filter(matcher::matches)
                    .sorted()
                    .forEach(this::criarCapitulo);
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
        }
        AcaoAntesGeracaoPDF.doActionBeforeGeneratePdf(capitulos.iterator());
        return capitulos;
    }

    private void criarCapitulo(Path arquivoMD) {
        CapituloBuilder capituloBuilder = new CapituloBuilder();
        Parser parser = Parser.builder().build();
        Node document;
        try {
            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
            document.accept(new VisitorHeadingHtml(capituloBuilder));
            try {
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                String render = renderer.render(document);
                AplicadorTema tema = new AplicadorTema();
                String html = tema.aplica(render);
                capituloBuilder.setConteudoHtml(html);
                capitulos.add(capituloBuilder.build());
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
    }

    private static class VisitorHeadingHtml extends AbstractVisitor {
        private final CapituloBuilder capituloBuilder;

        public VisitorHeadingHtml(CapituloBuilder capituloBuilder) {
            this.capituloBuilder = capituloBuilder;
        }

        @Override
        public void visit(Heading heading) {
            if (heading.getLevel() == 1) {
                String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                capituloBuilder.setTitulo(tituloDoCapitulo);
            } else if (heading.getLevel() == 2) {
                // seção
            } else if (heading.getLevel() == 3) {
                // título
            }
        }

    }
}
