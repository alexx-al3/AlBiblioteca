import classe.Livro;
import classe.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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

                case 1:
                    System.out.print("Título: ");
                    String t = sc.nextLine();

                    System.out.print("Autor: ");
                    String a = sc.nextLine();

                    System.out.print("Ano: ");
                    int ano = sc.nextInt();

                    LivroDAO.inserir(new Livro(t, a, ano));
                    break;

                case 2:
                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    UsuarioDAO.inserir(new Usuario(id, "Nome", "aluno"));
                    break;

                case 3:
                    System.out.print("Livro ID: ");
                    String l = sc.nextLine();

                    System.out.print("Usuário ID: ");
                    String u = sc.nextLine();

                    EmprestimoDAO.emprestar(l, u);
                    break;

                case 4:
                    System.out.print("Livro ID: ");
                    String d = sc.nextLine();

                    EmprestimoDAO.devolver(d);
                    break;

                case 5:
                    LivroDAO.listar();
                    break;
            }

        } while (op != 6);

        sc.close();
    }
}