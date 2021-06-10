package br.com.fiap.gt.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.RatingDao;
import br.com.fiap.gt.dao.impl.RatingDaoImpl;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RatingPK;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class RatingBean {

	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private RentalCompany rentalCompany = (RentalCompany) FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().get("company");
	private Rating rating = new Rating();
	private Rating rate = new Rating();
	RatingDao ratingDao = new RatingDaoImpl(em);
	private Rating ratingEdit = (Rating) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.get("rating");

	public String rate() {
		this.getRating().setRentalCompany(this.rentalCompany);
		this.getRating().setUser(this.user);
		ratingDao.createOrUpdate(this.getRating());
		return "rating?faces-redirect=true";
	}

	public List<Rating> getListRating() {
		return ratingDao.getList();
	}

	public List<Rating> getUserRating() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = (User) context.getExternalContext().getSessionMap().get("user");
		return ratingDao.findByUser(user);
	}

	public String deleteRating() throws EntityNotFoundException {
		try {
			ratingDao.delete(new RatingPK(rate.getId(), rate.getRentalCompany().getId(), rate.getUser().getId()));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return "list-rating?faces-redirect=true";
	}

	public List<Rating> getRentalRating() {
		FacesContext context = FacesContext.getCurrentInstance();
		RentalCompany rentalCompany = (RentalCompany) context.getExternalContext().getSessionMap().get("company");

		return ratingDao.findByRentCompany(rentalCompany);
	}

	public String newEdit() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rating", rating);
		return "editRating?faces-redirect=true";
	}

	public String editRating(Rating ratingEdit) {
		RatingDao ratingDao = new RatingDaoImpl(em);
		ratingDao.update(ratingEdit);
		return "list-rating?faces-redirect=true";
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Rating getRate() {
		return rate;
	}

	public void setRate(Rating rate) {
		this.rate = rate;
	}

	public Rating getRatingEdit() {
		return ratingEdit;
	}

	public void setRatingEdit(Rating ratingEdit) {
		this.ratingEdit = ratingEdit;
	}

}
