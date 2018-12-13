package br.com.pm.clinicasaracura.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.AgendaEquipamento;

public class AgendaEquipamentoDAO {

	private static AgendaEquipamentoDAO instance;
	protected EntityManager entityManager;

	public static AgendaEquipamentoDAO getInstance() {
		if (instance == null) {
			instance = new AgendaEquipamentoDAO();
		}
		return instance;
	}

	private AgendaEquipamentoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public AgendaEquipamento getByDate(final Date date) {
		return entityManager.find(AgendaEquipamento.class, date);
	}

	@SuppressWarnings("unchecked")
	public List<AgendaEquipamento> findAll() {
		return entityManager.createQuery("FROM " + AgendaEquipamento.class.getName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AgendaEquipamento> getByDateAndCrm(final Date date, int crm) {
		return entityManager.createQuery("FROM " + AgendaEquipamento.class.getName() + " WHERE dataAgendamento='" + date + "' AND idMedico=" + crm).getResultList();
	}
	
	public void persist(AgendaEquipamento agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(AgendaEquipamento agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(AgendaEquipamento agenda) {
		try {
			entityManager.getTransaction().begin();
			agenda = entityManager.find(AgendaEquipamento.class, agenda.getDataAgendamento());
			entityManager.remove(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeByDate(final Date date) {
		try {
			AgendaEquipamento agenda = getByDate(date);
			remove(agenda);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}