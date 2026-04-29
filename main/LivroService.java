public class LivroService {
    public static void cadastrarLivro(String titulo, String autor, int ano) {
        LivroDAO.inserir(new classe.Livro(titulo, autor, ano));
    }
}