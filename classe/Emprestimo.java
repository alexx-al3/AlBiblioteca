package classe;

import java.time.LocalDate;

public class Emprestimo {

    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
    }

    public void realizarEmprestimo() {
        if (livro.isDisponivel()) {
            livro.emprestar();
            System.out.println("Empréstimo realizado para " + usuario.getNome());
        } else {
            System.out.println("Não foi possível emprestar.");
        }
    }
}
