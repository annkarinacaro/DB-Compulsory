import java.sql.*;
import java.util.Scanner;

public class DepartmentNameUpdate {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "slwwf8lqhl";

    //forgot to add try and catch here later hopefully but works for now
    private static void usp_UpdateDepartmentName(Connection conn, int departmentNumber, String departmentName) throws SQLException{
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

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            Scanner myScanner = new Scanner(System.in);
            System.out.println("RENAME DEPARTMENT");
            System.out.println("Insert department ID: ");
            int departmentNum = myScanner.nextInt();
            System.out.println("Insert new name: " );
            String departmentName= myScanner.nextLine();




            usp_UpdateDepartmentName(conn, departmentNum, departmentName);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}


}
