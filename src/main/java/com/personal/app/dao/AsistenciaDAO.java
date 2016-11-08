package com.personal.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personal.app.modelo.Asistencia; 

@Repository
public class AsistenciaDAO {
	@Autowired
	protected SessionFactory sessionFactory;

	@Transactional()
	public List getAll() {
		// TODO Auto-generated method stub
		List  entities = new ArrayList();
		Session em = sessionFactory.getCurrentSession(); ///.openSession();// .getCurrentSession();
		Transaction tx = null;
		try {
			tx = em.beginTransaction();
			entities = em.createCriteria(Asistencia.class).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return entities;
	}

	@Transactional(readOnly= false)
	public void guardar(Asistencia obj) {
		// TODO Auto-generated method stub
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		em.saveOrUpdate(obj);
		em.flush();
		tx.commit(); 
	}
	
	
	@Transactional(readOnly= false)
	public void eliminar(Asistencia asistencia) {
		// TODO Auto-generated method stub
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		em.delete(asistencia);
		em.flush();
		tx.commit();
	}

	public List<Asistencia> getAll(Date fecha) {
		// TODO Auto-generated method stub
		List  entities = new ArrayList();
		Session em = sessionFactory.getCurrentSession(); ///.openSession();// .getCurrentSession();
		Transaction tx = null;
		try {
			tx = em.beginTransaction();
			entities = em.createCriteria(Asistencia.class).add(Restrictions.eq("fecha",fecha)).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return entities;
	}

	public void guardar(List<Asistencia> asistencias) {
		// TODO Auto-generated method stub
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		for(Asistencia asistencia : asistencias)
		{
			em.save(asistencia);
		}
		em.flush();
		tx.commit(); 
	}
 
	
	
	
}
