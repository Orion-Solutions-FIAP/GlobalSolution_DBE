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

import br.com.fiap.gt.dao.CarDao;
import br.com.fiap.gt.dao.impl.CarDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
public class CarEndPoint {

	private CarDao carDao = new CarDaoImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<Car> cars = carDao.getList();
			return Response.status(Response.Status.OK).entity(cars).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("rentalcompany/{rentalCompanyId}")
	public Response findByRentalCompany(@PathParam("rentalCompanyId") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		List<Car> cars = carDao.findByRentalCompanyId(id);
		if (cars == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(cars).build();
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			Car car = carDao.search(id);
			return Response.status(Response.Status.OK).entity(car).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Car car) {

		if (id == null || car == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			Car carBase = carDao.search(id);
			car.setRentalCompany(carBase.getRentalCompany());
			carDao.update(car);
			carDao.commit();
			return Response.status(Response.Status.OK).entity(car).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Car car) {
		if (car == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			carDao.create(car);
			carDao.commit();
			return Response.status(Response.Status.CREATED).entity(car).build();
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
			carDao.delete(id);
			carDao.commit();
			return Response.status(Response.Status.OK).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
