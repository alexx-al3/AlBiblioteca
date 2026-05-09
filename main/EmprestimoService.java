import classe.Livro;
import classe.Usuario;
import java.sql.Connection;
import java.sql.SQLException;

public class EmprestimoService {

    private static final int LIMITE_ALUNO     = 3;
    private static final int LIMITE_PROFESSOR = 5;

    public static void emprestarLivro(int livroId, int usuarioId) {

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false); // inicia transação

            try {
                // 1. Buscar e validar livro (dentro da transação)
                Livro livro = LivroDAO.buscarPorId(livroId);
                if (livro == null)
                    throw new RuntimeException("Livro não encontrado.");

                // 2. Verificação atômica: livro já emprestado?
                if (EmprestimoDAO.livroEstaEmprestado(conn, livroId))
                    throw new RuntimeException("Este livro já está emprestado.");

                // 3. Buscar e validar usuário
                Usuario usuario = UsuarioDAO.buscarPorId(usuarioId);
                if (usuario == null)
                    throw new RuntimeException("Usuário não encontrado.");

                // 4. Verificar limite de empréstimos por tipo
                int limite = "professor".equalsIgnoreCase(usuario.getTipo())
                        ? LIMITE_PROFESSOR : LIMITE_ALUNO;
                int ativos = EmprestimoDAO.contarEmprestimosAtivos(conn, usuarioId);
                if (ativos >= limite)
                    throw new RuntimeException(
                        "Limite de empréstimos atingido. " + usuario.getTipo() +
                        " pode ter no máximo " + limite + " livro(s) emprestado(s)."
                    );

                // 5. Marcar livro como indisponível e registrar empréstimo
                LivroDAO.atualizarDisponibilidade(livroId, false);
                EmprestimoDAO.registrarEmprestimo(conn, livroId, usuarioId);

                conn.commit(); // confirma tudo de uma vez

            } catch (Exception e) {
                conn.rollback(); // desfaz se qualquer etapa falhou
                throw new RuntimeException(e.getMessage(), e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão ao emprestar livro: " + e.getMessage(), e);
        }
    }

    public static void devolverLivro(int livroId) {

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // 1. Verificar se livro existe
                Livro livro = LivroDAO.buscarPorId(livroId);
                if (livro == null)
                    throw new RuntimeException("Livro não encontrado.");

                // 2. Verificar se o livro realmente está emprestado
                if (livro.isDisponivel())
                    throw new RuntimeException("Este livro não está emprestado.");

                // 3. Registrar devolução e liberar o livro
                EmprestimoDAO.registrarDevolucao(conn, livroId);
                LivroDAO.atualizarDisponibilidade(livroId, true);

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException(e.getMessage(), e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão ao devolver livro: " + e.getMessage(), e);
        }
    }
}
