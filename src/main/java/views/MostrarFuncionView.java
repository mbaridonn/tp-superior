package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.IngresarDatosController;
import javax.swing.JLabel;

public class MostrarFuncionView extends JDialog{
	
	private IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
	
	public MostrarFuncionView() {
		setTitle("Funcion obtenida");
		setBounds(100, 100, 266, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblLaFuncionObtenida = new JLabel("La funcion obtenida es:");
		lblLaFuncionObtenida.setBounds(68, 11, 134, 14);
		contentPanel.add(lblLaFuncionObtenida);

		String resultado = ingresarDatosController.resolverSistemaEcuaciones();
		
		JLabel label = new JLabel("");
		label.setText(resultado);
		label.setBounds(10, 36, 230, 14);
		contentPanel.add(label);
	}
	
	
}
