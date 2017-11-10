package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot2DPanel;

import controllers.IngresarDatosController;

public class GraficoView extends JFrame{
	
	private final static int numero = 1200;
	private static IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
	
	
	public GraficoView() {
		setTitle("Grafico");
		setBounds(300, 200, 650, 660);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		double[] entradas = ingresarDatosController.generarEntradas();
		double[] salidas = ingresarDatosController.generarSalidas(entradas);

		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Funcion", entradas, salidas);
		setContentPane(plot);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		setVisible(true);
	}

}
