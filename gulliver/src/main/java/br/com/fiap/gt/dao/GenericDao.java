package br.com.fiap.gt.dao;

import java.util.List;

import br.com.fiap.gt.exception.CommitException;

public interface GenericDao<E, K> {
	void create(E entity);

	E search(K id);

	void update(E entity);

	void delete(K id);

	void commit() throws CommitException;
	
	List<E> getList();
}
