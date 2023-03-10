import java.sql.*;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Connection connection = null;
        DBconnection dBconnection;
        //this is where you call your method object...
        dBconnection = new DBconnection();
        //once created, you call the method to get the connection...
        connection = dBconnection.connect();

        //after get the connection, keep with the method logic...
        if (connection != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Which method do you want to call? (A/B/C/D)");
            System.out.println("Choose A: CREATE DEPARTMENT\nChoose B: UPDATE MANAGER\nChoose C: CALL DEPARTMENT BY ID\nChoose D: TO ADD A NEW COLUMN"
            + "\nChoose E: TO DELETE A DEPARMENT\nChoose F: TO UPDATE THE DEPARTMENT NAME");
            String choice = scanner.next();

            switch (choice) {
                case "A":
                    CreateTable.main(args);
                    break;
                case "B":
                    DepartmentManagerUpdate.main(args);
                    break;
                case "C":
                    DeparmentsByID.main(args);
                    break;
                case "D":
                    AddColumn.main(args);
                    break;
                case "E":
                    DepartmentDelete.main(args);
                    break;
                case "F":
                    DepartmentNameUpdate.main(args); //forgot error handling
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

