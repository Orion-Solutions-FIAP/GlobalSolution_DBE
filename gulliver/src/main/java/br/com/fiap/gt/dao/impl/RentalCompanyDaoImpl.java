package br.com.fiap.gt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.dao.RentalCompanyDao;

public class RentalCompanyDaoImpl extends GenericDaoImpl<RentalCompany, Integer> implements RentalCompanyDao {

	public RentalCompanyDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<RentalCompany> findByName(String name) {
		return em.createNamedQuery("RentalCompany.FindByName", RentalCompany.class)
				.setParameter("n", "%" + name.toLowerCase() + "%").setMaxResults(50).getResultList();
	}

}
