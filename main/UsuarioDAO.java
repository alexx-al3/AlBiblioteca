import classe.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public static void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, tipo) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTipo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario(rs.getString("nome"), rs.getString("tipo"));
                u.setId(rs.getInt("id"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}