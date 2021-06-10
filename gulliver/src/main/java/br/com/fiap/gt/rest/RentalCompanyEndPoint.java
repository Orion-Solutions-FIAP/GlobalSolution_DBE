package br.com.fiap.gt.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/rentalCompanies")
@Produces(MediaType.APPLICATION_JSON)
public class RentalCompanyEndPoint {

	private RentalCompanyDao rentalCompanyDao = new RentalCompanyDaoImpl(
			EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<RentalCompany> rentalCompanies = rentalCompanyDao.getList();
			return Response.status(Response.Status.OK).entity(rentalCompanies).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			RentalCompany rentalCompany = rentalCompanyDao.search(id);
			return Response.status(Response.Status.OK).entity(rentalCompany).build();

		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, RentalCompany rentalCompany) {

		if (id == null || rentalCompany == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			RentalCompany rentalCompanyBase = rentalCompanyDao.search(id);
			rentalCompany.setCars(rentalCompanyBase.getCars());
			rentalCompany.setRatings(rentalCompanyBase.getRatings());
			rentalCompanyDao.update(rentalCompany);
			rentalCompanyDao.commit();
			return Response.status(Response.Status.OK).entity(rentalCompany).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(RentalCompany rentalCompany) {
		if (rentalCompany == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			rentalCompanyDao.create(rentalCompany);
			rentalCompanyDao.commit();
			return Response.status(Response.Status.CREATED).entity(rentalCompany).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			rentalCompanyDao.delete(id);
			rentalCompanyDao.commit();
			return Response.status(Response.Status.OK).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
