import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n=== BIBLIOTECA ===");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Cadastrar Usuário");
            System.out.println("3 - Emprestar Livro");
            System.out.println("4 - Devolver Livro");
            System.out.println("5 - Listar Livros");
            System.out.println("6 - Listar Usuários");
            System.out.println("7 - Sair");
            System.out.print("Opção: ");

            op = lerInt();

            switch (op) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> emprestarLivro();
                case 4 -> devolverLivro();
                case 5 -> LivroService.listarLivros().forEach(System.out::println);
                case 6 -> UsuarioDAO.buscarTodos().forEach(System.out::println);
                case 7 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 7);
    }

    private static void cadastrarLivro() {
        try {
            System.out.print("Título: ");
            String t = sc.nextLine();

            System.out.print("Autor: ");
            String a = sc.nextLine();

            System.out.print("Ano: ");
            String ano = sc.nextLine();

            var livro = LivroService.cadastrarLivro(t, a, ano);
            System.out.println("Livro cadastrado com ID " + livro.getId() + ".");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cadastrarUsuario() {
        try {
            System.out.print("Nome: ");
            String n = sc.nextLine();

            System.out.print("CPF (somente números): ");
            String cpf = sc.nextLine();

            System.out.print("Tipo (aluno/professor): ");
            String t = sc.nextLine();

            var u = UsuarioService.cadastrarUsuario(n, cpf, t);
            System.out.println("Usuário cadastrado com ID " + u.getId() + ".");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void emprestarLivro() {
        try {
            System.out.print("ID do Livro: ");
            int l = lerInt();

            System.out.print("ID do Usuário: ");
            int u = lerInt();

            EmprestimoService.emprestarLivro(l, u);
            System.out.println("Empréstimo realizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void devolverLivro() {
        try {
            System.out.print("ID do Livro: ");
            int l = lerInt();

            EmprestimoService.devolverLivro(l);
            System.out.println("Livro devolvido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /** Lê um inteiro e descarta o restante da linha. */
    private static int lerInt() {
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                return v;
            } catch (NumberFormatException e) {
                System.out.print("Digite apenas números: ");
            }
        }
    }
}
