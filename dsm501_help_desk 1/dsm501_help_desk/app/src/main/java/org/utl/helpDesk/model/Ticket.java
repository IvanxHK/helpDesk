package org.utl.helpDesk.model;

import com.google.gson.annotations.SerializedName;

public class Ticket {
    //    @SerializedName("id")
    private int id;
    //    @SerializedName("device")
    private String device;
    //    @SerializedName("type")
    private String type;
    //    @SerializedName("date")
    private String date;
    private String imageResName;
    //    @SerializedName("timeOf")
    private String timeOf;
    //    @SerializedName("description")
    private String description;
    //    @SerializedName("employee")
    private Employee employee;
    private int status;

    public Ticket() {
    }

    public Ticket(int id, String device, String type, String date, String imageResName, String timeOf, String description, Employee employee, int status) {
        this.id = id;
        this.device = device;
        this.type = type;
        this.date = date;
        this.imageResName = imageResName;
        this.timeOf = timeOf;
        this.description = description;
        this.employee = employee;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getImageResName() {
        return imageResName;
    }

    public void setImageResName(String imageResName) {
        this.imageResName = imageResName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeOf() {
        return timeOf;
    }

    public void setTimeOf(String timeOf) {
        this.timeOf = timeOf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getStatus() {
        return status;
    }

    public String getStringStatus(){
        switch (status){
            case 0:
                return "Cancelado";
            case 1:
                return "Aceptado";
            case 2:
                return "Atendido";
            default:
                return "Desconocido";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", device='" + device + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", imageResName='" + imageResName + '\'' +
                ", timeOf='" + timeOf + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                ", status=" + status +
                '}';
    }
}
