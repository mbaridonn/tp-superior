package views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controllers.IngresarDatosController;

public class RectaMinimosCuadradosView extends JDialog{
	
	private JPanel contentPanel;
	private JTable table;
	private JScrollPane scrollPane;
	private IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();

	public RectaMinimosCuadradosView() {
		setTitle("Recta Minimos Cuadrados");
		setBounds(100, 100, 266, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Mostrar funcion");
		btnNewButton.setBounds(12, 13, 224, 25);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obtener calculo");
		btnNewButton_1.setBounds(12, 51, 224, 25);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Graficar");
		btnNewButton_2.setBounds(12, 89, 224, 25);
		contentPanel.add(btnNewButton_2);
		
	}
}
