package classe;

public class Usuario extends Pessoa {

    public Usuario(String nome, String cpf) {
        super(nome, cpf);
    }

    public void exibirUsuario() {
        System.out.println("Usuário: " + getNome() + " - CPF: " + getCpf());
    }
}
