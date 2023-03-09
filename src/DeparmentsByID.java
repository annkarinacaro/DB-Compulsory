import java.sql.*;

public class DeparmentsByID {

    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company";
    static final String USER = "root";
    static final String PASS = "root";

        public static void main(String[] args) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){


                // Get department information
                int departmentId = 5; // Deparment ID
                String departmentName = null;
                int mgrSSN;
                String mgrStartDate = null;
                String query = "SELECT * FROM department WHERE DNumber= ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, departmentId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    departmentName = rs.getString("DName");
                    departmentId = rs.getInt("DNumber");
                    mgrSSN = rs.getInt("MgrSSN");
                    mgrStartDate = rs.getString("MgrStartDate");
                } else {
                    System.out.println("Department not found");
                    return;
                }
                rs.close();
                stmt.close();

                // Get total employees in department
                int totalEmployees = 0;
                query = "SELECT COUNT(*) AS TotalEmployees FROM employee WHERE Dno = ?";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, departmentId);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    totalEmployees = rs.getInt("TotalEmployees");
                }
                rs.close();
                stmt.close();

                // Display results
                System.out.println("Department: " + departmentName);
                System.out.println("Department ID: " + departmentId);
                System.out.println("Department Manager: " + mgrSSN);
                System.out.println("Manager started Date: " + mgrStartDate);
                System.out.println("Total employees: " + totalEmployees);

                // Clean up
                conn.close();
            } catch (SQLException e) {
                System.err.println("SQL error: " + e.getMessage());
            }
        }
}
