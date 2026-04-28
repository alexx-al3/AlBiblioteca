import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {
            exibirMenu();
            op = lerInt();

            switch (op) {

                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> emprestarLivro();
                case 4 -> devolverLivro();
                case 5 -> LivroDAO.listar();
                case 6 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 6);

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== BIBLIOTECA ===");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Emprestar Livro");
        System.out.println("4 - Devolver Livro");
        System.out.println("5 - Listar Livros");
        System.out.println("6 - Sair");
    }

    private static int lerInt() {
        int valor = sc.nextInt();
        sc.nextLine(); // limpar buffer
        return valor;
    }

    private static void cadastrarLivro() {

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        System.out.print("Ano: ");
        int ano = lerInt();

        LivroService.cadastrarLivro(titulo, autor, ano);
    }

    private static void cadastrarUsuario() {

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Tipo (aluno/professor): ");
        String tipo = sc.nextLine();

        UsuarioService.cadastrarUsuario(id, nome, tipo);
    }

    private static void emprestarLivro() {

        System.out.print("Livro ID: ");
        String livroId = sc.nextLine();

        System.out.print("Usuário ID: ");
        String usuarioId = sc.nextLine();

        EmprestimoService.emprestarLivro(livroId, usuarioId);
    }

    private static void devolverLivro() {

        System.out.print("Livro ID: ");
        String livroId = sc.nextLine();

        EmprestimoService.devolverLivro(livroId);
    }
}