import classe.Usuario;

public class UsuarioService {

    private static final java.util.Set<String> TIPOS_VALIDOS = java.util.Set.of("aluno", "professor");

    public static Usuario cadastrarUsuario(String nome, String cpf, String tipo) {

        // Validações de campo vazio
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (cpf == null || cpf.isBlank())
            throw new IllegalArgumentException("CPF é obrigatório.");
        if (tipo == null || tipo.isBlank())
            throw new IllegalArgumentException("Tipo é obrigatório.");

        // Validação de tipo
        if (!TIPOS_VALIDOS.contains(tipo.toLowerCase()))
            throw new IllegalArgumentException("Tipo inválido. Use 'aluno' ou 'professor'.");

        // Validação de formato CPF (apenas dígitos, 11 caracteres)
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        if (cpfNumerico.length() != 11)
            throw new IllegalArgumentException("CPF inválido. Digite 11 dígitos.");

        // Verificação de CPF duplicado
        if (UsuarioDAO.existePorCpf(cpfNumerico))
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este CPF.");

        Usuario u = new Usuario(nome.trim(), cpfNumerico, tipo.toLowerCase());
        UsuarioDAO.inserir(u);
        return u;
    }
}
