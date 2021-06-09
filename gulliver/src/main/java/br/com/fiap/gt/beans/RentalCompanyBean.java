package br.com.fiap.gt.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
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
	
	public RentalCompany getRentalCompany() {
		return rentalCompany;
	}
	public void setRentalCompany(RentalCompany rentalCompany) {
		this.rentalCompany = rentalCompany;
	}
	
	

}
