package solid.gerador;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import solid.domain.Capitulo;
import solid.domain.Ebook;
import solid.domain.GeradorEbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorEPUB implements GeradorEbook {

    @Override
    public void gerarEbook(Ebook ebook) {
        final Path arquivoDeSaida = ebook.getArquivoSaida();
        Book epub = new Book();
        for (Capitulo capitulo : ebook.getCapitulos()) {
            String html = capitulo.getConteudoHtml();
            String tituloDoCapitulo = capitulo.getTitulo();
            epub.addSection(tituloDoCapitulo, new Resource(html.getBytes(), MediatypeService.XHTML));
        }
        EpubWriter epubWriter = new EpubWriter();
        try {
            epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao	criar	arquivo	EPUB:	" + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }
}
