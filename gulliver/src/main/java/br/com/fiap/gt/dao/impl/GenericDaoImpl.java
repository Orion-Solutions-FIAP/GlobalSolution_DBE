package br.com.fiap.gt.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fiap.gt.dao.GenericDao;
import br.com.fiap.gt.exception.CommitException;

public abstract class GenericDaoImpl<E, K> implements GenericDao<E, K> {

	protected EntityManager em;

	private Class<E> clazz;

	@SuppressWarnings("all")
	public GenericDaoImpl(EntityManager em) {
		this.em = em;
		this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void create(E entity) {
		em.persist(entity);
	}

	@Override
	public E search(K id) {
		E entity = em.find(clazz, id);
		return entity;
	}

	@Override
	public void update(E entity) {
		em.merge(entity);
	}

	@Override
	public void delete(K id) {
		em.remove(search(id));
	}

	@Override
	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new CommitException();
		}
	}

	@Override
	public List<E> getList() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(clazz);
		query.from(clazz);
		return em.createQuery(query).getResultList();
	}

	@Override
	public E createOrUpdate(E entity) {
		return em.merge(entity);
	}
}
