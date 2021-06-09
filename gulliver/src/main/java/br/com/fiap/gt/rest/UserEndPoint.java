package br.com.fiap.gt.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.gt.dao.UserDao;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {

	private UserDao userdao = new UserDaoImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<User> users = userdao.getList();
			return Response.status(Response.Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") int id) {
		try {
			User user = userdao.search(id);
			return Response.status(Response.Status.OK).entity(user).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, User user) {

		if (id == null || user == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			user = userdao.search(id);
			userdao.update(user);
			userdao.commit();
			return Response.status(Response.Status.OK).entity(user).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
