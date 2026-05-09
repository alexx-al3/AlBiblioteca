import java.sql.*;

public class EmprestimoDAO {

    public static void registrarEmprestimo(Connection conn, int livroId, int usuarioId) throws SQLException {
        String sql = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao_prevista) " +
                     "VALUES (?, ?, CURRENT_DATE, CURRENT_DATE + INTERVAL '7 days')";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        }
    }

    public static void registrarDevolucao(Connection conn, int livroId) throws SQLException {
        String sql = "UPDATE emprestimo SET data_devolucao = CURRENT_DATE " +
                     "WHERE livro_id = ? AND data_devolucao IS NULL";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0)
                throw new SQLException("Nenhum empréstimo ativo encontrado para este livro.");
        }
    }

    public static int contarEmprestimosAtivos(Connection conn, int usuarioId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE usuario_id = ? AND data_devolucao IS NULL";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    public static boolean livroEstaEmprestado(Connection conn, int livroId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE livro_id = ? AND data_devolucao IS NULL";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
