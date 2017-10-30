package controllers;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import model.MetodoMinimosCuadrados;

public class IngresarDatosController {

	MetodoMinimosCuadrados metodoMinimosCuadrados;
	private static IngresarDatosController singleton = new IngresarDatosController();
	private DefaultTableModel tableModel;
	private Matrix resultados;
	
	public void setTableModel(DefaultTableModel tableModel) {
		tableModel.removeRow(tableModel.getRowCount()-1);
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
	
	public void generarCalculos() {
		metodoMinimosCuadrados.generarCalculos(tableModel);
	}
	
	public String resolverSistemaEcuaciones() {
		return metodoMinimosCuadrados.resolverSistemaEcuaciones(tableModel);
	}

	public void setMetodoMinimosCuadrados(MetodoMinimosCuadrados metodoMinimosCuadrados) {
		this.metodoMinimosCuadrados = metodoMinimosCuadrados;
	}
}
