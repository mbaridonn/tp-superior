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
		setTitle("Obtener Calculos");
		setBounds(800, 200, 650, 660);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 600, 150);
		contentPanel.add(scrollPane);

		DefaultTableModel tableModel = ingresarDatosController.getTableModel();
		
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTablaDeSumatorias = new JLabel("Tabla de sumatorias:");
		lblTablaDeSumatorias.setBounds(200, 800, 230, 14);
		contentPanel.add(lblTablaDeSumatorias);
		
		JLabel lblSistemaDeEcuaciones = new JLabel("Sistema de ecuaciones:");
		lblSistemaDeEcuaciones.setBounds(200, 800, 230, 14);
		contentPanel.add(lblSistemaDeEcuaciones);
		

		String[] sistemasDeEcuaciones = ingresarDatosController.getSistemasDeEcuaciones();
		int posYUltimaEcuacion = 216;
		for(int i = 0; i < sistemasDeEcuaciones.length; i++) {
			JLabel lblSistemaEcuaciones1 = new JLabel(sistemasDeEcuaciones[i]);
			lblSistemaEcuaciones1.setBounds(10, posYUltimaEcuacion, 420, 10);
			posYUltimaEcuacion += 30;
			contentPanel.add(lblSistemaEcuaciones1);
		}
	}
	
	
}
