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

        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro";

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

                lista.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static Livro buscarPorId(int id) {

        String sql = "SELECT * FROM livro WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAno(rs.getInt("ano"));
                l.setDisponivel(rs.getBoolean("disponivel"));
                return l;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void atualizarDisponibilidade(int id, boolean status) {

        String sql = "UPDATE livro SET disponivel = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}