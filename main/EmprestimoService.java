import classe.Usuario;
import classe.Livro;
public class EmprestimoService {

    public static void emprestarLivro(int livroId, String usuarioId) {

        Livro livro = LivroDAO.buscarPorId(livroId);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Livro já está emprestado!");
            return;
        }

        Usuario usuario = UsuarioDAO.buscarPorId(usuarioId);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        LivroDAO.atualizarDisponibilidade(livroId, false);
        EmprestimoDAO.registrarEmprestimo(livroId, usuarioId);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public static void devolverLivro(int livroId) {

        Livro livro = LivroDAO.buscarPorId(livroId);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        LivroDAO.atualizarDisponibilidade(livroId, true);
        EmprestimoDAO.registrarDevolucao(livroId);

        System.out.println("Livro devolvido com sucesso!");
    }
}