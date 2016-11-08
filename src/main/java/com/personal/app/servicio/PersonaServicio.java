package com.personal.app.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.app.dao.PersonaDAO;
import com.personal.app.modelo.Persona;

@Service("personaServicio")
public class PersonaServicio {

	@Autowired
	private PersonaDAO personaDAO;

	
	@Transactional
	public List<Persona> listarPersonal(){
		return personaDAO.getAll();
	}


	public void guardar(Persona persona) {
		// TODO Auto-generated method stub
		personaDAO.guardar(persona);
	}


	public void eliminar(Persona persona) {
		// TODO Auto-generated method stub
		personaDAO.eliminar(persona);
	}


	public void editar(Persona persona, String nombre) {
		// TODO Auto-generated method stub
		//valido que el nombre este bien y si esta bien lo guardo 
		persona.setNombres(nombre);
		personaDAO.guardar(persona);
	}


	public Persona obtenerFullPersona(Persona persona) {
		// TODO Auto-generated method stub
		return personaDAO.cargarFull(persona);
		 
	}
	
	
}
