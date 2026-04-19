import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistemaloja";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection conectar() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
