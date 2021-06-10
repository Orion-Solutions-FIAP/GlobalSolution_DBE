package br.com.fiap.gt.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	@Override
	public User exists(User user) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE email = :email AND password = :password", User.class)
				.setParameter("password", user.getPassword())
				.setParameter("email", user.getEmail());
		User result;
		try {
			result = query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		} 
		
	}
}

