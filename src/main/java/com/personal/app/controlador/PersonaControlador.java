package com.personal.app.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;

import com.personal.app.App;
import com.personal.app.EditarModelo;
import com.personal.app.modelo.Persona;
import com.personal.app.servicio.PersonaServicio;
import com.personal.app.vista.PersonaUI;

public class PersonaControlador implements Controlador {

	private PersonaUI personaUI;
	private PersonaServicio personaServicio;
	private ApplicationContext context;
	private List<Persona> personal;
	
	public PersonaControlador(ApplicationContext context) {
		this.context = context;
		this.personaServicio = context.getBean(PersonaServicio.class);
		this.personal = context.getBean(PersonaServicio.class).listarPersonal();
		this.personaUI = new PersonaUI(personal,this);
	}

	public void dibujar(App app) {
		app.getContentPane().add(personaUI);

	}

	public ActionListener accionGuardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Persona persona = new Persona();
				persona.setIdentidad(personaUI.getjIdentidad().getText());
				persona.setNombres(personaUI.getjNombres().getText());
				try {
					personaServicio.guardar(persona);
					personaUI.agregar(persona);
					JOptionPane.showMessageDialog(personaUI,"Operacion Exitosa");
				} catch (Exception exe) {
					// TODO: handle exception
					exe.printStackTrace();
				}
			}
		};
	}

	public ActionListener accionEliminar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Persona persona = personaUI.personaSeleccionada();
				String _m = "Deseas Eliminar a la persona "+ persona.getNombres() +" ?";
				int mensaje = JOptionPane.showConfirmDialog(personaUI,_m,"Eliminar Persona",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(mensaje == JOptionPane.YES_OPTION)
				{
					try {
						personaServicio.eliminar(persona);
						personaUI.eliminar(persona);
						
					} catch (Exception exe) {
						// TODO: handle exception
						exe.printStackTrace();
					}
				}
			}
		};
	}

	public EditarModelo accionEditar() {
		// TODO Auto-generated method stub
		return new EditarModelo() {
			public void editar(Object aValue, int rowIndex, int columnIndex) {
				Persona persona = personaUI.personaSeleccionada();
				String _m = "Deseas Editar a la nombre "+ persona.getNombres() +" por "+ aValue+" ?";
				int mensaje = JOptionPane.showConfirmDialog(personaUI,_m,"Editar Persona",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(mensaje == JOptionPane.YES_OPTION)
				{
					try {
						personaServicio.editar(persona,(String) aValue);
						personaUI.actualizarPersonas();
					} catch (Exception exe) {
						exe.printStackTrace();
					}
				}
			}
		};			
	}

	public ActionListener accionAsistencia() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Persona persona = personaUI.personaSeleccionada();
				persona = personaServicio.obtenerFullPersona(persona);
				personaUI.actualizarAsistencias(persona);
			}
		};
	}

	public ActionListener accionFiltrarAsistencia() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				personaUI.filtrarAsistencia();
			}
		};
	}


}
