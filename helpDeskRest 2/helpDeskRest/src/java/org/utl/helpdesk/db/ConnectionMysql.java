package org.utl.helpdesk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jean_
 */
public class ConnectionMysql {
    Connection connection;
    
    
    public Connection open() throws ClassNotFoundException, SQLException {
        //Establish the driver which we are gonna work
        String driver = "com.mysql.cj.jdbc.Driver";
        //Establish the connection route
        String url = "jdbc:mysql://127.0.0.1:3306/help_desk";
        //Establish the data access
        String user = "root";
        String password = "root";
        
        //Come up with the data driver
        Class.forName(driver);
        
        //Open the connection
        connection = DriverManager.getConnection(url, user, password);
        //Return the open and created connection
        return connection;
    }  
    
    /**
     * This method is for closing the connection MySQL that is open
     */
    
    public void close(){
        try{
            if(connection != null){
            connection.close();
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
