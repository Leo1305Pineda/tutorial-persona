package com.personal.app.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import com.personal.app.App;
import com.personal.app.controlador.PersonaControlador;
import com.personal.app.modelo.Asistencia;
import com.personal.app.modelo.Persona;
import com.personal.app.vista.componentes.JTableAsistenciaModel;
import com.personal.app.vista.componentes.JTablePersonaModel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
 

public class PersonaUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable jtable;
	private JScrollPane scrollPanel;

	private JButton jAsistencia;
	private JButton    jBtnGuardar;
	private JButton    jButtonEliminar;
	private JTextField jNombres;
	private JTextField jIdentidad;

	private JPanel jPnPersonal;
	private JPanel jPnPersonalNuevo;
	private JPanel jPnPersonalAsistencia;
	
	private List<Persona> personal;
	private Persona persona;
	private List<Asistencia> asistencias = new ArrayList<Asistencia>();
	private PersonaControlador controlador;

	private JTable jtableAsistencias;
	private JScrollPane scrollPanelAsistencias;

	private UtilDateModel   mDesde;
	private JDatePanelImpl  pDesde;
	private JDatePickerImpl dDesde;

	private UtilDateModel   mHasta;
	private JDatePanelImpl  pHasta;
	private JDatePickerImpl dHasta;

	
	
	
	public PersonaUI(List<Persona> personal, PersonaControlador controlador) {
		super(new MigLayout());
		this.setPersonal(personal);
		this.controlador = controlador;
		JPanel aux = new JPanel(new GridLayout(0,1));
		jtable = new JTable(new JTablePersonaModel(personal, controlador.accionEditar()));
		scrollPanel= new JScrollPane(jtable);
		//jtable.se(new Dimension(App.getWidthPantalla(),App.getHeightPantalla()));
		jtable.setPreferredScrollableViewportSize(new Dimension(App.getWidthPantalla()/2,App.getHeightPantalla()/2));
		jtable.setFillsViewportHeight(true);
		
		
		jPnPersonal = new JPanel(new MigLayout());
		jPnPersonal.setBorder(new TitledBorder("Listado de Personal "));
		jPnPersonal.add(scrollPanel,"split 2");
		add(jPnPersonal);			
		
		jPnPersonalNuevo = new JPanel( new MigLayout());
		jPnPersonalNuevo.setBorder(new TitledBorder("Agregar Nuevo "));

		jNombres = new JTextField(35);
		jIdentidad = new JTextField(15);
		
		jBtnGuardar = new JButton("Guardar");
		jBtnGuardar.addActionListener(controlador.accionGuardar());
		
		
		jButtonEliminar = new JButton("ELiminar");
		jButtonEliminar.addActionListener(controlador.accionEliminar());
		aux.add(jButtonEliminar);
		
		
		jAsistencia = new JButton("Asistencia");
		jAsistencia.addActionListener(controlador.accionAsistencia());
		aux.add(jAsistencia);
		jPnPersonal.add(aux);
		
		jPnPersonalNuevo.add( new JLabel("Identidad :"));
		jPnPersonalNuevo.add(jIdentidad,"wrap");
		jPnPersonalNuevo.add( new JLabel("Nombre :"));
		jPnPersonalNuevo.add(jNombres,"wrap");
		jPnPersonalNuevo.add(jBtnGuardar);
		add(jPnPersonalNuevo,"wrap");			

		/*************************************************************************************************************************************/
		jtableAsistencias = new JTable(new JTableAsistenciaModel(asistencias));
		scrollPanelAsistencias= new JScrollPane(jtableAsistencias);
		//jtable.se(new Dimension(App.getWidthPantalla(),App.getHeightPantalla()));
		jtableAsistencias.setPreferredScrollableViewportSize(new Dimension(App.getWidthPantalla(),App.getHeightPantalla()/2));
		jtableAsistencias.setFillsViewportHeight(true);
		
		
		mDesde = new UtilDateModel(new Date());
		pDesde = new JDatePanelImpl(mDesde);
		dDesde = new JDatePickerImpl(pDesde);
		dDesde.addActionListener(controlador.accionFiltrarAsistencia());
		
		mHasta = new UtilDateModel(new Date());
		pHasta = new JDatePanelImpl(mHasta);
		dHasta = new JDatePickerImpl(pHasta);
		dHasta.addActionListener(controlador.accionFiltrarAsistencia());

		jPnPersonalAsistencia = new JPanel(new MigLayout());
		jPnPersonalAsistencia.setSize(new Dimension(200,200));
		jPnPersonalAsistencia.setBorder(new TitledBorder("Listado de Asistencias "));
		jPnPersonalAsistencia.add(new JLabel("Desde :"),"split 4");
		jPnPersonalAsistencia.add(dDesde);
		jPnPersonalAsistencia.add(new JLabel("Hasta :"));
		jPnPersonalAsistencia.add(dHasta,"wrap");
		jPnPersonalAsistencia.add(scrollPanelAsistencias);
		add(jPnPersonalAsistencia,"span");			
	
	}

	public List<Persona> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Persona> personal) {
		this.personal = personal;
	}

	public JTextField getjNombres() {
		return jNombres;
	}

	public void setjNombres(JTextField jNombres) {
		this.jNombres = jNombres;
	}

	public JTextField getjIdentidad() {
		return jIdentidad;
	}

	public void setjIdentidad(JTextField jIdentidad) {
		this.jIdentidad = jIdentidad;
	}

	public void agregar(Persona persona) {
		// TODO Auto-generated method stub
		personal.add(persona);
		jtable.repaint();
		scrollPanel.repaint();
		//jtable.setModel(new JTablePersonaModel(personal));

	}

	public Persona personaSeleccionada() {
		int position = jtable.getSelectedRow();
		return (position == -1 ? null: personal.get(position));
	}

	public void eliminar(Persona persona) {
		// TODO Auto-generated method stub
		personal.remove(persona);
		jtable.repaint();
		scrollPanel.repaint();
	}

	public void actualizarPersonas() {
		// TODO Auto-generated method stub
		jtable.repaint();
		scrollPanel.repaint();
	}
 
	public void actualizarAsistencias(Persona persona ) {
		// TODO Auto-generated method stub
		this.persona = persona;
		this.asistencias = persona.getAsistencias();
		jPnPersonalAsistencia.setBorder(new TitledBorder("Listado de Asistencias de "+persona.getIdentidad()+" "+persona.getNombres()));
		jtableAsistencias.setModel(new JTableAsistenciaModel(asistencias));
	}

	public void actualizarAsistencias() {
		// TODO Auto-generated method stub
		jPnPersonalAsistencia.setBorder(new TitledBorder("Listado de Asistencias de "+persona.getIdentidad()+" "+persona.getNombres()));
		jtableAsistencias.setModel(new JTableAsistenciaModel(asistencias));
	}

	
	public JDatePanelImpl getpDesde() {
		return pDesde;
	}

	public void setpDesde(JDatePanelImpl pDesde) {
		this.pDesde = pDesde;
	}

	public JDatePickerImpl getdDesde() {
		return dDesde;
	}

	public void setdDesde(JDatePickerImpl dDesde) {
		this.dDesde = dDesde;
	}

	public void filtrarAsistencia() {
		// TODO Auto-generated method stub
		Date desde = (Date) dDesde.getModel().getValue();
		Date hasta = (Date) dHasta.getModel().getValue();
		asistencias = new ArrayList<Asistencia>();
		
		for(Asistencia asistencia:  persona.getAsistencias())
		{
			Date fecha = asistencia.getFecha();
			if(fecha.after(desde) && fecha.before(hasta))
			{  
				asistencias.add(asistencia);
			}
		}
		actualizarAsistencias();
	}
	
	
	

}
