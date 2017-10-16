package controllers;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;

public class IngresarDatosController {

	private static IngresarDatosController singleton = new IngresarDatosController();
	private DefaultTableModel tableModel;
	private Matrix resultados;
	
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;		
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public static IngresarDatosController getInstance() {
		return singleton;
	}

	public void setMatrizResultados(Matrix resultados) {
		this.resultados = resultados;		
	}
	
	public Matrix getMatrizResultados() {
		return resultados;
	}
	
}
