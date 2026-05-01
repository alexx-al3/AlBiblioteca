package classe;

public class Usuario {

    private int id;
    private String nome;
    private String tipo;

    public Usuario(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
}