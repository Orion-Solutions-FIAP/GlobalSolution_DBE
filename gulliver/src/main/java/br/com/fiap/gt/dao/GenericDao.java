package br.com.fiap.gt.dao;

import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.exception.CommitException;

public interface GenericDao<E, K> {
	void create(E entity);

	E search(K id) throws EntityNotFoundException;

	void update(E entity);

	void delete(K id) throws EntityNotFoundException;

	void commit() throws CommitException;
}
