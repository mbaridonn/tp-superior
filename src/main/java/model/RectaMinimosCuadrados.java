package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class RectaMinimosCuadrados implements MetodoMinimosCuadrados {
	
	@Override
	public String toString() {
		return "Recta Minimos Cuadrados";
	}

	//TODO: Extraer esta a una interfaz
	//dado una matriz, devuelve un array de sumatorias
	private Integer[] obtenerSumatorias(DefaultTableModel tableModel) {
		Integer sumatorias[] = new Integer[tableModel.getColumnCount()];
		int cantFilas = tableModel.getRowCount();
		int cantColumnas = tableModel.getColumnCount();
		int sumatoria;
		for(int j = 0; j < cantColumnas; j++) {
			sumatoria = 0;
			for(int i = 0; i < cantFilas; i++) {
				//Calcula la sumatoria de columna
				sumatoria += Integer.parseInt(tableModel.getValueAt(i, j).toString());
			}
			sumatorias[j] = sumatoria;	
		}
		return sumatorias;
	}
	
	//Calcula cada Xi*Yi
	private Integer[] completarCuartaColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = Integer.parseInt(tableModel.getValueAt(i, 0).toString()) * Integer.parseInt(tableModel.getValueAt(i, 1).toString());
		}
		return data;
	}

	//Calcula cada Xi^2
	private Integer[] completarTercerColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = Integer.parseInt(tableModel.getValueAt(i, 0).toString()) * Integer.parseInt(tableModel.getValueAt(i, 0).toString());
		}
		return data;
	}
	
	//completando la tabla con las nuevas columnas
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
		
								// a ...              +     b ...
		double[][] coeficientes = {{sum_xCuadrado, sum_x}, {sum_x, cantPuntos}};
		double[] terminosIndep = {sum_xPorY, sum_y};
		
		Matrix matrizCoeficientes = new Matrix(coeficientes);
	    Matrix matrizTerminosIndep = new Matrix(terminosIndep, 2);
	    
	    Matrix matrizResultados = matrizCoeficientes.solve(matrizTerminosIndep);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(matrizResultados);
	    
	    int cantDecimales = ingresarDatosContoller.getCantidadDecimales();
	    
        String resultado =  String.format("%."+cantDecimales+"f", matrizResultados.get(0, 0)) 
        					+ "*x + " 
        					+ String.format("%."+cantDecimales+"f", matrizResultados.get(1, 0));
        
		return resultado;		
	}
}
