package br.com.fiap.gt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.dao.CarDao;

public class CarDaoImpl extends GenericDaoImpl<Car, Integer> implements CarDao {

	public CarDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Car> findByBrandAndIsActive(String brand, boolean isActive) {
		return em.createNamedQuery("Car.FindByBrandAndIsActive", Car.class)
				.setParameter("b", "%" + brand.toLowerCase() + "%")
				.setParameter("is", isActive)
				.getResultList();
	}

	@Override

	public List<Car> findByRentalCompanyId(int id) {
		return em.createNamedQuery("Car.FindByRentalCompanyId", Car.class)
				.setParameter("id", id)
				.getResultList();
	}
}
