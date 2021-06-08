package br.com.fiap.gt.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.gt.model.User;
import br.com.fiap.gt.dao.UserDao;

public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao{

	public UserDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public User findByEmail(String email) {
		return em.createNamedQuery("User.FindByEmail", User.class)
		.setParameter("e", email.toLowerCase()).getSingleResult();
	}
}
