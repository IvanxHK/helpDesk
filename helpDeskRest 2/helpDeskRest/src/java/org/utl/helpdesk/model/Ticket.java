package org.utl.helpdesk.model;

public class Ticket {

	private int id;
	private String device;
	private String type;
	private String date;
	private String imageResName;
	private String timeOf;
	private String description;
	private Employee employee;
	private int status;

	public Ticket() {
	}

	public Ticket(int id, String device, String type, String date, String imageResId, String timeOf, String description, Employee employee, int status) {
		this.id = id;
		this.device = device;
		this.type = type;
		this.date = date;
		this.imageResName = imageResId;
		this.timeOf = timeOf;
		this.description = description;
		this.employee = employee;
		this.status = status;
	}

	public String getImageResName() {
		return imageResName;
	}

	public void setImageResName(String imageResName) {
		this.imageResName = imageResName;
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

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ticket{" + "id=" + id + ", device=" + device + ", type=" + type + ", date=" + date + ", timeOf=" + timeOf + ", description=" + description + ", employee=" + employee.toString() + '}';
	}
}
