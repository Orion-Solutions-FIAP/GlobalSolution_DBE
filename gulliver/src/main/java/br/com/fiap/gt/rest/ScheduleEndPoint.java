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
import br.com.fiap.gt.dao.ScheduleDao;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.dao.impl.ScheduleDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.Schedule;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleEndPoint {

	private ScheduleDao scheduleDao = new ScheduleDaoImpl(
			EntityManagerFactorySingleton.getInstance().createEntityManager());
	private RentalCompanyDao rentalCompanyDao = new RentalCompanyDaoImpl(
			EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<Schedule> schedules = scheduleDao.getList();
			return Response.status(Response.Status.OK).entity(schedules).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("rentalcompany/{rentalCompanyId}")
	public Response findByRentalCompany(@PathParam("rentalCompanyId") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		List<Schedule> schedule = scheduleDao.findByRentalCompanyId(id);
		if (schedule == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(schedule).build();
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") Integer id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Schedule schedule;
		try {
			schedule = scheduleDao.search(id);
			return Response.status(Response.Status.OK).entity(schedule).build();

		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();

		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Schedule schedule) {

		if (id == null || schedule == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			Schedule scheduleBase = scheduleDao.search(id);
			schedule.setRentalCompany(scheduleBase.getRentalCompany());
			scheduleDao.update(schedule);
			scheduleDao.commit();
			return Response.status(Response.Status.OK).entity(schedule).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Schedule schedule) {
		if (schedule == null || schedule.getRentalCompany() == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try {
			rentalCompanyDao.search(schedule.getRentalCompany().getId());
			scheduleDao.create(schedule);
			scheduleDao.commit();
			return Response.status(Response.Status.CREATED).entity(schedule).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
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
			scheduleDao.delete(id);
			scheduleDao.commit();
			return Response.status(Response.Status.OK).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (CommitException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
