package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.DiaAtendimento;

public class DiaAtendimentoDAO {

	private static DiaAtendimentoDAO instance;
	protected EntityManager entityManager;

	public static DiaAtendimentoDAO getInstance() {
		if (instance == null) {
			instance = new DiaAtendimentoDAO();
		}

		return instance;
	}

	private DiaAtendimentoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public DiaAtendimento getById(final int id) {
		return entityManager.find(DiaAtendimento.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<DiaAtendimento> findAll() {
		return entityManager.createQuery("FROM " + DiaAtendimento.class.getName()).getResultList();
	}

	public void persist(DiaAtendimento diaAtendimento) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(diaAtendimento);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(DiaAtendimento diaAtendimento) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(diaAtendimento);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(DiaAtendimento diaAtendimento) {
		try {
			entityManager.getTransaction().begin();
			diaAtendimento = entityManager.find(DiaAtendimento.class, diaAtendimento.getIdDia());
			entityManager.remove(diaAtendimento);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			DiaAtendimento diaAtendimento = getById(id);
			remove(diaAtendimento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

