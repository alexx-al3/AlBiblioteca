import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:5432/postgres";

    private static final String USER = "postgres.wozwfhzjaafudukrlkln";
    private static final String PASSWORD = "bancodedadosalex";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar:");
            e.printStackTrace();
            return null;
        }
    }
}