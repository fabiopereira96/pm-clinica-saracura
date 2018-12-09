package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.TipoExame;

public class TipoExameDAO {
	
	private static TipoExameDAO instance;
	protected EntityManager entityManager;

	public static TipoExameDAO getInstance() {
		if (instance == null) {
			instance = new TipoExameDAO();
		}
		return instance;
	}

	private TipoExameDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public TipoExame getById(final int id) {
		return entityManager.find(TipoExame.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoExame> findAll() {
		return entityManager.createQuery("FROM " + TipoExame.class.getName()).getResultList();
	}

	public void persist(TipoExame exame) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(exame);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(TipoExame exame) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(exame);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(TipoExame exame) {
		try {
			entityManager.getTransaction().begin();
			exame = entityManager.find(TipoExame.class, exame.getIdTipoExame());
			entityManager.remove(exame);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			TipoExame exame = getById(id);
			remove(exame);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
