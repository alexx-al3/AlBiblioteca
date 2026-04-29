import java.sql.*;
import classe.Usuario;

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
            e.printStackTrace();
        }
    }

    public static Usuario buscarPorId(String id) {

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario(rs.getString("nome"), rs.getString("tipo"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}