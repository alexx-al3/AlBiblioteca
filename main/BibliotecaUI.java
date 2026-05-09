import javax.swing.*;
import java.awt.*;
import java.util.List;
import classe.Livro;
import classe.Usuario;

public class BibliotecaUI extends JFrame {

    public BibliotecaUI() {

        setTitle("AlBiblioteca");
        setSize(420, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(30, 80, 160));
        JLabel titulo = new JLabel("Sistema de Biblioteca");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        cabecalho.add(titulo);

        JPanel painelBotoes = new JPanel(new GridLayout(6, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));

        JButton btnLivro         = new JButton("Cadastrar Livro");
        JButton btnUsuario       = new JButton("Cadastrar Usuário");
        JButton btnEmprestar     = new JButton("Emprestar Livro");
        JButton btnDevolver      = new JButton("Devolver Livro");
        JButton btnListarLivros  = new JButton("Listar Livros");
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

    // -------------------------------------------------------------------------

    private void abrirCadastroLivro() {

        JTextField campoTitulo = new JTextField();
        JTextField campoAutor  = new JTextField();
        JTextField campoAno    = new JTextField();

        Object[] campos = {
            "Título:", campoTitulo,
            "Autor:",  campoAutor,
            "Ano:",    campoAno
        };

        int opcao = JOptionPane.showConfirmDialog(
            this, campos, "Cadastrar Livro", JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao != JOptionPane.OK_OPTION) return; // usuário cancelou

        try {
            Livro livro = LivroService.cadastrarLivro(
                campoTitulo.getText(),
                campoAutor.getText(),
                campoAno.getText()
            );

            JOptionPane.showMessageDialog(this,
                "Livro cadastrado com sucesso!\n\n" +
                "ID     : " + livro.getId()      + "\n" +
                "Título : " + livro.getTitulo()  + "\n" +
                "Autor  : " + livro.getAutor()   + "\n" +
                "Ano    : " + livro.getAno(),
                "Livro Cadastrado",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            mostrarErroValidacao(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro ao cadastrar livro", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------

    private void abrirCadastroUsuario() {

        JTextField campoNome = new JTextField();
        JTextField campoCpf  = new JTextField();
        String[] tipos       = {"aluno", "professor"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCpf,
            "Tipo:", comboTipo
        };

        int opcao = JOptionPane.showConfirmDialog(
            this, campos, "Cadastrar Usuário", JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao != JOptionPane.OK_OPTION) return;

        try {
            Usuario u = UsuarioService.cadastrarUsuario(
                campoNome.getText(),
                campoCpf.getText(),
                comboTipo.getSelectedItem().toString()
            );

            JOptionPane.showMessageDialog(this,
                "Usuário cadastrado com sucesso!\n\n" +
                "ID   : " + u.getId()    + "\n" +
                "Nome : " + u.getNome()  + "\n" +
                "CPF  : " + u.getCpf()   + "\n" +
                "Tipo : " + u.getTipo(),
                "Usuário Cadastrado",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            mostrarErroValidacao(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro ao cadastrar usuário", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------

    private void emprestarLivro() {

        JTextField campoLivroId   = new JTextField();
        JTextField campoUsuarioId = new JTextField();

        Object[] campos = {
            "ID do Livro:",    campoLivroId,
            "ID do Usuário:",  campoUsuarioId
        };

        int opcao = JOptionPane.showConfirmDialog(
            this, campos, "Emprestar Livro", JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao != JOptionPane.OK_OPTION) return;

        try {
            int livroId   = parseId(campoLivroId.getText(), "ID do Livro");
            int usuarioId = parseId(campoUsuarioId.getText(), "ID do Usuário");

            EmprestimoService.emprestarLivro(livroId, usuarioId);

            JOptionPane.showMessageDialog(this,
                "Empréstimo realizado com sucesso!",
                "Empréstimo", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            mostrarErroValidacao(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro ao realizar empréstimo", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------

    private void devolverLivro() {

        String entrada = JOptionPane.showInputDialog(this, "ID do Livro a devolver:");

        if (entrada == null || entrada.isBlank()) return; // cancelou

        try {
            int livroId = parseId(entrada, "ID do Livro");
            EmprestimoService.devolverLivro(livroId);

            JOptionPane.showMessageDialog(this,
                "Livro devolvido com sucesso!",
                "Devolução", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            mostrarErroValidacao(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro ao devolver livro", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------

    private void listarLivros() {
        try {
            List<Livro> livros = LivroService.listarLivros();

            if (livros.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum livro cadastrado.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Livro l : livros) {
                sb.append(l.getId())
                  .append(" | ").append(l.getTitulo())
                  .append(" | ").append(l.getAutor())
                  .append(" | ").append(l.getAno())
                  .append(" | ").append(l.isDisponivel() ? "Disponível" : "Emprestado")
                  .append("\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JOptionPane.showMessageDialog(this,
                new JScrollPane(area), "Livros Cadastrados (" + livros.size() + ")",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            mostrarErro("Erro ao listar livros", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------

    private void listarUsuarios() {
        try {
            List<Usuario> usuarios = UsuarioDAO.buscarTodos();

            if (usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum usuário cadastrado.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Usuario u : usuarios) {
                sb.append(u.getId())
                  .append(" | ").append(u.getNome())
                  .append(" | ").append(u.getCpf())
                  .append(" | ").append(u.getTipo())
                  .append("\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JOptionPane.showMessageDialog(this,
                new JScrollPane(area), "Usuários Cadastrados (" + usuarios.size() + ")",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            mostrarErro("Erro ao listar usuários", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Helpers

    /** Converte string para int com mensagem de erro clara. */
    private int parseId(String valor, String campo) {
        if (valor == null || valor.isBlank())
            throw new IllegalArgumentException(campo + " não pode estar vazio.");
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " inválido. Digite apenas números.");
        }
    }

    /** Diálogo para erro de validação (amarelo). */
    private void mostrarErroValidacao(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    /** Diálogo para erro técnico (vermelho). */
    private void mostrarErro(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(this, titulo + ":\n" + mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
