package br.com.fiap.gt.dao;

import java.util.List;

import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RatingPK;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.User;

public interface RatingDao extends GenericDao<Rating, RatingPK> {

	//Contar avalia��es por Locadora de Carros
	Long countByRentalCompany(RentalCompany rentalCompany);
	
	//Buscar avalia��es por usu�rio
	List<Rating> findByUser(User user);
	
	//Buscar pelo Id da locadora de carros
	List<Rating> findByRentalCompanyId(int id);

	//Buscar avaliacao por locadora
    List<Rating> findByRentCompany(RentalCompany rentalCompany);
}
