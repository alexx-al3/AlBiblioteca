package classe;

import java.util.ArrayList;

public class Sistema {

    public static ArrayList<Livro> livros = new ArrayList<>();
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public static Livro buscarLivro(String id) {
        for (Livro l : livros) {
            if (l.getId().equals(id))
                return l;
        }
        return null;
    }

    public static Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id))
                return u;
        }
        return null;
    }
}