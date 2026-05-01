import classe.Usuario;

public class UsuarioService {

    public static void cadastrarUsuario(String nome, String tipo) {
        Usuario u = new Usuario(nome, tipo);
        UsuarioDAO.inserir(u);
        System.out.println("Usuário criado com ID: " + u.getId());
    }
}