package com.personal.app.vista;

import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.personal.app.App;
import com.personal.app.controlador.AsistenciaControlador;
import com.personal.app.modelo.Asistencia;
import com.personal.app.modelo.Persona;
import com.personal.app.vista.componentes.JTableEditarAsistenciaModel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
 

public class AsistenciaUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable jtable;
	private JScrollPane scrollPanel;
	private JPanel jPnPersonal;
	
	private List<Persona> personal;
	private List<Asistencia> asistencias;

	private AsistenciaControlador controlador;

	private UtilDateModel   mFecha;
	private JDatePanelImpl  pFecha;
	private JDatePickerImpl dFecha;
	private Date fecha;
	private JButton generar;

	
	public AsistenciaUI(List<Persona> personal, List<Asistencia> asistencias, AsistenciaControlador controlador, Date fecha ) {
		super(new MigLayout());
		this.personal = personal;
		this.controlador = controlador;
		this.fecha = fecha;
		this.asistencias = asistencias;
		
		jtable = new JTable(new JTableEditarAsistenciaModel(asistencias,controlador.accionEditar()));
		scrollPanel= new JScrollPane(jtable);
		jtable.setPreferredScrollableViewportSize(new Dimension(App.getWidthPantalla(),App.getHeightPantalla()));
		jtable.setFillsViewportHeight(true);

		JComboBox comboBox = new JComboBox();
        comboBox.addItem("ASISTIO");
	    comboBox.addItem("NOASISTIO");
	    jtable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    jtable.getColumnModel().getColumn(3).setCellRenderer(renderer);
	    
	    jtable.getColumnModel().getColumn(0).setMaxWidth(100);
	    jtable.getColumnModel().getColumn(1).setMaxWidth(150);
	    jtable.getColumnModel().getColumn(2).setMaxWidth(150);
	    jtable.getColumnModel().getColumn(3).setMaxWidth(300);
	    
		jPnPersonal = new JPanel(new MigLayout());
		jPnPersonal.setBorder(new TitledBorder("Listado de Asistencias "));
		jPnPersonal.add(scrollPanel);

		mFecha = new UtilDateModel(this.fecha);
		pFecha = new JDatePanelImpl(mFecha);
		dFecha = new JDatePickerImpl(pFecha);

		generar = new JButton("Generar");
		generar.addActionListener(controlador.accionGenerarAsistencia());
		
		add(new JLabel("Fecha"));		
		add(dFecha);		
		add(generar,"wrap");
		add(jPnPersonal,"span");		
	}

	public List<Persona> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Persona> personal) {
		this.personal = personal;
	}
	

	public JDatePickerImpl getdFecha() {
		return dFecha;
	}

	public void setdFecha(JDatePickerImpl dFecha) {
		this.dFecha = dFecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	} 

	public Asistencia asistenciaSeleccionada() {
		int position = jtable.getSelectedRow();
		return (position == -1 ? null: asistencias.get(position));
	}
	
	public void actualizarTabla() {
		// TODO Auto-generated method stub
		scrollPanel.repaint();
		jtable.repaint();
	}

	public void actualizarAsistencias(List<Asistencia> asistencias) {
		// TODO Auto-generated method stub
		this.asistencias = asistencias;
		jtable.setModel(new JTableEditarAsistenciaModel(asistencias,controlador.accionEditar()));

		JComboBox comboBox = new JComboBox();
        comboBox.addItem("ASISTIO");
	    comboBox.addItem("NOASISTIO");
	    jtable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    jtable.getColumnModel().getColumn(3).setCellRenderer(renderer);
	    
	    jtable.getColumnModel().getColumn(0).setMaxWidth(100);
	    jtable.getColumnModel().getColumn(1).setMaxWidth(150);
	    jtable.getColumnModel().getColumn(2).setMaxWidth(150);
	    jtable.getColumnModel().getColumn(3).setMaxWidth(300);
	}
  
}
