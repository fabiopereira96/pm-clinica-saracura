package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.Especialidade;

public class EspecialidadeDAO {

	private static EspecialidadeDAO instance;
	protected EntityManager entityManager;

	public static EspecialidadeDAO getInstance() {
		if (instance == null) {
			instance = new EspecialidadeDAO();
		}

		return instance;
	}

	private EspecialidadeDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Especialidade getById(final int id) {
		return entityManager.find(Especialidade.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Especialidade> findAll() {
		return entityManager.createQuery("FROM " + Especialidade.class.getName()).getResultList();
	}

	public void persist(Especialidade especialidade) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(especialidade);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Especialidade especialidade) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(especialidade);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Especialidade especialidade) {
		try {
			entityManager.getTransaction().begin();
			especialidade = entityManager.find(Especialidade.class, especialidade.getIdEspecialidade());
			entityManager.remove(especialidade);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Especialidade especialidade = getById(id);
			remove(especialidade);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

