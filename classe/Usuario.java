package classe;

public class Usuario extends Pessoa {

    private int id;
    private String tipo;

    public Usuario(String nome, String cpf, String tipo) {
        super(nome, cpf);
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return id + " | " + getNome() + " | " + getCpf() + " | " + tipo;
    }
}
