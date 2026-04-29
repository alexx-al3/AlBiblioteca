public class UsuarioService {
    public static void cadastrarUsuario(String id, String nome, String tipo) {
        UsuarioDAO.inserir(new classe.Usuario(id, nome, tipo));
    }
}