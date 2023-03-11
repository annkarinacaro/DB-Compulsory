import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentNameUpdate {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "slwwf8lqhl";

    public static void usp_UpdateDepartmentName(Connection conn, int departmentNumber, String departmentName) throws SQLException{
        // Check for DName dupes
        PreparedStatement pstmtSelect = conn.prepareStatement("SELECT COUNT(*) FROM department WHERE DName = ?");
        pstmtSelect.setString(1, departmentName);
        ResultSet rs = pstmtSelect.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Can't be updated, DName already in use");
            return;
        }

        PreparedStatement pstmt = conn.prepareStatement("UPDATE department SET DName=? WHERE DNumber=?");
        pstmt.setString(1, departmentName);
        pstmt.setInt(2, departmentNumber);
        pstmt.executeUpdate();
        System.out.println("mayhaps?");
    }




}
