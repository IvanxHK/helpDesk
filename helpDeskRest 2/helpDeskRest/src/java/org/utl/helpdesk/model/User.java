package org.utl.helpdesk.model;

/**
 *
 * @author Jean_
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private Employee employee;

    public User() {
    }

    public User(int id, String userName, String password, Employee employee) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", employee=" + employee.toString() + '}';
    }
}
