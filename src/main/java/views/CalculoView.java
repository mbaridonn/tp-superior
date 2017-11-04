package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;
import javax.swing.JLabel;

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
		scrollPane.setBounds(10, 36, 230, 144);
		contentPanel.add(scrollPane);

		DefaultTableModel tableModel = ingresarDatosController.getTableModel();
		
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTablaDeSumatorias = new JLabel("Tabla de sumatorias:");
		lblTablaDeSumatorias.setBounds(10, 11, 230, 14);
		contentPanel.add(lblTablaDeSumatorias);
		
		JLabel lblSistemaDeEcuaciones = new JLabel("Sistema de ecuaciones:");
		lblSistemaDeEcuaciones.setBounds(10, 191, 230, 14);
		contentPanel.add(lblSistemaDeEcuaciones);
		
		int cantPuntos = tableModel.getRowCount() - 1;
		int sum_x = (int) tableModel.getValueAt(cantPuntos, 0);
		int sum_y = (int) tableModel.getValueAt(cantPuntos, 1);
		int sum_xCuadrado = (int) tableModel.getValueAt(cantPuntos, 2);
		int sum_xPorY = (int) tableModel.getValueAt(cantPuntos, 3);
		
		JLabel lblSistemaEcuaciones1 = new JLabel("a*"+sum_xCuadrado+" + b*"+sum_x+" = "+sum_xPorY);
		lblSistemaEcuaciones1.setBounds(10, 216, 230, 14);
		contentPanel.add(lblSistemaEcuaciones1);
		
		JLabel lblSistemaEcuaciones2 = new JLabel("a*"+sum_x+" + b*"+cantPuntos+" = "+sum_y);
		lblSistemaEcuaciones2.setBounds(10, 241, 230, 14);
		contentPanel.add(lblSistemaEcuaciones2);
		
		ingresarDatosController.setTableModel(tableModel);
	}
	
	
}
