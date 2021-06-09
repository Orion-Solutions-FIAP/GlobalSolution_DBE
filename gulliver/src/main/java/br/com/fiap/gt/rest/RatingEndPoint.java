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

import br.com.fiap.gt.dao.RatingDao;
import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.UserDao;
import br.com.fiap.gt.dao.impl.RatingDaoImpl;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RatingPK;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/rating")
@Produces(MediaType.APPLICATION_JSON)
public class RatingEndPoint {

	private RatingDao ratingDao = new RatingDaoImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<Rating> ratings = ratingDao.getList();
			return Response.status(Response.Status.OK).entity(ratings).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("rentalcompany/{rentalCompanyId}")
	public Response findByRentalCompany(@PathParam("rentalCompanyId") Integer id) {

		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		List<Rating> ratings = ratingDao.findByRentalCompanyId(id);
		if (ratings == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(ratings).build();
	}

	@GET
	@Path("{id}/{rentalCompanyId}/{userId}")
	public Response findById(@PathParam("id") Integer id, @PathParam("rentalCompanyId") Integer rentalCompanyId,
			@PathParam("userId") Integer userId) {

		if (id == null || rentalCompanyId == null || userId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Rating rating = ratingDao.search(new RatingPK(id, rentalCompanyId, userId));
		if (rating == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(rating).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Rating rating) {

		if (id == null || rating == null || rating.getRentalCompany() == null || rating.getUser() == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			Rating ratingBase = ratingDao
					.search(new RatingPK(id, rating.getRentalCompany().getId(), rating.getUser().getId()));
			if (ratingBase == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			rating.setRentalCompany(ratingBase.getRentalCompany());
			rating.setUser(ratingBase.getUser());
			ratingDao.update(rating);
			ratingDao.commit();
			return Response.status(Response.Status.OK).entity(rating).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Rating rating) {
		if (rating == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			rating = ratingDao.createOrUpdate(rating);
			ratingDao.commit();
			return Response.status(Response.Status.CREATED).entity(rating).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{id}/{rentalCompanyId}/{userId}")
	public Response delete(@PathParam("id") Integer id, @PathParam("rentalCompanyId") Integer rentalCompanyId,
			@PathParam("userId") Integer userId) {

		if (id == null || rentalCompanyId == null || userId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		RatingPK ratingPk = new RatingPK(id, rentalCompanyId, userId);
		try {
			if (ratingDao.search(ratingPk) == null)
				return Response.status(Response.Status.NOT_FOUND).build();
			ratingDao.delete(ratingPk);
			ratingDao.commit();
			return Response.status(Response.Status.OK).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
