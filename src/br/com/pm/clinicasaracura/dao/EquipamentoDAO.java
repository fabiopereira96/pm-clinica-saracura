package br.com.pm.clinicasaracura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pm.clinicasaracura.entity.Equipamento;

public class EquipamentoDAO {
	
	private static EquipamentoDAO instance;
	protected EntityManager entityManager;

	public static EquipamentoDAO getInstance() {
		if (instance == null) {
			instance = new EquipamentoDAO();
		}
		return instance;
	}
	
	private EquipamentoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-saracura-jpa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Equipamento getById(final int id) {
		return entityManager.find(Equipamento.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipamento> findByExamId(final int id) {
		return entityManager.createQuery("FROM " + Equipamento.class.getName() + " WHERE idTipoExame = " + id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipamento> findAll() {
		return entityManager.createQuery("FROM " + Equipamento.class.getName()).getResultList();
	}

	public void persist(Equipamento equip) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(equip);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Equipamento equip) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(equip);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Equipamento equip) {
		try {
			entityManager.getTransaction().begin();
			equip = entityManager.find(Equipamento.class, equip.getIdEquipamento());
			entityManager.remove(equip);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Equipamento equip = getById(id);
			remove(equip);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
