import java.util.List;
import classe.Livro;

public class LivroService {

    public static void cadastrarLivro(String titulo, String autor, int ano) {
        LivroDAO.inserir(new Livro(titulo, autor, ano));
    }

    public static List<Livro> listarLivros() {
        return LivroDAO.buscarTodos();
    }
}