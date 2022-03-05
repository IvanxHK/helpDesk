package org.utl.helpdesk.rest;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.utl.helpdesk.controller.ControllerTicket;
import org.utl.helpdesk.model.Ticket;


@Path("ticket")
public class TicketRest {

	@Path("insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(@FormParam("ticket") String t) throws Exception {
		String out = "";

		try {
			Gson gson = new Gson();
			Ticket ticket = gson.fromJson(t, Ticket.class);
			ControllerTicket ct = new ControllerTicket();
			int id = ct.insert(ticket);
			out = String.format("{\"idGenerado\": \"%d\"}", id);
		} catch (Exception ex) {
			ex.printStackTrace();

			out = String.format("{\"error\": \"Hubo un error en la inserción, vuelve a intentarlo o llama al administrador del sistema %s\"}", ex.toString());
		}

		return Response.status(Response.Status.OK).entity(out).build();
	}

	@Path("getAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@QueryParam("employeeId") int employeeId, @QueryParam("status") int status) {
		String out = "";
		try {
			Gson gson = new Gson();
			ControllerTicket ct = new ControllerTicket();
			out = gson.toJson(ct.getAll(employeeId, status));
			return Response.status(Response.Status.OK).entity(out).build();
		} catch (ClassNotFoundException | SQLException ex) {
			out = String.format("{\"error\": \"Hubo un error en la inserción, vuelve a intentarlo o llama al administrador del sistema %s\"}", ex.toString());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
		}

	}

	@Path("delete")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("id") int id) {
		String out;
		try {
			ControllerTicket ct = new ControllerTicket();
			ct.delete(id);
			out = "{\"result\": \"Eliminación exitosa\"}";
			return Response.status(Response.Status.OK).entity(out).build();
		} catch (Exception ex) {
			ex.printStackTrace();

			out = String.format("{\"error\": \"Hubo un error en la eliminación, vuelve a inentarlo o llama al administrador del sistema %s\"}", ex.toString());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
		}

	}

}