package com.personal.app.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.app.dao.AsistenciaDAO;
import com.personal.app.dao.PersonaDAO;
import com.personal.app.modelo.Asistencia;
import com.personal.app.modelo.Persona;

@Service("asistenciaServicio")
public class AsistenciaServicio {

	@Autowired
	private AsistenciaDAO asistenciaDAO ;

	@Autowired
	private PersonaDAO personaDAO;
	
	@Transactional
	public List<Persona> listar(){
		return asistenciaDAO.getAll();
	}

	@Transactional
	public void guardar(Asistencia asistencia) {
		// TODO Auto-generated method stub
		asistenciaDAO.guardar(asistencia);
	}

	@Transactional
	public void eliminar(Asistencia asistencia) {
		// TODO Auto-generated method stub
		asistenciaDAO.eliminar(asistencia);
	}

	public List<Persona> personalActivo() {
		// TODO Auto-generated method stub
		return personaDAO.personalActivo();
	}

	public List<Asistencia> crearAsistencia(List<Persona> personal) {
		// TODO Auto-generated method stub
		Date fecha = new Date();
		return crearAsistencia(personal,fecha);
	}
	
	public List<Asistencia> crearAsistencia(List<Persona> personal,Date fecha) {
		List<Asistencia> asistencias =  asistenciaDAO.getAll(fecha);
		List<Asistencia> asistenciasGuardar = new ArrayList<Asistencia>();
		for(Persona persona: personal)
		{	Boolean encontrado = false;
			for(Asistencia asistencia : asistencias)
			{
				if(asistencia.getPersona().getId().equals(persona.getId())){encontrado = true; break;}
			}
			if(!encontrado)
			{
				Asistencia asistencia = new Asistencia();
				asistencia.setAsistencia("ASISTIO");
				asistencia.setFecha(fecha);
				asistencia.setPersona(persona);
				asistencias.add(asistencia);
				asistenciasGuardar.add(asistencia);
			}
		}
		asistenciaDAO.guardar(asistenciasGuardar);
		return asistencias;
	}
	
	
	
	

}





