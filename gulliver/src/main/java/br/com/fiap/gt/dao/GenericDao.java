package br.com.fiap.gt.dao;

import java.util.List;

import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.exception.EntityNotFoundException;

public interface GenericDao<E, K> {
	void create(E entity);

	E search(K id) throws EntityNotFoundException;

	void update(E entity);

	void delete(K id) throws EntityNotFoundException;

	void commit() throws CommitException;
	
	List<E> getList();
	
	E createOrUpdate(E entity);
}
