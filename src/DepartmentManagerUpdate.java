import java.sql.*;

public class DepartmentManagerUpdate {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company";
    static final String USER = "root";
    static final String PASS = "root";

    private static boolean employeeExists(Connection conn, String superSsn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM employee WHERE SuperSSN = ?");
        pstmt.setString(1, superSsn);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }




    public static void updateDepartmentManager(int dNumber, int mgrSSN, Connection conn) throws SQLException {

        // Check if the employee being updated is already a manager
        PreparedStatement pstmtSelect = conn.prepareStatement("SELECT COUNT(*) FROM department WHERE MgrSSN = ?");
        pstmtSelect.setInt(1, mgrSSN);
        ResultSet rs = pstmtSelect.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Can't be updated, employee is already a manager.");
            return;
        }

        // Prepare the update statement
        PreparedStatement pstmt = conn.prepareStatement("UPDATE department SET MgrSSN = ?, MgrStartDate = NOW() WHERE DNumber = ?");
        pstmt.setInt(1, mgrSSN);
        pstmt.setInt(2, dNumber);

        // Execute the update statement
        int updateCount = pstmt.executeUpdate();

        // Check if the update was successful
        if (updateCount == 0) {
            throw new SQLException("Department not found.");
        } else if (updateCount > 1) {
            throw new SQLException("Multiple departments updated.");
        }
        System.out.println("Department manager updated successfully.");
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {


            int dNumber = 10; // the DNumber of the department to update
            int mgrSSN = 987987987; // the new value for the MgrSSN column
            String superSsn = "987654321"; // the SuperSSN of the employee who will become the new manager

            if (!employeeExists(conn, superSsn)) {
                System.out.println("Employee with SuperSSN " + superSsn + " doesn't exist");
                return;
            }



            updateDepartmentManager(dNumber, mgrSSN, conn);
        } catch (SQLException ex) {
            System.err.println("Error updating department manager: " + ex.getMessage());
        }
    }
}
