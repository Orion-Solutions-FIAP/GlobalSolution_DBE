package br.com.fiap.gt.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.CarDao;
import br.com.fiap.gt.dao.RatingDao;
import br.com.fiap.gt.dao.impl.CarDaoImpl;
import br.com.fiap.gt.dao.impl.RatingDaoImpl;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named  
@RequestScoped
public class RatingBean {
	
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	
	private Rating rating = new Rating();
	
	public void rate() {
		RatingDao ratingDao = new RatingDaoImpl(em);
		try {
			this.getRating().setRentalCompany(new RentalCompanyDaoImpl(em).search(1));
			this.getRating().setUser(new UserDaoImpl(em).search(1));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ratingDao.create(this.getRating());
	}
	


	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	
	
}
