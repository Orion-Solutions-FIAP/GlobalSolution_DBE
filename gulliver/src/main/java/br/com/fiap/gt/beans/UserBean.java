package br.com.fiap.gt.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class UserBean {
	
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
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("company");
        return "login?faces-redirect=true";
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String save() {
		UserDaoImpl uDao = new UserDaoImpl(em);
		uDao.create(this.user);
		this.user = new User();
		return "index?faces-redirect=true";
	}
}
