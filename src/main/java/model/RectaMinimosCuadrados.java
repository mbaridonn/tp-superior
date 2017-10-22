package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class RectaMinimosCuadrados implements MetodoMinimosCuadrados {
	
	@Override
	public String toString() {
		return "Recta Minimos Cuadrados";
	}

	private Integer[] obtenerSumatorias(DefaultTableModel tableModel) {
		Integer sumatorias[] = new Integer[tableModel.getColumnCount()];
		int cantFilas = tableModel.getRowCount();
		int cantColumnas = tableModel.getColumnCount();
		int sumatoria;
		for(int j = 0; j < cantColumnas; j++) {
			sumatoria = 0;
			for(int i = 0; i < cantFilas; i++) {
				sumatoria += Integer.parseInt(tableModel.getValueAt(i, j).toString());
			}
			sumatorias[j] = sumatoria;	
		}
		return sumatorias;
	}
	
	private Integer[] completarCuartaColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = Integer.parseInt(tableModel.getValueAt(i, 0).toString()) * Integer.parseInt(tableModel.getValueAt(i, 1).toString());
		}
		return data;
	}

	private Integer[] completarTercerColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = Integer.parseInt(tableModel.getValueAt(i, 0).toString()) * Integer.parseInt(tableModel.getValueAt(i, 0).toString());
		}
		return data;
	}
	
	public void generarCalculos(DefaultTableModel tableModel) {
		Integer dataTercerColumna[] = completarTercerColumna(tableModel);
		tableModel.addColumn("x^2", dataTercerColumna);
		Integer dataCuartaColumna[] = completarCuartaColumna(tableModel);
		tableModel.addColumn("x*y", dataCuartaColumna);
		tableModel.addRow(obtenerSumatorias(tableModel));
	}
	
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		int sum_x = (int) tableModel.getValueAt(cantPuntos, 0);
		int sum_y = (int) tableModel.getValueAt(cantPuntos, 1);
		int sum_xCuadrado = (int) tableModel.getValueAt(cantPuntos, 2);
		int sum_xPorY = (int) tableModel.getValueAt(cantPuntos, 3);
		
		double[][] coeficientes = {{sum_xCuadrado, sum_x}, {sum_x, cantPuntos}};
		double[] terminosIndep = {sum_xPorY, sum_y};
		
		Matrix lhs = new Matrix(coeficientes);
	    Matrix rhs = new Matrix(terminosIndep, 2);
	    
	    Matrix ans = lhs.solve(rhs);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(ans);
	    
        String resultado = Math.round(ans.get(0, 0)) + "*x + " + Math.round(ans.get(1, 0));
        
		return resultado;		
	}
}
