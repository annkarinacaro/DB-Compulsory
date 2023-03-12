import java.sql.*;

public class CreateDepartment {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/anapatrick_1_company";
        String user = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            CallableStatement stmt = conn.prepareCall("{? = CALL usp_CreateDepartment( ?, ?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, "differentone");
            stmt.setString(3, "688888889");
            stmt.execute();
            int deptNumber = stmt.getInt(1);
            System.out.println("Department created with DNumber: " + deptNumber);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
