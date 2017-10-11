package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;

public class IngresarDatosView extends JDialog {

	private JPanel contentPanel;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public IngresarDatosView() {
		setTitle("Ingresar Datos");
		setBounds(100, 100, 266, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 230, 144);
		contentPanel.add(scrollPane);

		String[] columnNames = { "x", "f(x)"};
		Integer[][] data = { { 1, 2 }, 
				{ 3, 4 }, 
				{ 5, 6 }};

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Aproximar mediante");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RectaMinimosCuadradosView rectaMinimosCuadradosView = new RectaMinimosCuadradosView();
				rectaMinimosCuadradosView.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 176, 230, 23);
		contentPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Graficar nube de puntos");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
				ingresarDatosController.setTable(table);
				ingresarDatosController.mostrarValores();
			}
		});
		btnNewButton_1.setBounds(10, 210, 230, 23);
		contentPanel.add(btnNewButton_1);
	}
}
