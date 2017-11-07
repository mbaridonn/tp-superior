package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;

public class TablaAproxView extends JFrame{
	
	private final JPanel contentPanel;
	
	public TablaAproxView() {
		setTitle("Tabla Aproximaciones");
		setBounds(100, 100, 266, 282);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 230, 144);
		contentPanel.add(scrollPane);
		
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		DefaultTableModel model = ingresarDatosController.getTableModel();
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
	}

}
