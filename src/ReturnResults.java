import java.sql.Connection;

public class ReturnResults {


    public static void main(String[] args) {
        Connection connection = null;
        DBconnection dBconnection;
        try {
            //this is where you call your method object...
            dBconnection = new DBconnection();
            //once created, you call the method to get the connection...
            connection = dBconnection.connect();
            //after get the connection, keep with the method logic...
            if (connection != null) {
                //your logic code...
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}