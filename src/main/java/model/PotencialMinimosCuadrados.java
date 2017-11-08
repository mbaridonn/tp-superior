package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class PotencialMinimosCuadrados implements MetodoMinimosCuadrados {
	
	@Override
	public String toString() {
		return "Potencial Minimos Cuadrados";
	}

	@Override
	public void generarCalculos(DefaultTableModel tableModel) {
		tableModel.addColumn("Ln x", logaritmoNaturalXi(tableModel));
		tableModel.addColumn("Ln y", logaritmoNaturalYi(tableModel));
		Double dataQuintaColumna[] = completarQuintaColumna(tableModel);
		tableModel.addColumn("xi^2", dataQuintaColumna);	
		Double dataSextaColumna[] = completarSextaColumna(tableModel);
		tableModel.addColumn("Xi*Yi", dataSextaColumna);	
		tableModel.addRow(obtenerSumatorias(tableModel));		
	}
	
	private Double[] completarQuintaColumna(DefaultTableModel tableModel) {
		return logaritmoNaturalXi(tableModel);
	}
	
	
	private Double[] completarSextaColumna(DefaultTableModel tableModel) {
		DefaultTableModel tablaACompletar =new DefaultTableModel(); 	
		tablaACompletar.addColumn("Xi", logaritmoNaturalXi(tableModel));
		tablaACompletar.addColumn("Yi", logaritmoNaturalYi(tableModel));	
		return resultadosXPorY(tablaACompletar);
	}
	

	@Override
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		//Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 1);
		Double sum_lnx = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_lny = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_xPorY= (Double) tableModel.getValueAt(cantPuntos, 5);
		// Double sum_xCuadradoPorY = (Double) tableModel.getValueAt(cantPuntos, 6);
									/*b,a*/				/*b,a*/
		double[][] coeficientes = {{sum_xCuadrado, sum_lnx }, {sum_lnx, sum_x}};
		double[] terminosIndep = {sum_xPorY, sum_lny};
		
		Matrix matrizCoeficientes = new Matrix(coeficientes);
	    Matrix matrizTerminosIndep = new Matrix(terminosIndep, 2);
	    
	    Matrix matrizResultados = matrizCoeficientes.solve(matrizTerminosIndep);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(matrizResultados);
	    
	    int cantDecimales = ingresarDatosContoller.getCantidadDecimales();
	    
	    //double c = matrizResultados.get(0, 0);
	    double b = matrizResultados.get(0, 0);
	    double a = matrizResultados.get(1, 0);
	    
        String resultado =  String.format("%."+cantDecimales+"f", a) 
        					+ "(x^2) + " 
        					+ String.format("%."+cantDecimales+"f", b)
        					+ " x + ";
        					//+ String.format("%."+cantDecimales+"f", c);
        
		return resultado;
	}

	@Override
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel) {
		String[] sistemasDeEcuaciones = new String[3];
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_x = (Double) tableModel.getValueAt(cantPuntos, 0);
		// Double sum_y = (Double) tableModel.getValueAt(cantPuntos, 1);
		Double sum_lnx = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_lny = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_xCuadrado = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_xPorY = (Double) tableModel.getValueAt(cantPuntos, 5);
		//Double sum_xCuadradoPorY = (Double) tableModel.getValueAt(cantPuntos, 6);
		
		String primeraEcuacion = sum_xCuadrado + " b + " + sum_lnx + " a = " + sum_xPorY;
		String segundaEcuacion = sum_lnx + " b + " + sum_x + " a = " + sum_lny;
		//String terceraEcuacion = sum_lnx + " c + " + sum_lny + " b + " + sum_xCuadrado + " a = " + sum_xCuadradoPorY;
		
		sistemasDeEcuaciones[0] = primeraEcuacion;
		sistemasDeEcuaciones[1] = segundaEcuacion;
		//sistemasDeEcuaciones[2] = terceraEcuacion;
		
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
