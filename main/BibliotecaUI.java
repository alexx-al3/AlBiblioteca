import javax.swing.*;
import java.awt.*;
import java.util.List;
import classe.Livro;

public class BibliotecaUI extends JFrame {

    public BibliotecaUI() {

        setTitle("Sistema de Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnLivro = new JButton("Cadastrar Livro");
        JButton btnUsuario = new JButton("Cadastrar Usuário");
        JButton btnEmprestar = new JButton("Emprestar Livro");
        JButton btnDevolver = new JButton("Devolver Livro");
        JButton btnListar = new JButton("Listar Livros");

        add(btnLivro);
        add(btnUsuario);
        add(btnEmprestar);
        add(btnDevolver);
        add(btnListar);

        // ===== AÇÕES =====

        btnLivro.addActionListener(e -> abrirCadastroLivro());
        btnUsuario.addActionListener(e -> abrirCadastroUsuario());
        btnEmprestar.addActionListener(e -> emprestarLivro());
        btnDevolver.addActionListener(e -> devolverLivro());
        btnListar.addActionListener(e -> listarLivros());

        setVisible(true);
    }

    // ===== CADASTRAR LIVRO =====

    private void abrirCadastroLivro() {

        JTextField titulo = new JTextField();
        JTextField autor = new JTextField();
        JTextField ano = new JTextField();

        Object[] campos = {
                "Título:", titulo,
                "Autor:", autor,
                "Ano:", ano
        };

        int opcao = JOptionPane.showConfirmDialog(
                null,
                campos,
                "Cadastrar Livro",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                int anoInt = Integer.parseInt(ano.getText());

                LivroService.cadastrarLivro(
                        titulo.getText(),
                        autor.getText(),
                        anoInt
                );

                JOptionPane.showMessageDialog(null, "Livro cadastrado!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    // ===== CADASTRAR USUÁRIO =====

    private void abrirCadastroUsuario() {

        JTextField nome = new JTextField();
        JTextField id = new JTextField();

        String[] tipos = {"aluno", "professor"};
        JComboBox<String> tipo = new JComboBox<>(tipos);

        Object[] campos = {
                "Nome:", nome,
                "ID:", id,
                "Tipo:", tipo
        };

        int opcao = JOptionPane.showConfirmDialog(
                null,
                campos,
                "Cadastrar Usuário",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            try {

                UsuarioService.cadastrarUsuario(
                        id.getText(),
                        nome.getText(),
                        tipo.getSelectedItem().toString()
                );

                JOptionPane.showMessageDialog(null, "Usuário cadastrado!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    // ===== EMPRESTAR LIVRO =====

    private void emprestarLivro() {

        JTextField livroId = new JTextField();
        JTextField usuarioId = new JTextField();

        Object[] campos = {
                "ID do Livro:", livroId,
                "ID do Usuário:", usuarioId
        };

        int opcao = JOptionPane.showConfirmDialog(
                null,
                campos,
                "Emprestar Livro",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            try {

                EmprestimoService.emprestarLivro(
                        livroId.getText(),
                        usuarioId.getText()
                );

                JOptionPane.showMessageDialog(null, "Livro emprestado!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    // ===== DEVOLVER LIVRO =====

    private void devolverLivro() {

        String livroId = JOptionPane.showInputDialog("ID do Livro:");

        try {
            EmprestimoService.devolverLivro(livroId);
            JOptionPane.showMessageDialog(null, "Livro devolvido!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    // ===== LISTAR LIVROS (CORRIGIDO DE VERDADE) =====

    private void listarLivros() {

        try {
            List<Livro> livros = LivroDAO.buscarTodos();

            StringBuilder lista = new StringBuilder();

            for (Livro l : livros) {
                lista.append(l.getId())
                     .append(" | ")
                     .append(l.getTitulo())
                     .append(" | ")
                     .append(l.getAutor())
                     .append(" | ")
                     .append(l.getAno())
                     .append(" | ")
                     .append(l.isDisponivel() ? "Disponível" : "Emprestado")
                     .append("\n");
            }

            JTextArea area = new JTextArea(lista.toString());
            area.setEditable(false);

            JOptionPane.showMessageDialog(
                    null,
                    new JScrollPane(area),
                    "Livros",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}