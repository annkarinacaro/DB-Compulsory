import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DepartmentDelete {
    static final String DB_URL = "jdbc:mysql://localhost:3306/anapatrick_1_company?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "root";


    private static void usp_DeleteDepartment(Connection conn, int departmentNumber) throws SQLException{
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM project WHERE DNum=?");
            pstmt.setInt(1, departmentNumber);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Project deleted already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE employee SET Dno=NULL WHERE Dno=?"); //this should cascade into works_on if we add the right constraint
            pstmt.setInt(1, departmentNumber);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Employee and hopefully works_on deleted already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM department WHERE DNumber=?");
            pstmt.setInt(1, departmentNumber);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Dep deleted already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM dept_locations WHERE DNumber=?");
            pstmt.setInt(1, departmentNumber);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Dep loc deleted already");
        }
        System.out.println("deleted the fucc");
    }

    //purely for checking if it deletes properly
    private static void generateToDelete(Connection conn) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department (DNumber, DName, MgrSSN, MgrStartDate) VALUES (10, N'getme', 453453453, NOW())");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Dep exists already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO dept_locations (DNUmber, DLocation) VALUES (10, N'delete')");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Dep loc exists already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO project (PName, PNumber, PLocation, DNum) VALUES (N'delete', 69, N'delete', 10)");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Project exists already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee (FName, Minit, LName, SSN, BDate, Address, Sex, Salary, SuperSSN, Dno) VALUES (N'getme', N'B', N'me', 1984, N'1955-01-09', N'731 Fondren, Houston, TX', N'M', 30000.00, 453453453, 10)");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Employee exists already");
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO works_on (Essn, Pno, Hours) VALUES (1984, 69, 32)");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Works_on exists already");
        }
        System.out.println("Created shit to delete");
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            Scanner myScanner = new Scanner(System.in);
            System.out.println("DELETE DEPARMENT");
            System.out.println("Department number: ");
            int departmentNumber = myScanner.nextInt();

            usp_DeleteDepartment(conn, departmentNumber);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
