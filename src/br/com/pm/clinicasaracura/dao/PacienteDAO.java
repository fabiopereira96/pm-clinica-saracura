package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.Paciente;

public class PacienteDAO {

	private static PacienteDAO instance;
	protected EntityManager entityManager;

	public static PacienteDAO getInstance() {
		if (instance == null) {
			instance = new PacienteDAO();
		}

		return instance;
	}

	private PacienteDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Paciente getById(final int id) {
		return entityManager.find(Paciente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findAll() {
		return entityManager.createQuery("FROM " + Paciente.class.getName()).getResultList();
	}

	public void persist(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			paciente = entityManager.find(Paciente.class, paciente.getIdPaciente());
			entityManager.remove(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Paciente paciente = getById(id);
			remove(paciente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
