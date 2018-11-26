package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.Medico;

public class MedicoDAO {

	private static MedicoDAO instance;
	protected EntityManager entityManager;

	public static MedicoDAO getInstance() {
		if (instance == null) {
			instance = new MedicoDAO();
		}
		return instance;
	}

	private MedicoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public Medico getById(final int id) {
		return entityManager.find(Medico.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Medico> findAll() {
		return entityManager.createQuery("FROM " + Medico.class.getName()).getResultList();
	}

	public void persist(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(medico);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(medico);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			medico = entityManager.find(Medico.class, medico.getCrm());
			entityManager.remove(medico);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Medico medico = getById(id);
			remove(medico);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
