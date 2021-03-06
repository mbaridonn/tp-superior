package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;

public interface MetodoMinimosCuadrados {
	
	public void generarCalculos(DefaultTableModel tableModel);
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel);
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel);
	
	default Double[] obtenerSumatorias(DefaultTableModel tableModel) {
		int cantDecimales = IngresarDatosController.getInstance().getCantidadDecimales();
		Double sumatorias[] = new Double[tableModel.getColumnCount()];
		int cantFilas = tableModel.getRowCount();
		int cantColumnas = tableModel.getColumnCount();
		Double sumatoria;
		for(int j = 0; j < cantColumnas; j++) {
			sumatoria = 0.0;
			for(int i = 0; i < cantFilas; i++) {
				//Calcula la sumatoria de columna
				//sumatoria += Double.parseDouble(tableModel.getValueAt(i, j).toString());
				sumatoria += round(valorEnCelda(i, j, tableModel), cantDecimales);
			}
			sumatorias[j] = sumatoria;
		}
		return sumatorias;
	};
	
	default Double[] resultadosXElevadoAUnaPotencia(DefaultTableModel tableModel, int potencia) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 0;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			resultados[i] = (Double) Math.pow(valorDeX, potencia);
		}
		return resultados;
	};
	
	default double valorEnCelda(int fila, int columna,DefaultTableModel tableModel) {		
		return Double.parseDouble(tableModel.getValueAt(fila, columna).toString());
	};
	
	default Double[] resultadosXPorY(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 0;
		int indiceColumnaY = 1;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = valorDeX * valorDeY;
		}
		return resultados;
	}
	
	public double obtenerImagen(double entrada);
	
	default double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
