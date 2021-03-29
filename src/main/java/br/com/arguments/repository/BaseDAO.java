package br.com.arguments.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDAO {

	@PersistenceContext
	private EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}
}