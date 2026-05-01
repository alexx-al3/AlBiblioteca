import javax.swing.*;
import java.awt.*;
import java.util.List;
import classe.Livro;
import classe.Usuario;

public class BibliotecaUI extends JFrame {

    public BibliotecaUI() {

        setTitle("AlBiblioteca");
        setSize(400, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(30, 80, 160));
        JLabel titulo = new JLabel("📚 Sistema de Biblioteca");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        cabecalho.add(titulo);

        JPanel painelBotoes = new JPanel(new GridLayout(6, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 20));

        JButton btnLivro = new JButton("Cadastrar Livro");
        JButton btnUsuario = new JButton("Cadastrar Usuário");
        JButton btnEmprestar = new JButton("Emprestar Livro");
        JButton btnDevolver = new JButton("Devolver Livro");
        JButton btnListarLivros = new JButton("Listar Livros");
        JButton btnListarUsuarios = new JButton("Listar Usuários");

        painelBotoes.add(btnLivro);
        painelBotoes.add(btnUsuario);
        painelBotoes.add(btnEmprestar);
        painelBotoes.add(btnDevolver);
        painelBotoes.add(btnListarLivros);
        painelBotoes.add(btnListarUsuarios);

        add(cabecalho, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        btnLivro.addActionListener(e -> abrirCadastroLivro());
        btnUsuario.addActionListener(e -> abrirCadastroUsuario());
        btnEmprestar.addActionListener(e -> emprestarLivro());
        btnDevolver.addActionListener(e -> devolverLivro());
        btnListarLivros.addActionListener(e -> listarLivros());
        btnListarUsuarios.addActionListener(e -> listarUsuarios());

        setVisible(true);
    }

    private void abrirCadastroLivro() {

        JTextField titulo = new JTextField();
        JTextField autor = new JTextField();
        JTextField ano = new JTextField();

        Object[] campos = {
                "Título:", titulo,
                "Autor:", autor,
                "Ano:", ano
        };

        int opcao = JOptionPane.showConfirmDialog(null, campos, "Cadastrar Livro", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                LivroService.cadastrarLivro(titulo.getText(), autor.getText(), Integer.parseInt(ano.getText()));

                List<Livro> livros = LivroService.listarLivros();
                Livro ultimo = livros.get(livros.size() - 1);

                JOptionPane.showMessageDialog(null,
                        "✅ Livro cadastrado com sucesso!\n\n" +
                        "ID     : " + ultimo.getId() + "\n" +
                        "Título : " + ultimo.getTitulo() + "\n" +
                        "Autor  : " + ultimo.getAutor() + "\n" +
                        "Ano    : " + ultimo.getAno(),
                        "Livro Cadastrado",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    private void abrirCadastroUsuario() {

        JTextField nome = new JTextField();
        String[] tipos = {"aluno", "professor"};
        JComboBox<String> tipo = new JComboBox<>(tipos);

        Object[] campos = {
                "Nome:", nome,
                "Tipo:", tipo
        };

        int opcao = JOptionPane.showConfirmDialog(null, campos, "Cadastrar Usuário", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Usuario u = UsuarioService.cadastrarUsuario(nome.getText(), tipo.getSelectedItem().toString());

                JOptionPane.showMessageDialog(null,
                        "✅ Usuário cadastrado com sucesso!\n\n" +
                        "ID   : " + u.getId() + "\n" +
                        "Nome : " + u.getNome() + "\n" +
                        "Tipo : " + u.getTipo(),
                        "Usuário Cadastrado",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    private void emprestarLivro() {

        JTextField livroId = new JTextField();
        JTextField usuarioId = new JTextField();

        Object[] campos = {
                "ID do Livro:", livroId,
                "ID do Usuário:", usuarioId
        };

        int opcao = JOptionPane.showConfirmDialog(null, campos, "Emprestar Livro", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                EmprestimoService.emprestarLivro(
                        Integer.parseInt(livroId.getText()),
                        Integer.parseInt(usuarioId.getText())
                );
                JOptionPane.showMessageDialog(null, "✅ Empréstimo realizado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    private void devolverLivro() {

        String livroId = JOptionPane.showInputDialog("ID do Livro:");

        try {
            EmprestimoService.devolverLivro(Integer.parseInt(livroId));
            JOptionPane.showMessageDialog(null, "✅ Livro devolvido com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    private void listarLivros() {

        try {
            List<Livro> livros = LivroService.listarLivros();
            StringBuilder lista = new StringBuilder();

            for (Livro l : livros) {
                lista.append(l.getId())
                        .append(" | ").append(l.getTitulo())
                        .append(" | ").append(l.getAutor())
                        .append(" | ").append(l.getAno())
                        .append(" | ").append(l.isDisponivel() ? "Disponível" : "Emprestado")
                        .append("\n");
            }

            JTextArea area = new JTextArea(lista.toString());
            area.setEditable(false);

            JOptionPane.showMessageDialog(null, new JScrollPane(area), "Livros Cadastrados", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    private void listarUsuarios() {

        try {
            List<Usuario> usuarios = UsuarioDAO.buscarTodos();
            StringBuilder lista = new StringBuilder();

            for (Usuario u : usuarios) {
                lista.append(u.getId())
                        .append(" | ").append(u.getNome())
                        .append(" | ").append(u.getTipo())
                        .append("\n");
            }

            JTextArea area = new JTextArea(lista.toString());
            area.setEditable(false);

            JOptionPane.showMessageDialog(null, new JScrollPane(area), "Usuários Cadastrados", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BibliotecaUI();
    }
}