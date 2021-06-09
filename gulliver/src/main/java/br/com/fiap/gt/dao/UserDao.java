package br.com.fiap.gt.dao;

import br.com.fiap.gt.model.User;

public interface UserDao extends GenericDao<User, Integer>{

	//Buscar usuï¿½rio por e-mail
	User findByEmail(String email);
	
	User exists(User user);
}
