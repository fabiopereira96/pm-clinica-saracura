package br.com.pm.clinicasaracura.dao;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.AgendaMedica;
import br.com.pm.clinicasaracura.entity.Medico;

public class AgendaMedicaDAO {

	private static AgendaMedicaDAO instance;
	protected EntityManager entityManager;
	private final SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public static AgendaMedicaDAO getInstance() {
		if (instance == null) {
			instance = new AgendaMedicaDAO();
		}
		return instance;
	}

	private AgendaMedicaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public AgendaMedica getByDate(final Date date) {
		return entityManager.find(AgendaMedica.class, date);
	}
	
	@SuppressWarnings("unchecked")
	public List<AgendaMedica> getByMedico(final int medicoCrm) {
		return entityManager.createQuery("FROM " + AgendaMedica.class.getName() + " WHERE idMedico=" + medicoCrm).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AgendaMedica> isConflict(final Date date, Medico doctor) {
		String strDate = this.sdf.format(date);

		return entityManager.createQuery(
			"FROM " + AgendaMedica.class.getName() + " WHERE diaAgendamento <= '" + strDate + "' AND '" + strDate + "' <= ADDTIME(diaAgendamento, '" + doctor.getIntervaloAtendimento() + ":00') AND idMedico=" + doctor.getCrm()
		).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AgendaMedica> findAll() {
		return entityManager.createQuery("FROM " + AgendaMedica.class.getName()).getResultList();
	}

	public void persist(AgendaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(AgendaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(AgendaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			agenda = entityManager.find(AgendaMedica.class, agenda.getDiaAgendamento());
			entityManager.remove(agenda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeByDate(final Date date) {
		try {
			AgendaMedica agenda = getByDate(date);
			remove(agenda);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
