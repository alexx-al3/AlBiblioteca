package classe;

public class Livro {

    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    // Encapsulamento
    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Método de negócio
    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Livro emprestado!");
        } else {
            System.out.println("Livro já está emprestado.");
        }
    }

    public void devolver() {
        disponivel = true;
        System.out.println("Livro devolvido!");
    }
}
