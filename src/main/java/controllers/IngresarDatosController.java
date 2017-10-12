package controllers;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.RectaMinimosCuadrados;

public class IngresarDatosController {

	private static IngresarDatosController singleton = new IngresarDatosController();
	private JTable table;
	
	public static IngresarDatosController getInstance() {
		return singleton;
	}

	public void setTable(JTable table) {
		this.table = table;		
	}
	
	public void mostrarValores() {
		System.out.println("Valores de la tabla:");
		System.out.println(table.getModel().getValueAt(0, 0));
		System.out.println(table.getModel().getValueAt(1, 0));
		System.out.println(table.getModel().getValueAt(2, 0));
		System.out.println(table.getModel().getValueAt(0, 1));
		System.out.println(table.getModel().getValueAt(1, 1));
		System.out.println(table.getModel().getValueAt(2, 1));
	}
	
	public JTable obtenerTablaSegun(RectaMinimosCuadrados rectaMinimosCuadrados) { //tiene que ser una interfaz "MetodoCalculo"
		String[] columnNames = { "x", "f(x)"};
		Integer[][] data = { { 999, 999 }, 
				{ 999, 999 }, 
				{ 9999, 9999 }};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		return new JTable(model);
	}
	
}
