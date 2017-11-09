package model;

import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class PotencialMinimosCuadrados implements MetodoMinimosCuadrados {
	
	@Override
	public String toString() {
		return "Potencial Minimos Cuadrados";
	}

	
	// 0. xi 1. yi 2. X = ln(xi) 3. Y = ln(yi) 4.Xi^2 5.Xi*Yi
	
	//columna 2. Xi = ln(xi)
	private Double[] completarSegundaColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 0;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			resultados[i] = (Double) Math.log(valorDeX);
		}
		return resultados;
	}
	
	//columna 3. Yi = ln(yi)
	private Double[] logaritmoNaturalYi(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaY = 1;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = (Double) Math.log(valorDeY);
		}
		return resultados;
	};	
	
	//columna 4. Xi^2
	private Double[] XAlCuadrado(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 2; // 2. Xi = ln(xi)
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			resultados[i] = (Double) Math.pow(valorDeX, 2);
		}
		return resultados;
	};
	
	//columna 5. Xi*Yi
	private Double[] resultadosYPorx(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		int indiceColumnaX = 2; // 0. xi 1. yi 2. X = ln(xi) 3. Y = ln(yi) 4.Xi^2 5.Xi*Yi
		int indiceColumnaY = 3;
		Double resultados[] = new Double[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			double valorDeX = valorEnCelda(i,indiceColumnaX,tableModel);
			double valorDeY = valorEnCelda(i,indiceColumnaY,tableModel);
			resultados[i] = valorDeX * valorDeY;
		}
		return resultados;
	}
	
	// 0. xi 1. yi 2. X = ln(xi) 3. Y = ln(yi) 4.Xi^2 5.Xi*Yi
	@Override
	public void generarCalculos(DefaultTableModel tableModel) {
		Double dataSegundaColumna[] = completarSegundaColumna(tableModel);
		tableModel.addColumn("X = ln(xi)", dataSegundaColumna);
		Double dataTerceraColumna[] = logaritmoNaturalYi(tableModel);
		tableModel.addColumn("Y = ln(yi)", dataTerceraColumna);
		Double dataCuartaColumna[] = XAlCuadrado(tableModel);
		tableModel.addColumn("Xi^2", dataCuartaColumna);
		Double dataQuintaColumna[] = resultadosYPorx(tableModel);
		tableModel.addColumn("Xi*Yi", dataQuintaColumna);
		tableModel.addRow(obtenerSumatorias(tableModel));
		
	}

	@Override
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int cantPuntos = tableModel.getRowCount() - 1;
		// 0. xi 1. yi 2. X = ln(xi) 3. Y = ln(yi) 4.Xi^2 5.Xi*Yi
		Double sum_X = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_Y = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_XCuadrado = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_XPorY = (Double) tableModel.getValueAt(cantPuntos, 5);
		
									// a*sum(Xi^2) + b*sum(Xi) // a*sum(Xi) + b*n 
		double[][] coeficientes = {{sum_XCuadrado, sum_X}, {sum_X, cantPuntos}};
						//= sum(Xi*Yi) //= sum(Yi)
		double[] terminosIndep = {sum_XPorY, sum_Y};
									
									
		Matrix matrizCoeficientes = new Matrix(coeficientes);
	    Matrix matrizTerminosIndep = new Matrix(terminosIndep, 2);
	    
	    Matrix matrizResultados = matrizCoeficientes.solve(matrizTerminosIndep);
	    IngresarDatosController ingresarDatosContoller = IngresarDatosController.getInstance();
	    ingresarDatosContoller.setMatrizResultados(matrizResultados);
	    
	    int cantDecimales = ingresarDatosContoller.getCantidadDecimales();
	    
	    double a = matrizResultados.get(0, 0);
		double b = matrizResultados.get(1, 0);
		double B = (Double) Math.pow(Math.E,b);
	    
        String resultado =  String.format("%."+cantDecimales+"f", a) 
        					+ "*x + " 
        					+ String.format("%."+cantDecimales+"f", B);
        
		return resultado;
	}

	// 0. xi 1. yi 2. X = ln(xi) 3. Y = ln(yi) 4.Xi^2 5.Xi*Yi
	@Override
	public String[] sistemasDeEcuaciones(DefaultTableModel tableModel) {
		String[] sistemasDeEcuaciones = new String[2];
		int cantPuntos = tableModel.getRowCount() - 1;
		Double sum_X = (Double) tableModel.getValueAt(cantPuntos, 2);
		Double sum_Y = (Double) tableModel.getValueAt(cantPuntos, 3);
		Double sum_XCuadrado = (Double) tableModel.getValueAt(cantPuntos, 4);
		Double sum_XPorY = (Double) tableModel.getValueAt(cantPuntos, 5);
										// a*sum(Xi^2) + b*sum(Xi) = sum(Xi*Yi) 
		String primeraEcuacion = sum_XCuadrado + " a " + "+ " + sum_X + " b " + "= " + sum_XPorY;
										// a*sum(Xi) + b*n  = sum(Yi)
		String segundaEcuacion = sum_X + " a " + "+ " + cantPuntos + " b " + "= " + sum_Y;
		
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
		double B = (Double) Math.pow(Math.E,b);
		return a * entrada + B;
	}

}
