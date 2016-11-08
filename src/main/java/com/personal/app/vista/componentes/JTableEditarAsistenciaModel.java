package com.personal.app.vista.componentes;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.personal.app.EditarModelo;
import com.personal.app.modelo.Asistencia;

public class JTableEditarAsistenciaModel extends AbstractTableModel {
	
	private List<Asistencia> asistencias;
	private String[] columnas = {"ID","FECHA","PERSONA","ASISTENCIA","COMENTARIO"};
	private EditarModelo  editarModelo;
	
	public JTableEditarAsistenciaModel(List<Asistencia> asistencias, EditarModelo  editarModelo) {
		super();
		this.asistencias = asistencias;
		this.editarModelo = editarModelo;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return asistencias.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
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
			return asistencias.get(rowIndex).getId();
		}
		else if(columnIndex == 1 )
		{ 	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-M-d"); 
			return  dateFormat.format(asistencias.get(rowIndex).getFecha());
		}
		else if(columnIndex == 2 )
		{
			return asistencias.get(rowIndex).getPersona().getFullNombre();
		}
		else if(columnIndex == 3 )
		{
			return asistencias.get(rowIndex).getAsistencia();
		}
		else
		{
			return asistencias.get(rowIndex).getComentario();
		}
	}
	
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 3) {
            return false;
        } else {
            return true;
        }
    }

    @Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

    	//System.out.println("gogogog  "+aValue);
		editarModelo.editar(aValue,rowIndex,columnIndex);
	}
    
    
}
