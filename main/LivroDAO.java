
import classe.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LivroDAO {

    public static void inserir(Livro livro) {

        String sql = "INSERT INTO livro VALUES (?, ?, ?, ?, true)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getId());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setInt(4, livro.getAno());

            stmt.executeUpdate();
            System.out.println("Livro salvo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listar() {

        String sql = "SELECT * FROM livro";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("id") + " | " +
                                rs.getString("titulo") + " | " +
                                rs.getString("autor") + " | " +
                                rs.getInt("ano") + " | " +
                                (rs.getBoolean("disponivel") ? "Disponível" : "Emprestado"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}