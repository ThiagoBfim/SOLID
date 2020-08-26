package solid.plugin;

import com.itextpdf.html2pdf.jsoup.Jsoup;
import com.itextpdf.html2pdf.jsoup.nodes.Document;

import java.util.List;

public class AplicadorTema {

    public String aplica(String conteudoHtml) {
        Document document = Jsoup.parse(conteudoHtml);
        List<String> listaDeTemas = Tema.listaDeTemas();
        for (String css : listaDeTemas) {
            document.select("head").append("<style>	" + css + "	</style>");
        }
        return document.html();
    }
}
