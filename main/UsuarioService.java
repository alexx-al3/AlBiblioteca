import classe.Usuario;

public class UsuarioService {

    public static Usuario cadastrarUsuario(String nome, String tipo) {
        Usuario u = new Usuario(nome, tipo);
        UsuarioDAO.inserir(u);
        return u;
    }
}