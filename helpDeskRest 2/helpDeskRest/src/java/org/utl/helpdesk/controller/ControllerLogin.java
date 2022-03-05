package org.utl.helpdesk.controller;

import org.utl.helpdesk.model.User;
import org.utl.helpdesk.db.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.utl.helpdesk.model.Employee;

/**
 *
 * @author Jean_
 */
public class ControllerLogin {

    public User login(String username, String password) throws ClassNotFoundException, SQLException  {
        //Define the consult that is gonna be executed
        String query = "select * from users inner join employee on users.id_employee = employee.id_employee where user_name=? and password=?;";
        //Generate the connection object
        ConnectionMysql connMySQL = new ConnectionMysql();
        //Open connection
        Connection conn = connMySQL.open();
        //Object to execute the consult
        PreparedStatement pstmt = conn.prepareStatement(query);
        //Fill data parameters
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        //Object to receive data
        ResultSet rs = pstmt.executeQuery();
        //User type object
        User u = null;
        if (rs.next()) {
			u = fill(rs);
        }
        //close objecs to connect to the db
        rs.close();
        pstmt.close();
        connMySQL.close();
        //return object type user
        return u;
    }

    private User fill(ResultSet rs) throws SQLException {
        //Temp variable to create a new Employee object
        Employee e = new Employee();

        //Temp variable to create a new Employee object
        User u = new User();

        //Object type User
        u.setUserName(rs.getString("user_name"));
        u.setId(rs.getInt("id_user"));
        u.setPassword(rs.getString("password"));

        //Establish its personal data
        e.setId(rs.getInt("id_employee"));
        e.setName(rs.getString("name"));
        e.setLastName(rs.getString("last_name"));
        e.setEmail(rs.getString("email"));
        e.setPhoneNumber(rs.getString("phone_number"));
        e.setDepartment(rs.getString("department"));

        u.setEmployee(e);

        return u;
    }
}
