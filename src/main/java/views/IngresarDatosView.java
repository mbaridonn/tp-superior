package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;
import model.MetodoMinimosCuadrados;
import model.RectaMinimosCuadrados;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class IngresarDatosView extends JDialog {

	private JPanel contentPanel;
	private JTable table;

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
		String[][] data = { { "1", "2" }, 
				{ "3", "4" }, 
				{ "5", "6" }};

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		
		JLabel lblSeleccioneMetodoDe = new JLabel("Seleccione un metodo de aproximacion");
		lblSeleccioneMetodoDe.setBounds(10, 171, 230, 14);
		contentPanel.add(lblSeleccioneMetodoDe);
		
		JComboBox<MetodoMinimosCuadrados> comboBox = new JComboBox<MetodoMinimosCuadrados>();
		comboBox.addItem(new RectaMinimosCuadrados());
		comboBox.setBounds(10, 196, 230, 20);
		contentPanel.add(comboBox);
		
		JButton btnAproximarMediante = new JButton("Aproximar mediante");
		btnAproximarMediante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ingresarDatosController.setTableModel(model);
				ingresarDatosController.setMetodoMinimosCuadrados((MetodoMinimosCuadrados) comboBox.getSelectedItem());
				ingresarDatosController.generarCalculos();
				MenuResultadosView menuResultadosView = new MenuResultadosView();
				menuResultadosView.setVisible(true);
				setVisible(false);
			}
		});
		btnAproximarMediante.setBounds(10, 227, 230, 23);
		contentPanel.add(btnAproximarMediante);
		
	}
}
