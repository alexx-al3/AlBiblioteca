
import classe.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    public static void inserir(Usuario u) {

        String sql = "INSERT INTO usuario VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getId());
            stmt.setString(2, "Nome");
            stmt.setString(3, "aluno");

            stmt.executeUpdate();
            System.out.println("Usuário salvo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}