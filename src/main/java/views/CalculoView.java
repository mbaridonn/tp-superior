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
		Integer dataTercerColumna[] = completarTercerColumna(tableModel);
		tableModel.addColumn("x^2", dataTercerColumna);
		Integer dataCuartaColumna[] = completarCuartaColumna(tableModel);
		tableModel.addColumn("x*y", dataCuartaColumna);
		tableModel.addRow(obtenerSumatorias(tableModel));
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		ingresarDatosController.setTableModel(tableModel);
	}
	
	private Integer[] obtenerSumatorias(DefaultTableModel tableModel) {
		Integer sumatorias[] = new Integer[tableModel.getColumnCount()];
		int cantFilas = tableModel.getRowCount();
		int cantColumnas = tableModel.getColumnCount();
		int sumatoria;
		for(int j = 0; j < cantColumnas; j++) {
			sumatoria = 0;
			for(int i = 0; i < cantFilas; i++) {
				sumatoria += (Integer) tableModel.getValueAt(i, j);
			}
			sumatorias[j] = sumatoria;	
		}
		return sumatorias;
	}
	
	private Integer[] completarCuartaColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = (Integer) tableModel.getValueAt(i, 0) * (Integer) tableModel.getValueAt(i, 1);
		}
		return data;
	}

	private Integer[] completarTercerColumna(DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Integer data[] = new Integer[cantFilas];
		for(int i = 0; i < cantFilas; i++ ) {
			data[i] = (Integer) tableModel.getValueAt(i, 0) * (Integer) tableModel.getValueAt(i, 0);
		}
		return data;
	}
}
