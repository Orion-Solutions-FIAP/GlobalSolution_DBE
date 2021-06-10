package br.com.fiap.gt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RatingPK;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.dao.RatingDao;

public class RatingDaoImpl extends GenericDaoImpl<Rating, RatingPK> implements RatingDao {

	public RatingDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Long countByRentalCompany(RentalCompany rentalCompany) {
		return em.createNamedQuery("Rating.CountByRentalCompany", Long.class).setParameter("rc", rentalCompany)
				.getSingleResult();
	}

	@Override
	public List<Rating> findByUser(User user) {
		return em.createNamedQuery("Rating.FindByUser", Rating.class).setParameter("u", user).setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<Rating> findByRentalCompanyId(int id) {
		return em.createNamedQuery("Rating.FindByRentalCompanyId", Rating.class).setParameter("id", id).getResultList();
	}
	
	@Override
    public List<Rating> findByRentCompany(RentalCompany rentalCompany) {
        return em.createQuery("Select r from Rating r where r.rentalCompany.id = :rentalId", Rating.class)
                    .setParameter("rentalId", rentalCompany.getId())
                    .getResultList();
    }

}
