package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class ParabolaMinimosCuadrados implements MetodoMinimosCuadrados{
	
	@Override
	public String toString() {
		return "Parabola Minimos Cuadrados";
	}

	@Override
	public void generarCalculos(DefaultTableModel tableModel) {
		tableModel.addColumn("x^2", resultadosXElevadoAUnaPotencia(tableModel,2));
		tableModel.addColumn("x^3", resultadosXElevadoAUnaPotencia(tableModel,3));
		tableModel.addColumn("x^4", resultadosXElevadoAUnaPotencia(tableModel,4));
		tableModel.addColumn("x*y", resultadosXPorY(tableModel));
		tableModel.addColumn("(x^2)*y", resultadosXAlCuadradoPorY(tableModel));
		tableModel.addRow(obtenerSumatorias(tableModel));
	}

	@Override
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 1);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_xCubo = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xCuarta = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 5);
		Double sum_xCuadradoPorY = (Double) tableModel.getValueAt(cantPuntos, 6);
									/*c,b,a*/				/*c,b,a*/
		double[][] coeficientes = {{cantPuntos, sum_x, sum_xCuadrado}, {sum_x, sum_xCuadrado,sum_xCubo},{sum_xCuadrado,sum_xCubo,sum_xCuarta}};
		double[] terminosIndep = {sum_y, sum_xPorY,sum_xCuadradoPorY};
		
		Matrix matrizCoeficientes = new Matrix(coeficientes);
	    Matrix matrizTerminosIndep = new Matrix(terminosIndep, 3);
	    
	    Matrix matrizResultados = matrizCoeficientes.solve(matrizTerminosIndep);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(matrizResultados);
	    
	    int cantDecimales = ingresarDatosContoller.getCantidadDecimales();
	    
	    double c = matrizResultados.get(0, 0);
	    double b = matrizResultados.get(1, 0);
	    double a = matrizResultados.get(2, 0);
	    
        String resultado =  String.format("%."+cantDecimales+"f", a) 
        					+ "(x^2) + " 
        					+ String.format("%."+cantDecimales+"f", b)
        					+ " x + "
        					+ String.format("%."+cantDecimales+"f", c);
        
		return resultado;
	}
	
	private Double[] resultadosXAlCuadradoPorY(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 0;
		int indiceColumnaY = 1;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			Double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			Double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = valorDeX * valorDeX * valorDeY;
		}
		return resultados;
	}

	@Override
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel) {
		String[] sistemasDeEcuaciones = new String[3];
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 1);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_xCubo = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xCuarta = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 5);
		Double sum_xCuadradoPorY = (Double) tableModel.getValueAt(cantPuntos, 6);
		
		String primeraEcuacion = cantPuntos + " c + " + sum_x + " b + " + sum_xCuadrado + " a = " + sum_y;
		String segundaEcuacion = sum_x + " c + " + sum_xCuadrado + " b + " + sum_xCubo + " a = " + sum_xPorY;
		String terceraEcuacion = sum_xCuadrado + " c + " + sum_xCubo + " b + " + sum_xCuarta + " a = " + sum_xCuadradoPorY;
		
		sistemasDeEcuaciones[0] = primeraEcuacion;
		sistemasDeEcuaciones[1] = segundaEcuacion;
		sistemasDeEcuaciones[2] = terceraEcuacion;
		
		return sistemasDeEcuaciones;
	}

	@Override
	public double obtenerImagen(double entrada) {
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		Matrix matrizResultados = ingresarDatosController.getMatrizResultados();
		double c = matrizResultados.get(0, 0);
	    double b = matrizResultados.get(1, 0);
	    double a = matrizResultados.get(2, 0);
		return c*Math.pow(entrada, 2) + b*entrada + a;
	}
}
