import java.time.LocalDate;
import java.util.List;
import classe.Livro;

public class LivroService {

    public static Livro cadastrarLivro(String titulo, String autor, String anoStr) {

        // Validações de campo vazio
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título é obrigatório.");
        if (autor == null || autor.isBlank())
            throw new IllegalArgumentException("Autor é obrigatório.");
        if (anoStr == null || anoStr.isBlank())
            throw new IllegalArgumentException("Ano é obrigatório.");

        // Validação de formato do ano
        int ano;
        try {
            ano = Integer.parseInt(anoStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ano inválido. Digite apenas números.");
        }

        int anoAtual = LocalDate.now().getYear();
        if (ano < 1000 || ano > anoAtual)
            throw new IllegalArgumentException("Ano deve estar entre 1000 e " + anoAtual + ".");

        // Verificação de duplicata
        if (LivroDAO.existePorTituloEAutor(titulo.trim(), autor.trim()))
            throw new IllegalArgumentException("Já existe um livro com este título e autor cadastrado.");

        Livro livro = new Livro(titulo.trim(), autor.trim(), ano);
        LivroDAO.inserir(livro);
        return livro;
    }

    public static List<Livro> listarLivros() {
        return LivroDAO.buscarTodos();
    }
}
