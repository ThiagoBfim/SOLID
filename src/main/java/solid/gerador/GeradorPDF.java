package solid.gerador;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;
import solid.domain.Capitulo;
import solid.domain.Ebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorPDF {

    public void gerarEbook(Ebook ebook) {
        final Path arquivoDeSaida = ebook.getArquivoSaida();
        try (PdfWriter writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
             PdfDocument pdf = new PdfDocument(writer);
             Document pdfDocument = new Document(pdf)) {

            for (Capitulo capitulo : ebook.getCapitulos()) {
                generatePdf(pdfDocument, capitulo);
                if (ebook.isNotLastCapitulo(capitulo)) {
                    skipPage(pdfDocument);
                }

            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro	ao	criar	arquivo	PDF:	" + ebook.getArquivoSaida().toAbsolutePath(), ex);
        }
    }

    private void skipPage(Document pdfDocument) {
        pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }

    private void generatePdf(Document pdfDocument, Capitulo capitulo) throws IOException {
        String html = capitulo.getConteudoHtml();
        List<IElement> convertToElements = HtmlConverter.convertToElements(html);
        for (IElement element : convertToElements) {
            pdfDocument.add((IBlockElement) element);
        }
    }
}
