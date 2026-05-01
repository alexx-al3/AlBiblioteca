import classe.Usuario;
import classe.Livro;

public class EmprestimoService {

    public static void emprestarLivro(int livroId, int usuarioId) {
        Livro livro = LivroDAO.buscarPorId(livroId);
        if (livro == null) throw new RuntimeException("Livro não encontrado!");
        if (!livro.isDisponivel()) throw new RuntimeException("Livro já está emprestado!");

        Usuario usuario = UsuarioDAO.buscarPorId(usuarioId);
        if (usuario == null) throw new RuntimeException("Usuário não encontrado!");

        LivroDAO.atualizarDisponibilidade(livroId, false);
        EmprestimoDAO.registrarEmprestimo(livroId, usuarioId);
    }

    public static void devolverLivro(int livroId) {
        Livro livro = LivroDAO.buscarPorId(livroId);
        if (livro == null) throw new RuntimeException("Livro não encontrado!");

        LivroDAO.atualizarDisponibilidade(livroId, true);
        EmprestimoDAO.registrarDevolucao(livroId);
    }
}