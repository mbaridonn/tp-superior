package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot2DPanel;

import controllers.IngresarDatosController;

public class GraficoView extends JFrame{
	
	private final static int numero = 1200;
	private static IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
	
	public static double[] generarEntradas(){
		double init = 0.0;
		double[] entradas = new double[numero];
		int i = 0;
		for(i = 0;i<numero;i++) {
			entradas[i] = init;
			init+=0.01;
		}
		return entradas;
	}

	public static double[] generarSalidas(double[] entradas) {
		double[] salidas = new double[numero];
		for(int i = 0; i < numero; i++) {
			salidas[i] = ingresarDatosController.obtenerImagen(entradas[i]);
		}
		return salidas;
	}
	
	public GraficoView() {
		setTitle("Grafico");
		setBounds(100, 100, 266, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		double[] entradas = generarEntradas();
		double[] salidas = generarSalidas(entradas);

		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Funcion", entradas, salidas);
		setContentPane(plot);
		/*
		 * Si quiero que arranque maximizado, usar:
		 * setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		 */
		setVisible(true);
	}

}
