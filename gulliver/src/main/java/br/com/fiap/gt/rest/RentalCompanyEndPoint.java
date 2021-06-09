package br.com.fiap.gt.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
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
	public Response findById(@PathParam("id") int id) {
		RentalCompany rentalCompany = rentalCompanyDao.search(id);
		if(rentalCompany == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(rentalCompany).build();
	}

}
