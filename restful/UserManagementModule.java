package com.howtodoinjava.restful;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/d")
public class UserManagementModule
{
	@GET
	@Path("/u")
	@RolesAllowed("ADMIN")
	public Response getAllUsers()
	{
		String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
		return Response.status(200).entity(result).build();
	}
}
