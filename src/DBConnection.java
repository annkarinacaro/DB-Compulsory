import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBconnection{
        //changing the method declaration to return a Connection
        public Connection connect() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/anapatrick_1_company", "root","root");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return null;
            }
            if (connection != null) {
                System.out.println("Connection working");
            } else {
                System.out.println("Failed to make connection!");
            }
            return connection;
        }
    }
