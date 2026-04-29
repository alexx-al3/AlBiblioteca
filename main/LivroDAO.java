import classe.Livro;
import java.sql.*;

public class LivroDAO {

    public static void inserir(Livro livro) {

        String sql = "INSERT INTO livro (id, titulo, autor, ano, disponivel) VALUES (?, ?, ?, ?, true)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getId());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setInt(4, livro.getAno());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet buscarTodos(Connection conn) throws Exception {
        String sql = "SELECT * FROM livro";
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public static ResultSet buscarPorTitulo(Connection conn, String titulo) throws Exception {
        String sql = "SELECT * FROM livro WHERE LOWER(titulo) LIKE LOWER(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + titulo + "%");
        return stmt.executeQuery();
    }
}