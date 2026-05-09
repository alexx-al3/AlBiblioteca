package classe;

import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        // prazo: professor tem 14 dias, aluno tem 7 dias
        int prazo = "professor".equalsIgnoreCase(usuario.getTipo()) ? 14 : 7;
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(prazo);
    }

    public boolean estaAtrasado() {
        return dataDevolucaoReal == null &&
               LocalDate.now().isAfter(dataDevolucaoPrevista);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }

    public LocalDate getDataDevolucaoReal() { return dataDevolucaoReal; }
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) { this.dataDevolucaoReal = dataDevolucaoReal; }
}
