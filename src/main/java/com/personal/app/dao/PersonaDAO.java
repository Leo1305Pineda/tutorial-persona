package com.personal.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personal.app.modelo.Persona;

@Repository
public class PersonaDAO {
	
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
			entities = em.createCriteria(Persona.class).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return entities;
	}

	@Transactional(readOnly= false)
	public void guardar(Object obj) {
		// TODO Auto-generated method stub
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		em.saveOrUpdate(obj);
		em.flush();
		tx.commit(); 
	}
	@Transactional(readOnly= false)
	public void eliminar(Persona persona) {
		// TODO Auto-generated method stub
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		em.delete(persona);
		em.flush();
		tx.commit();
	}

	public Persona cargarFull(Persona persona) {
		// TODO Auto-generated method stub
 
		Session em = sessionFactory.getCurrentSession();
		Transaction tx = em.beginTransaction();
		persona = em.get(Persona.class,persona.getId());
		persona.getAsistencias().size();
		tx.commit();
		return persona;
		
	}

	public List<Persona> personalActivo() {
		// TODO Auto-generated method stub
		List  entities = new ArrayList();
		Session em = sessionFactory.getCurrentSession(); ///.openSession();// .getCurrentSession();
		Transaction tx = null;
		try {
			tx = em.beginTransaction();
			entities = em.createCriteria(Persona.class).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return entities;
	}
	
	
}
