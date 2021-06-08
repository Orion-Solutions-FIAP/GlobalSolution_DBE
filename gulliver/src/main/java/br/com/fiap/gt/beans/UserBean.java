package br.com.fiap.gt.beans;

import javax.enterprise.context.RequestScoped;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 45d8072 (teste)
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
>>>>>>> f818b41 (Login adicionado)
import javax.inject.Named;
import javax.persistence.EntityManager;

=======
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.UserDao;
>>>>>>> 03cf525 (teste)
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class UserBean {
	
<<<<<<< HEAD
<<<<<<< HEAD
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager(); 

	private User user = new User();
	
	public String save() {
		UserDaoImpl uDao = new UserDaoImpl(em);
		uDao.create(this.user);
		this.user = new User();
		return "index?faces-redirect=true";
=======
=======
>>>>>>> 45d8072 (teste)
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private User user = new User();
	
	public String login() {
		User exist = new UserDaoImpl(em).exists(this.getUser());
		if(exist != null) {
			this.setUser(exist);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", getUser());
			return "index?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Invalido!", "Erro"));
			return "login?faces-redirect=true";
		}
<<<<<<< HEAD
>>>>>>> f818b41 (Login adicionado)
=======
=======
	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager(); 

	private User user = new User();
	
	public String save() {
		UserDao uDao = new UserDaoImpl(em);
		uDao.create(this.user);
		this.user = new User();
		return "index?faces-redirect=true";
>>>>>>> 03cf525 (teste)
>>>>>>> 45d8072 (teste)
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
<<<<<<< HEAD
<<<<<<< HEAD
=======
	

>>>>>>> f818b41 (Login adicionado)
=======
	

=======
>>>>>>> 03cf525 (teste)
>>>>>>> 45d8072 (teste)
}
