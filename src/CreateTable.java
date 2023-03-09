import java.sql.*;

public class CreateTable {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company";
    static final String USER = "root";
    static final String PASS = "root";



    private static boolean departmentExists(Connection conn, String departmentName) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM department WHERE DName = ?");
        pstmt.setString(1, departmentName);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }

    private static boolean employeeExists(Connection conn, String ssn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM employee WHERE SSN = ?");
        pstmt.setString(1, ssn);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }

    private static void createDepartment(Connection conn, String departmentName, String managerSSN) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department ( DName, MgrSSN, MgrStartDate) VALUES ( ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, departmentName);
        pstmt.setString(2, managerSSN);
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int DNumber = rs.getInt(1);
            System.out.println("Created department " + departmentName + " with ID " + DNumber);
        }

    }



    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            String departmentName = "testooo00";
            String ssn = "453453453";

            if (departmentExists(conn, departmentName)) {
                System.out.println("Department already exists");
                return;
            }

            if (!employeeExists(conn, ssn)) {
                System.out.println("Employee doesn't exist");
                return;
            }

            createDepartment(conn, departmentName, ssn);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
