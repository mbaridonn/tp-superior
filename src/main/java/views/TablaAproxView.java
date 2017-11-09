package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;
import model.MetodoMinimosCuadrados;
import model.ParabolaMinimosCuadrados;
import model.RectaMinimosCuadrados;

public class TablaAproxView extends JFrame{
	
	private final JPanel contentPanel;
	
	public TablaAproxView() {
		setTitle("Tabla Aproximaciones");
		setBounds(100, 100, 358, 335);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 322, 144);
		contentPanel.add(scrollPane);
		
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		DefaultTableModel tableModelBase = ingresarDatosController.getTableModel();

		DefaultTableModel tableModelCreada = new DefaultTableModel();
		Double[] puntosX = obtenerValoresEn(0, tableModelBase);
		tableModelCreada.addColumn("x", puntosX);
		Double[] puntosFx = obtenerValoresEn(1, tableModelBase);
		tableModelCreada.addColumn("f(x)", puntosFx);

		MetodoMinimosCuadrados rectaMinimosCuadrados = new RectaMinimosCuadrados();
		MetodoMinimosCuadrados parabolaMinimosCuadrados = new ParabolaMinimosCuadrados();
		
		agregarColumnaImagen(tableModelCreada, rectaMinimosCuadrados);
		agregarColumnaImagen(tableModelCreada, parabolaMinimosCuadrados);
		
		agregarColumnasError(tableModelCreada, rectaMinimosCuadrados, 2);
		agregarColumnasError(tableModelCreada, parabolaMinimosCuadrados, 3);
		
		JTable table = new JTable(tableModelCreada);
		scrollPane.setViewportView(table);
		
		String nombreMetodoMasAproximante = metodoMasAproximante().toString();
		JLabel lblElMetodoQue = new JLabel("El metodo que mas se aproxima es: " + nombreMetodoMasAproximante);
		lblElMetodoQue.setBounds(10, 213, 322, 14);
		contentPanel.add(lblElMetodoQue);
	}
	
	private MetodoMinimosCuadrados metodoMasAproximante() {
		//TERMINAR
		MetodoMinimosCuadrados metodo = new RectaMinimosCuadrados();
		return metodo;
	}
	
	private void agregarColumnaImagen(DefaultTableModel tableModelCreada, MetodoMinimosCuadrados metodoMinimosCuadrados) {
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		DefaultTableModel tableModelBase = ingresarDatosController.getTableModel();
		DefaultTableModel tableModelCopia = clonar(tableModelBase);
		ingresarDatosController.setMetodoMinimosCuadrados(metodoMinimosCuadrados);
		metodoMinimosCuadrados.generarCalculos(tableModelCopia);
		metodoMinimosCuadrados.resolverSistemaEcuaciones(tableModelCopia);
		Double[] imagenes = obtenerImagenes(metodoMinimosCuadrados, tableModelCopia);
		tableModelCreada.addColumn(metodoMinimosCuadrados.toString(), imagenes);
	}
	
	private void agregarColumnasError(DefaultTableModel tableModel, MetodoMinimosCuadrados metodoMinimosCuadrados, int columna) {
		Double[] errores = obtenerErrores(tableModel, columna);
		tableModel.addColumn("ERROR " + metodoMinimosCuadrados.toString(), errores);
	}
	
	private Double[] obtenerErrores(DefaultTableModel tableModel, int columna) {
		int cantFilas = tableModel.getRowCount();
		Double[] errores = new Double[cantFilas];
		for(int fila = 0; fila < cantFilas; fila++) {
			errores[fila] = valorEnCelda(fila, 2, tableModel) - valorEnCelda(fila, columna, tableModel);
		}
		return errores;
	}
	
	private DefaultTableModel clonar(DefaultTableModel tableModelBase) {
		int cantFilas = tableModelBase.getRowCount();
		int cantColumnas = tableModelBase.getColumnCount();
		final DefaultTableModel tableModelCopia = new DefaultTableModel(tableModelBase.getRowCount(), 0);
		for(int columna = 0; columna < cantColumnas; columna++) {
			tableModelCopia.addColumn(tableModelBase.getColumnName(columna));
			for(int fila = 0; fila < cantFilas; fila++) {
				tableModelCopia.setValueAt(tableModelBase.getValueAt(fila, columna), fila, columna);
			}
		}
		return tableModelCopia;
	}

	private Double valorEnCelda(int fila, int columna,DefaultTableModel tableModel) {		
		return Double.parseDouble(tableModel.getValueAt(fila, columna).toString());
	};
	
	private Double redondear(double valor) {
		valor = valor * 100;
		valor = (int) valor;
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
