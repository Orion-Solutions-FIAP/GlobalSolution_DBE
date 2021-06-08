package br.com.fiap.gt.dao;

import java.util.List;

import br.com.fiap.gt.model.RentalCompany;

public interface RentalCompanyDao extends GenericDao<RentalCompany, Integer> {

	//Buscar Locadora de carros por nome
	List<RentalCompany> findByName(String name);
}
