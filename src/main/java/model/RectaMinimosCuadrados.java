package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class RectaMinimosCuadrados implements MetodoMinimosCuadrados {
	
	@Override
	public String toString() {
		return "Recta Minimos Cuadrados";
	}
	
	//Calcula cada Xi*Yi
	private Double[] completarCuartaColumna(DefaultTableModel tableModel) {
		return resultadosXPorY(tableModel);
	}

	//Calcula cada Xi^2
	private Double[] completarTercerColumna(DefaultTableModel tableModel) {
		return resultadosXElevadoAUnaPotencia(tableModel,2);
	}
	
	//completando la tabla con las nuevas columnas
	public void generarCalculos(DefaultTableModel tableModel) {
		Double dataTercerColumna[] = completarTercerColumna(tableModel);
		tableModel.addColumn("x^2", dataTercerColumna);
		Double dataCuartaColumna[] = completarCuartaColumna(tableModel);
		tableModel.addColumn("x*y", dataCuartaColumna);
		tableModel.addRow(obtenerSumatorias(tableModel));
	}
	
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 1);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 3);
		
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

	@Override
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel) {
		String[] sistemasDeEcuaciones = new String[1];
		return sistemasDeEcuaciones;
	}
}
