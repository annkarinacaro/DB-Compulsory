import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "INSERT INTO department (DName, DNumber, MgrSSN, MgrStartDate) VALUES ('test', '89', '123456789', '1986-01-01 00:00:00'\n)";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
