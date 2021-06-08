package br.com.fiap.gt.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.gt.dao.UserDao;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {

	private UserDao userdao = new UserDaoImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

//	@GET
//	public void execute() {
//		System.out.println("Execute API");
//	}

	@GET
	public Response index() {
		try {
			List<User> users = userdao.getList();
			return Response.status(Response.Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
