import java.sql.*;
import java.util.Scanner;
public class AddColumn {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company";
    static final String USER = "root";
    static final String PASS = "root";

    public static void addNewColumn(String column,String type,  Connection conn) throws SQLException {
        // Prepare the update statement
        String sql = "ALTER TABLE department ADD COLUMN " + column + " " + type;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("New column added to department table");
    }


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            Scanner myScanner = new Scanner(System.in);
            System.out.println("ADD NEW COLUMN");
            System.out.println("Insert column name: " );              // Read user input
            String column = myScanner.nextLine();
            System.out.println("Insert column type: " );              // Read user input
            String type = myScanner.nextLine();


          /*  if (!employeeExists(conn, superSsn)) {
                System.out.println("Employee with SuperSSN " + superSsn + " doesn't exist");
                return;
            }*/


            addNewColumn(column,type, conn);
        } catch (SQLException ex) {
            System.err.println("Error adding the new column: " + ex.getMessage());
        }
    }




}