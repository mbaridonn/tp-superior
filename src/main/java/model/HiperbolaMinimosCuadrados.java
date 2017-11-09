package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class HiperbolaMinimosCuadrados implements MetodoMinimosCuadrados{

	@Override
	public String toString() {
		return "Hiperbola Minimos Cuadrados";
	}
	
	//columna xi
	
	//Calcula cada Yi=1/yi
	private Double[] completarTerceraColumna(DefaultTableModel tableModel) {
			return resultadosUnoSobreY(tableModel);
		}
		
	private Double[] resultadosUnoSobreY(DefaultTableModel tableModel) {
			int cantFilas = tableModel.getRowCount();
			int indiceColumnaY = 1;
			Double resultados[] = new Double[cantFilas];
			for(int i = 0; i < cantFilas; i++ ) {
				double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
				resultados[i] = 1 / valorDeY;
			}
			return resultados;
		}
	
	//columna xi^2
	private Double[] completarCuartaColumna(DefaultTableModel tableModel) {
		return resultadosXElevadoAUnaPotencia(tableModel,2);
	}

	//columna Yi*xi = 1/yi * xi
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
	
	//completando la tabla con las nuevas columnas
	@Override
	public void generarCalculos(DefaultTableModel tableModel) {
		Double dataTercerColumna[] = completarTerceraColumna(tableModel);
		tableModel.addColumn("Yi=1/yi", dataTercerColumna);
		Double dataCuartaColumna[] = completarCuartaColumna(tableModel);
		tableModel.addColumn("xi^2", dataCuartaColumna);
		Double dataQuintaColumna[] = completarQuintaColumna(tableModel);
		tableModel.addColumn("Yi*xi", dataQuintaColumna);
		tableModel.addRow(obtenerSumatorias(tableModel));
		
	}
	
	
	//obtengo a y b
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
        					+ "*x + " 
        					+ String.format("%."+cantDecimales+"f", matrizResultados.get(1, 0));
        
		return resultado;		
	}

	
	//Y = AX + B 
	//X = xi, Y = 1/yi
	//A = 1/a
	//B = b/a
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
