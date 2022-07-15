package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */

//In Services Tab, right-click on Databases and click: Register MySQL Server...
//Right-click on MySQL Server and click: Create database...
//Create tables
//Project Properties -> Include Library -> MySQL JDBC Driver
public class PhoneBook {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        Connection conn=null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/contacts?" +
                                           "user=root&password="); //password excluded...
            st = conn.createStatement();
            String query = "INSERT INTO People(firstName,lastName,telephone,email) VALUES('Kelly','Test','111-222-3333','kt548710@test.com')";
            st.executeUpdate(query);

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        //inserting new contacts
        //deleting contacts
    }
}
