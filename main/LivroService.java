import classe.Livro;

public class LivroService {

    public static void cadastrarLivro(String titulo, String autor, int ano) {
        LivroDAO.inserir(new Livro(titulo, autor, ano));
    }

    public static void listarLivros() {

        LivroDAO.buscarTodos().forEach(l ->
            System.out.println(
                l.getId() + " | " +
                l.getTitulo() + " | " +
                l.getAutor() + " | " +
                l.getAno() + " | " +
                (l.isDisponivel() ? "Disponível" : "Emprestado")
            )
        );
    }
}