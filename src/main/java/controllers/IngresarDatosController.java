package controllers;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import model.MetodoMinimosCuadrados;
import model.RectaMinimosCuadrados;

public class IngresarDatosController {

	private MetodoMinimosCuadrados metodoMinimosCuadrados;
	private static IngresarDatosController singleton = new IngresarDatosController();
	private DefaultTableModel tableModel;
	private Matrix resultados;
	private int cantidadDecimales;
	
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
		resolverSistemaEcuaciones();
	}
	
	public String resolverSistemaEcuaciones() {
		return metodoMinimosCuadrados.resolverSistemaEcuaciones(tableModel);
	}

	public void setMetodoMinimosCuadrados(MetodoMinimosCuadrados metodoMinimosCuadrados) {
		this.metodoMinimosCuadrados = metodoMinimosCuadrados;
	}

	public void setCantidadDecimales(int value) {
		cantidadDecimales = value;		
	}
	
	public int getCantidadDecimales() {
		return cantidadDecimales;
	}

	public String[] getSistemasDeEcuaciones() {
//		String[] sistemasDeEcuaciones = metodoMinimosCuadrados.sistemasDeEcuaciones(tableModel);
//		int cantidadDeEcuaciones = sistemasDeEcuaciones.length;
//		String ecuacionesText = "";
//		for(int i = 0 ; i < cantidadDeEcuaciones;i++) {
//			ecuacionesText += sistemasDeEcuaciones[i];
//			if(i != cantidadDeEcuaciones -1) ecuacionesText += "\n\n"; // Best practices ever
//		}
		return metodoMinimosCuadrados.sistemasDeEcuaciones(tableModel);
	}

	public double obtenerImagen(double entrada) {
		return metodoMinimosCuadrados.obtenerImagen(entrada);
	}

	public void setTableModelResultados(DefaultTableModel model) {
		setTableModel(model);
		Double[] data = {1.0, 2.0, 3.0};
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		ingresarDatosController.setMetodoMinimosCuadrados(new RectaMinimosCuadrados());
		ingresarDatosController.generarCalculos();
		ingresarDatosController.resolverSistemaEcuaciones();
		model.addColumn("Recta", data);
		setTableModel(model);
	}
	
}
