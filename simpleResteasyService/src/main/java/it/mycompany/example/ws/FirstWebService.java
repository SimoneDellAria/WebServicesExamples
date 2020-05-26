package it.mycompany.example.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/example")
public class FirstWebService {
	
	/**
	 * This GET Service returns a welcome message
	 * 
	 * @return A simple text message
	 */
	@GET
	@Path("/hello")
	public String sayHello(){
		return "Hello From Your First RestEasy Service! \nCiao dal tuo primo servizio RestEasy!";
	}
	
	/**
	 * This GET Service returns a welcome message taking as input param the user text
	 * 
	 * @param name
	 * @return A simple text message including the name param
	 */
	@GET
	@Path("/hello/{name}")
	public String sayHelloWithName(@PathParam ("name") String name){
		return "Hello " + name + " From Your First RestEasy Service! \n Ciao " + name + " dal tuo primo servizio RestEasy!";
	}

}
