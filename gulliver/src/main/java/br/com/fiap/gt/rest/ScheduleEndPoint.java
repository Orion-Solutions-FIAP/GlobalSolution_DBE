package br.com.fiap.gt.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.gt.dao.ScheduleDao;
import br.com.fiap.gt.dao.impl.ScheduleDaoImpl;
import br.com.fiap.gt.model.Schedule;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleEndPoint {

	private ScheduleDao scheduleDao = new ScheduleDaoImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

	@GET
	public Response index() {
		try {
			List<Schedule> schedules = scheduleDao.getList();
			System.out.println(schedules.get(0).getRentalCompany().getName());
			return Response.status(Response.Status.OK).entity(schedules).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
