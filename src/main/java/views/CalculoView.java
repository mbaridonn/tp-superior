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
		setBounds(700, 300, 650, 500);
		setFont(new java.awt.Font("Tahoma", 0, 20)); 
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 600, 200);
		contentPanel.add(scrollPane);

		DefaultTableModel tableModel = ingresarDatosController.getTableModel();
		
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTablaDeSumatorias = new JLabel("Tabla de sumatorias:");
		lblTablaDeSumatorias.setBounds(10, 10, 230, 30);
		lblTablaDeSumatorias.setFont(new java.awt.Font("Tahoma", 0, 20)); 
		contentPanel.add(lblTablaDeSumatorias);
		
		JLabel lblnota = new JLabel("* La ¨²ltima fila es la sumatoria de cada columna.");
		lblnota.setBounds(10, 250, 550, 20);
		lblnota.setFont(new java.awt.Font("Tahoma", 0, 20)); 
		contentPanel.add(lblnota);
		
		JLabel lblSistemaDeEcuaciones = new JLabel("Sistema de ecuaciones:");
		lblSistemaDeEcuaciones.setBounds(10, 290, 550, 20);
		lblSistemaDeEcuaciones.setFont(new java.awt.Font("Tahoma", 0, 20)); 
		contentPanel.add(lblSistemaDeEcuaciones);
		

		String[] sistemasDeEcuaciones = ingresarDatosController.getSistemasDeEcuaciones();
		int posYUltimaEcuacion = 310;
		for(int i = 0; i < sistemasDeEcuaciones.length; i++) {
			JLabel lblSistemaEcuaciones1 = new JLabel(sistemasDeEcuaciones[i]);
			lblSistemaEcuaciones1.setBounds(50, posYUltimaEcuacion, 420, 50);
			lblSistemaEcuaciones1.setFont(new java.awt.Font("Tahoma", 0, 20));
			posYUltimaEcuacion += 30;
			contentPanel.add(lblSistemaEcuaciones1);
		}
	}
	
	
}
