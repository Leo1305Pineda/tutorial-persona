package com.personal.app.vista.componentes;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.personal.app.EditarModelo;
import com.personal.app.modelo.Persona;

public class JTablePersonaModel extends AbstractTableModel {

	private List<Persona> personal;
	private EditarModelo  editarModelo;
	private String[] columnas = {"ID","IDENTIDAD","NOMBRES"};
	

	public JTablePersonaModel(List<Persona> personal,EditarModelo  editarModelo) {
		super();
		this.personal = personal;
		this.editarModelo = editarModelo;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return personal.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnas[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0 )
		{
			return personal.get(rowIndex).getId();
		}
		else if(columnIndex == 1 )
		{
			return personal.get(rowIndex).getIdentidad();
		}
		else
		{
			return personal.get(rowIndex).getNombres();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		editarModelo.editar(aValue,rowIndex,columnIndex);
	}

}
