import classe.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public static void inserir(Usuario u) {

        String sql = "INSERT INTO usuario (id, nome, tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getId());
            stmt.setString(2, u.getNome());
            stmt.setString(3, u.getTipo());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}