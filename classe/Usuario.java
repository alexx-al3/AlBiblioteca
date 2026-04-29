package classe;

import java.util.UUID;

public class Usuario {

    private String id;
    private String nome;
    private String tipo;

    public Usuario(String nome, String tipo) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
}
