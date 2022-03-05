package org.utl.helpdesk.rest;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.utl.helpdesk.controller.ControllerLogin;
import org.utl.helpdesk.model.User;

/**
 *
 * @author Jean_
 */
@Path("login")
public class LoginRest {

	@Path("login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("userName") @DefaultValue("") String u, @FormParam("password") @DefaultValue("") String p) {
		String out;
		ControllerLogin cl = new ControllerLogin();
		Gson gs = new Gson();
		try {
			User user = cl.login(u, p);
			out = gs.toJson(user);
			return Response.status(Response.Status.OK).entity(out).build();
		} catch (ClassNotFoundException  | SQLException e) {
			out = String.format("{\"error\": \"There was an error %s\"}", e.toString());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
		}
	}

}
