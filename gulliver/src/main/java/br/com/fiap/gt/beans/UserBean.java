package br.com.fiap.gt.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.UserDao;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class UserBean {
	
	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager(); 

	private User user = new User();
	
	public String save() {
		UserDao uDao = new UserDaoImpl(em);
		uDao.create(this.user);
		this.user = new User();
		return "index?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
