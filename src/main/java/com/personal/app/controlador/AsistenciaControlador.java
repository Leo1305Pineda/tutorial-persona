package com.personal.app.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;

import com.personal.app.App;
import com.personal.app.EditarModelo;
import com.personal.app.modelo.Asistencia;
import com.personal.app.modelo.Persona;
import com.personal.app.servicio.AsistenciaServicio;
import com.personal.app.vista.AsistenciaUI;

public class AsistenciaControlador  implements Controlador {

	private AsistenciaUI asistenciaUI;
	private AsistenciaServicio asistenciaServicio;
	private ApplicationContext context;
	private List<Persona> personal;
	private List<Asistencia> asistencias;
	
	public AsistenciaControlador(ApplicationContext context) {
		this.context = context;
		this.asistenciaServicio = context.getBean(AsistenciaServicio.class);
		this.personal = asistenciaServicio.personalActivo();
		this.asistencias = asistenciaServicio.crearAsistencia(personal);
		this.asistenciaUI = new AsistenciaUI(personal,asistencias,this, new Date());
	}
	
	
	public void dibujar(App app) {
		// TODO Auto-generated method stub
		app.getContentPane().add(asistenciaUI);
	}


	public ActionListener accionGenerarAsistencia() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date fecha = (Date) asistenciaUI.getdFecha().getModel().getValue();
				asistencias = asistenciaServicio.crearAsistencia(personal,fecha);
				asistenciaUI.actualizarAsistencias(asistencias);

			}
		};
	}

	public EditarModelo accionEditar() {
		// TODO Auto-generated method stub
		return new EditarModelo() {
			public void editar(Object aValue, int rowIndex, int columnIndex) {
				Asistencia asistencia = asistenciaUI.asistenciaSeleccionada();
				String _m = "Deseas Editar la asistencia  ?";
				int mensaje = JOptionPane.showConfirmDialog(asistenciaUI,_m,"Editar Asistencia",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(mensaje == JOptionPane.YES_OPTION)
				{
					try {
				    	switch (columnIndex) {
						case 3:
				    		asistencia.setAsistencia((String)aValue);
							break;
						case 4:
				    		asistencia.setComentario((String) aValue);
							break;
						}
						asistenciaServicio.guardar(asistencia);
						asistenciaUI.actualizarTabla();
					} catch (Exception exe) {
						exe.printStackTrace();
					}
				}
			}
		};			
	}
}
