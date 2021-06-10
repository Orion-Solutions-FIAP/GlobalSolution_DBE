package br.com.fiap.gt.dao;

import java.util.List;

import br.com.fiap.gt.model.Car;

public interface CarDao extends GenericDao<Car, Integer> {

	//Buscar carros por Marca e se est� ativo ou n�o.
	List<Car> findByBrandAndIsActive(String brand, boolean isActive);
	
	//Buscar carros pelo Id da locadora de carros
	List<Car> findByRentalCompanyId(int id);
}
