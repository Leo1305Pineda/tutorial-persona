package com.personal.app;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.personal.app.modelo.Persona;
import com.personal.app.servicio.PersonaServicio;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
	public void testApp()
    {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguracion.class);
		
		PersonaServicio ps =  context.getBean(PersonaServicio.class);
		
		Persona p =  ps.listarPersonal().get(0);
		
		ps.obtenerFullPersona(p);
		
    }
}
