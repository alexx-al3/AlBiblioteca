import java.sql.*;

public class EmprestimoDAO {

    public static void emprestar(String livroId, String usuarioId) {

        String check = "SELECT disponivel FROM livro WHERE id = ?";
        String insert = "INSERT INTO emprestimo (livro_id, usuario_id) VALUES (?, ?)";
        String update = "UPDATE livro SET disponivel = false WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmtCheck = conn.prepareStatement(check);
            stmtCheck.setString(1, livroId);
            ResultSet rs = stmtCheck.executeQuery();

            if (rs.next() && rs.getBoolean("disponivel")) {

                PreparedStatement stmt1 = conn.prepareStatement(insert);
                stmt1.setString(1, livroId);
                stmt1.setString(2, usuarioId);
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement(update);
                stmt2.setString(1, livroId);
                stmt2.executeUpdate();

            } else {
                throw new RuntimeException("Livro indisponível!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void devolver(String livroId) {

        String updateLivro = "UPDATE livro SET disponivel = true WHERE id = ?";
        String updateEmp = "UPDATE emprestimo SET data_devolucao = NOW() WHERE livro_id = ? AND data_devolucao IS NULL";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt1 = conn.prepareStatement(updateLivro);
            stmt1.setString(1, livroId);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(updateEmp);
            stmt2.setString(1, livroId);
            stmt2.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}