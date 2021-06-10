package br.com.fiap.gt.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
	private RentalCompany rental = new RentalCompany();
	private RentalCompany rentalCompany = (RentalCompany) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company");
	
	
	public List<RentalCompany> getRentalCompanies(){
		RentalCompanyDao dao = new RentalCompanyDaoImpl(em);
		return dao.getList();
	}
	
	public List<Car> getCarsFromRentalCompany() throws EntityNotFoundException{
		CarDao carDao = new CarDaoImpl(em);
		List<Car> cars = carDao.findByRentalCompanyId(this.rentalCompany.getId());
		return cars;
	}
	
//	public RentalCompany getCompanySession() {
//		RentalCompany company = (RentalCompany) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company");
//		return company;
//	}
	
	public String details() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("company", rental);
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("rental", rental);
//		context.getExternalContext().getFlash().put("rental", rental);
//		context.getExternalContext().getFlash().keep("rental");
        return "rating?faces-redirect=true";
    }
	
	public RentalCompany getRentalCompany() {
		return rentalCompany;
	}
	public void setRentalCompany(RentalCompany rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public RentalCompany getRental() {
		return rental;
	}

	public void setRental(RentalCompany rental) {
		this.rental = rental;
	}

	

}
