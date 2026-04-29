import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmprestimoDAO {

    public static void registrarEmprestimo(int livroId, String usuarioId) {

        String sql = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo) VALUES (?, ?, NOW())";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livroId);
            stmt.setString(2, usuarioId);

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