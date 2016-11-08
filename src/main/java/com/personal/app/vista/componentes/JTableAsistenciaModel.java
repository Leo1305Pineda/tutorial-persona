package com.personal.app.vista.componentes;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.personal.app.modelo.Asistencia;

public class JTableAsistenciaModel extends AbstractTableModel {

	private List<Asistencia> asistencias;
	private String[] columnas = {"ID","FECHA","ASISTENCIA","COMENTARIO"};

	public JTableAsistenciaModel(List<Asistencia> asistencias ) {
		super();
		this.asistencias = asistencias;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return asistencias.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
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
			return asistencias.get(rowIndex).getAsistencia();
		}
		else
		{
			return asistencias.get(rowIndex).getComentario();
		}
	}
}
