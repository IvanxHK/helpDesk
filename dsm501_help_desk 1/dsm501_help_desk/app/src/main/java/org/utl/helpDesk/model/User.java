package org.utl.helpDesk.model;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("employee")
    private Employee employee;

    public User() {
    }

    public User(Integer id, String userName, String password, Employee employee) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
