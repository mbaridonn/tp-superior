package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;
import model.MetodoMinimosCuadrados;
import model.RectaMinimosCuadrados;

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
		DefaultTableModel tableModelBase = ingresarDatosController.getTableModel();
		MetodoMinimosCuadrados metodoMinimosCuadrados = new RectaMinimosCuadrados();
		ingresarDatosController.setMetodoMinimosCuadrados(metodoMinimosCuadrados);
		
		Double[] puntosX = obtenerValoresEn(0, tableModelBase);
		Double[] puntosFx = obtenerValoresEn(1, tableModelBase);
		
		metodoMinimosCuadrados.generarCalculos(tableModelBase);
		metodoMinimosCuadrados.resolverSistemaEcuaciones(tableModelBase);
		
		Double[] imagenes = obtenerImagenes(metodoMinimosCuadrados, tableModelBase);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("x", puntosX);
		tableModel.addColumn("f(x)", puntosFx);
		tableModel.addColumn("Recta", imagenes);
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}
	
	private Double valorEnCelda(int fila, int columna,DefaultTableModel tableModel) {		
		return Double.parseDouble(tableModel.getValueAt(fila, columna).toString());
	};
	
	private Double redondear(double valor) {
		valor = valor * 100;
		valor = (double)((int) valor);
		valor = valor / 100;
		return new Double(valor);
	}
	
	private Double[] obtenerImagenes(MetodoMinimosCuadrados metodoMinimosCuadrados, DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount() - 1;
		Double[] imagenes = new Double[cantFilas];
		for(int fila = 0; fila < cantFilas; fila++) {
			double valor = metodoMinimosCuadrados.obtenerImagen(valorEnCelda(fila,0,tableModel));
			imagenes[fila] = redondear(valor);
		}
		return imagenes;
	}
	
	private Double[] obtenerValoresEn(int columna, DefaultTableModel tableModel) {
		int cantFilas = tableModel.getRowCount();
		Double[] valores = new Double[cantFilas];
		for(int fila = 0; fila < cantFilas; fila++) {
			valores[fila] = valorEnCelda(fila, columna, tableModel);
		}
		return valores;
	}

}
