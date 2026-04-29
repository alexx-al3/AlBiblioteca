public class TesteConexao {
    public static void main(String[] args) {
        try {
            ConnectionFactory.getConnection();
            System.out.println("Conectou com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}