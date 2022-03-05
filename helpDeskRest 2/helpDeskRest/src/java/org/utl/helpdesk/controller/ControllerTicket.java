package org.utl.helpdesk.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.utl.helpdesk.db.ConnectionMysql;
import org.utl.helpdesk.model.Employee;
import org.utl.helpdesk.model.Ticket;

public class ControllerTicket {

	public int insert(Ticket t) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO ticket (id_employee, device, image_res_name, type, date_of, time_of, description_of, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

		int idTicketGenerated = -1;

		ConnectionMysql connMySQL = new ConnectionMysql();

		Connection conn = connMySQL.open();

		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, t.getEmployee().getId());
		pstmt.setString(2, t.getDevice());
		pstmt.setString(3, t.getImageResName());
		pstmt.setString(4, t.getType());
		pstmt.setString(5, t.getDate());
		pstmt.setString(6, t.getTimeOf());
		pstmt.setString(7, t.getDescription());
		pstmt.setInt(8, t.getStatus());

		ResultSet rs = null;

		pstmt.executeUpdate();

		rs = pstmt.getGeneratedKeys();

		if (rs.next()) {
			idTicketGenerated = rs.getInt(1);
			t.setId(idTicketGenerated);
		}

		rs.close();
		pstmt.close();
		connMySQL.close();

		return idTicketGenerated;
	}

	public List<Ticket> getAll(int employeeId, int status) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM ticket WHERE id_employee = ? and status = ?";

		List<Ticket> tickets = new ArrayList<>();

		ConnectionMysql connection = new ConnectionMysql();
		Connection mysql = connection.open();

		PreparedStatement pstmt = mysql.prepareStatement(query);
		pstmt.setInt(1, employeeId);
		pstmt.setInt(2, status);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			tickets.add(fill(rs));
		}

		rs.close();
		pstmt.close();
		connection.close();

		return tickets;
	}

	private Ticket fill(ResultSet rs) throws SQLException {
		Ticket ticket = new Ticket();
		Employee e = new Employee();

		e.setId(rs.getInt("id_employee"));
		ticket.setId(rs.getInt("id_ticket"));
		ticket.setDevice(rs.getString("device"));
		ticket.setType(rs.getString("type"));
		ticket.setDate(rs.getString("date_of"));
		ticket.setImageResName(rs.getString("image_res_name"));
		ticket.setTimeOf(rs.getString("time_of"));
		ticket.setDescription(rs.getString("description_of"));
		ticket.setStatus(rs.getInt("status"));
		ticket.setEmployee(e);

		System.out.println(ticket.toString()
		);
		return ticket;
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String query = "UPDATE ticket SET status = 0 WHERE id_ticket = ?";
		ConnectionMysql connectionMysql = new ConnectionMysql();
		Connection mysql = connectionMysql.open();
		PreparedStatement pstmt = mysql.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		pstmt.close();
		connectionMysql.close();
	}

}

