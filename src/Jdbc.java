import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    private static final String URL = "jdbc:mysql://localhost:3306/CompanyManager";
    private static final String USER = "root";
    private static final String PASSWORD = "Poukiem34*";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}