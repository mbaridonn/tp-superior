package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;

public class CalculoView extends JDialog {
	
	IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
	
	public CalculoView(){
		setTitle("Menu Resultados");
		setBounds(100, 100, 266, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 230, 144);
		contentPanel.add(scrollPane);

		DefaultTableModel tableModel = ingresarDatosController.getTableModel();
		
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		ingresarDatosController.setTableModel(tableModel);
	}
	
	
}
