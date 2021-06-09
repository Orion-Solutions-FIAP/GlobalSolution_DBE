package br.com.fiap.gt.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.CarDao;
import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.impl.CarDaoImpl;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class RentalCompanyBean {
	
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private RentalCompany rentalCompany = new RentalCompany();
	
	public List<RentalCompany> getRentalCompanies(){
		RentalCompanyDao dao = new RentalCompanyDaoImpl(em);
		return dao.getList();
	}
	
	public List<Car> getCarsFromRentalCompany() throws EntityNotFoundException{
		CarDao carDao = new CarDaoImpl(em);
		List<Car> cars = carDao.findByRentalCompany(1);
		System.out.println(cars);
		return cars;
	}
	
	public RentalCompany getRentalCompany() {
		return rentalCompany;
	}
	public void setRentalCompany(RentalCompany rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	

}
