package CS_564.Metabolites;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class demonstrates one way to interact with MySQL using Java. I have 
 * demonstrated another way in the SimpleEntity.java file as well. I think this 
 * way is easier to understand. But the other way is pretty cool and useful
 * once you figure out how it works. 
 * 
 * This file can be run  like a normal Java program to see its functionality 
 * 
 * NOTE: You need to change the line:
 * Connection conn = DriverManager.getConnection(myUrl, "mysqluser", "password");
 * Instead of "mysqluser" and "password", you need to enter the credentials for
 * a user you have created that has access to the MySQL sever on your computer
 * 
 * ANOTHER NOTE: In order for this file to run properly you need to create 
 * a database named "test_database" and a table named test_table with two columns 
 * "id" and "text"
 * 
 * The SQL command look like this: 
 * CREATE DATABASE test_database;
 * CREATE TABLE test_table(id int, text varchar(100))
 * 
 * 
 */

public class MySQLExample {

    public static void main(String[] args) {
       
        try {
            //The driver enables java to interact with MySQL
            String myDriver = "com.mysql.cj.jdbc.Driver";
            
            //URL of the MySQL server where the database is stored 
            String myUrl = "jdbc:mysql://localhost/test_database";
            
            //Establish the connection with MySQL server need to login with a
            //user that you have created for your local database
            //YOU WILL NEED TO CHANGE 
            Connection conn = DriverManager.getConnection(myUrl, "mysqluser", "password");
            
            System.out.println("Connection Successful");
            
            //This creates a Statement Object that can be used to send SQL
            //statements to the database
            Statement stmt = conn.createStatement();
            
            //Defining a SQL Query to send some data to the database 
            String query = "INSERT INTO test_table" + " VALUES (20000, 'Sent from java')";
            
            //This actual executes the query and updates the database 
            stmt.executeUpdate(query);
            
            System.out.println("Updated Table!!!");
            
            //Data Type to stored information retrieved from MySQL
            ResultSet rs;
            
            //Retrieve all rows in "test_table"
            rs = stmt.executeQuery("SELECT * FROM test_table");
            
            //Iterating through the rows of the table and printing to screen 
            while(rs.next()) {
                
                System.out.println( "ID: " + rs.getInt("id") +" Text: " + rs.getString("text") );
                
            }
           
        
        }catch(Exception e) {
            
            System.out.println(e);
            
        }
        
       
            
    } 
}
