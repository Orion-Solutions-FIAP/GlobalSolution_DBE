package br.com.fiap.gt.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {
	
	 @GET 
	 public void execute() { 
		 System.out.println("Execute API"); 
	 }
}
