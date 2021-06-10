package br.com.fiap.gt.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.RatingDao;
import br.com.fiap.gt.dao.impl.RatingDaoImpl;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named  
@RequestScoped
public class RatingBean {
	
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private RentalCompany rentalCompany = (RentalCompany) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company");
	private Rating rating = new Rating();
	private Rating ratingEdit = new Rating();
	
	
	public void rate() {
		RatingDao ratingDao = new RatingDaoImpl(em);
		this.getRating().setRentalCompany(this.rentalCompany);
		this.getRating().setUser(this.user);
		ratingDao.create(this.getRating());
	}
	
	public List<Rating> getListRating(){
		RatingDao ratingDao = new RatingDaoImpl(em);
        return ratingDao.getList();
    }
	
	public List<Rating> getUserRating(){
		RatingDao ratingDao = new RatingDaoImpl(em);
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        return ratingDao.findByUser(user);
    }
	
	//public void newEdit() {
	//	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rating", rating);
	//	System.out.println(this.rating);
//	}
	
	public String editRating() {
		RatingDao ratingDao = new RatingDaoImpl(em);
		ratingDao.update(this.ratingEdit);
		return "list-rating?faces-redirect=true";
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Rating getRatingEdit() {
		return ratingEdit;
	}

	public void setRatingEdit(Rating ratingEdit) {
		this.ratingEdit = ratingEdit;
		System.out.println(this.ratingEdit);
	}
	
	
	
	
}
