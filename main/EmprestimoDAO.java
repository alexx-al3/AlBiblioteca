import java.sql.*;

public class EmprestimoDAO {

    public static void registrarEmprestimo(int livroId, int usuarioId) {
        String sql = "INSERT INTO emprestimo (livro_id, usuario_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livroId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registrarDevolucao(int livroId) {
        String sql = "UPDATE emprestimo SET data_devolucao = NOW() WHERE livro_id = ? AND data_devolucao IS NULL";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livroId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}