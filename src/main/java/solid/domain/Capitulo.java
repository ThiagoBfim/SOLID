package solid.domain;

public class Capitulo {
    private StringBuilder conteudoHtml;
    private final String titulo;

    public Capitulo(StringBuilder conteudoHtml, String titulo) {
        this.conteudoHtml = conteudoHtml;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudoHtml() {
        return conteudoHtml.toString();
    }

    public void addConteudoFinal(String textoExtra) {
        conteudoHtml.append(textoExtra);
    }

}
