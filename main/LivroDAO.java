import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import classe.Livro;

public class LivroDAO {

    public static void inserir(Livro livro) {

        String sql = "INSERT INTO livro (titulo, autor, ano, disponivel) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, true);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Livro> buscarTodos() {

        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAno(rs.getInt("ano"));
                l.setDisponivel(rs.getBoolean("disponivel"));

                livros.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return livros;
    }
}