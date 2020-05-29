package it.mycompany.simpleJerseyRestService.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/welcome")
public class FirstService {
	
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello from your first Rest Service! \nUn saluto dal tuo primo servizio Rest!";
	}
	
	@GET
	@Path("/speak/{user-text}")
	@Produces(MediaType.TEXT_PLAIN)
	public String saySomething(@PathParam("user-text") String text ) {
		return "Hi! This is your input: " + text + "\nCiao! Questo e' il tuo input: " + text;
	}

}
