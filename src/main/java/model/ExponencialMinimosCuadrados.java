package model;

import java.math.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class ExponencialMinimosCuadrados implements MetodoMinimosCuadrados {

	@Override
	public String toString() {
		return "Exponencial Minimos Cuadrados";
	}
	
	@Override
	public void generarCalculos(DefaultTableModel tableModel) {
		tableModel.addColumn("Xi=xi", completarTerceraColumna(tableModel));
		tableModel.addColumn("Yi=ln yi", completarCuartaColumna(tableModel));
		tableModel.addColumn("Xi^2", resultadosXElevadoAUnaPotencia(tableModel,2));
		tableModel.addColumn("Xi*Yi", completarQuintaColumna(tableModel));
		tableModel.addRow(obtenerSumatorias(tableModel));
		
	}
	
	//
	private Double[] completarQuintaColumna(DefaultTableModel tableModel) {
		return resultadosYPorx(tableModel);
	}
	
	private Double[] resultadosYPorx(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 0; //0 xi, 1 yi, 2 Yi, 3 xi^2, 4 Yi * xi 
		int indiceColumnaY = 2;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = valorDeX * valorDeY;
		}
		return resultados;
	}
	
	//ln yi
	private Double[] completarCuartaColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaY = 1;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = Math.log(valorDeY);
		}
		return resultados;
	}

	//xi
	private Double[] completarTerceraColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 1;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			resultados[i] = valorDeX;
		}
		return resultados;
	}

	@Override
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		//0 xi, 1 yi, 2 Yi, 3 xi^2, 4 Yi * xi 
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		Double sum_Y = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 4);
		
									// a*sum(xi^2) + b*sum(xi) // a*sum(xi) + b*n 
		double[][] coeficientes = {{sum_xCuadrado, sum_x}, {sum_x, cantPuntos}};
						//= sum(1/yi * xi) //= sum(1/yi)
		double[] terminosIndep = {sum_xPorY, sum_Y};
									
									
		Matrix matrizCoeficientes = new Matrix(coeficientes);
	    Matrix matrizTerminosIndep = new Matrix(terminosIndep, 2);
	    
	    Matrix matrizResultados = matrizCoeficientes.solve(matrizTerminosIndep);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(matrizResultados);
	    
	    int cantDecimales = ingresarDatosContoller.getCantidadDecimales();
	    
        String resultado =  String.format("%."+cantDecimales+"f", matrizResultados.get(0, 0)) 
        					+ "e^ ( " 
        					+ String.format("%."+cantDecimales+"f", matrizResultados.get(1, 0))
        					+"x)";
        
		return resultado;		
	}

	@Override
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel) {
		String[] sistemasDeEcuaciones = new String[2];
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 4);
		
		String primeraEcuacion = sum_xCuadrado + " a " + "+ " + sum_x + " b " + "= " + sum_xPorY;
		String segundaEcuacion = sum_x + " a " + "+ " + cantPuntos + " b " + "= " + sum_y;
		
		sistemasDeEcuaciones[0] = primeraEcuacion;
		sistemasDeEcuaciones[1] = segundaEcuacion;
		
		return sistemasDeEcuaciones;
	}

	@Override
	public double obtenerImagen(double entrada) {
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		Matrix matrizResultados = ingresarDatosController.getMatrizResultados();
		double a = matrizResultados.get(0, 0);
		double b = matrizResultados.get(1, 0);
		return a * entrada + b;
	}

}
