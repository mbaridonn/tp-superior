package views;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class EjemploGraficadorFunciones {
	
	private final static int numero = 1200;
	
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
			salidas[i] = Math.cos(entradas[i]);
		}
		return salidas;
	}

	public static void main(String[] args) {
		double[] entradas = generarEntradas();
		double[] salidas = generarSalidas(entradas);

		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", entradas, salidas);

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
