package classe;

import java.util.UUID;

public class Livro {

    private String id;
    private String titulo;
    private String autor;
    private int ano;

    public Livro(String titulo, String autor, int ano) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }
}
