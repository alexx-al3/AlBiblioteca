import classe.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    public static void inserir(Usuario usuario) {

        String sql = "INSERT INTO usuario (id, nome, tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getTipo());

            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário!");
            e.printStackTrace();
        }
    }
}