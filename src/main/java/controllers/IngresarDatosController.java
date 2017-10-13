package controllers;

import javax.swing.table.DefaultTableModel;

public class IngresarDatosController {

	private static IngresarDatosController singleton = new IngresarDatosController();
	DefaultTableModel tableModel;
	
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;		
	}
	
	public DefaultTableModel getModel() {
		return tableModel;
	}

	public static IngresarDatosController getInstance() {
		return singleton;
	}
	
	
}
