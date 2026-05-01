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
            System.out.println("6 - Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> emprestarLivro();
                case 4 -> devolverLivro();
                case 5 -> LivroService.listarLivros().forEach(System.out::println);
                case 6 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 6);
    }

    private static void cadastrarLivro() {

        System.out.print("Título: ");
        String t = sc.nextLine();

        System.out.print("Autor: ");
        String a = sc.nextLine();

        System.out.print("Ano: ");
        int ano = sc.nextInt();
        sc.nextLine();

        LivroService.cadastrarLivro(t, a, ano);
    }

    private static void cadastrarUsuario() {

        System.out.print("Nome: ");
        String n = sc.nextLine();

        System.out.print("Tipo: ");
        String t = sc.nextLine();

        UsuarioService.cadastrarUsuario(n, t);
    }

   private static void emprestarLivro() {

    System.out.print("Livro ID: ");
    int l = sc.nextInt();
    sc.nextLine();

    System.out.print("Usuário ID: ");
    int u = sc.nextInt();  // era String u = sc.nextLine();
    sc.nextLine();

    EmprestimoService.emprestarLivro(l, u);
}

    private static void devolverLivro() {

        System.out.print("Livro ID: ");
        int l = sc.nextInt();
        sc.nextLine();

        EmprestimoService.devolverLivro(l);
    }
}