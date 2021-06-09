package br.com.fiap.gt.dao;
import java.util.List;

import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.Schedule;

public interface ScheduleDao extends GenericDao<Schedule, Integer>{

	//Buscar hor�rios por locadora de carros
	List<Schedule> findByRentalCompany(RentalCompany rentalCompany);
	
	//Buscar hor�rios pelo Id da Locadora de Carros
	List<Schedule> findByRentalCompanyId(int id);
}
