package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;
import model.ExponencialMinimosCuadrados;
import model.HiperbolaMinimosCuadrados;
import model.MetodoMinimosCuadrados;
import model.ParabolaMinimosCuadrados;
import model.PotencialMinimosCuadrados;
import model.RectaMinimosCuadrados;
import java.awt.Font;

public class IngresarDatosView extends JDialog {

	private JPanel contentPanel;
	private JTable table;

	public IngresarDatosView() {
		setTitle("Ingresar Datos");
		setBounds(300, 100, 650, 660);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 600, 300);
		contentPanel.add(scrollPane);

		String[] columnNames = { "x", "f(x)"};
		String[][] data = { { "1.0", "2.0" }, 
				{ "3.0", "4.0" }, 
				{ "5.0", "6.0" }};


		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if(todosLosCamposEstanLlenos()){
					model.addRow(new Object[]{"",""});
				}
				if(masDeUnaFilaEstaVacia()){
					int indiceFilaVacia = getPrimerFilaVacia();
					model.removeRow(indiceFilaVacia);
				}
			}
			private boolean todosLosCamposEstanLlenos(){
				for(int i = 0; i < table.getRowCount();i++){
					for(int j = 0; j < table.getColumnCount();j++){
						if(table.getValueAt(i, j).equals("")) return false;
					}
				}
				return true;
			}
			private boolean masDeUnaFilaEstaVacia(){
				int filasVacias = 0;
				for(int i = 0; i < table.getRowCount();i++){
					if(estaFilaEstaVacia(i)) filasVacias++;
				}
				return filasVacias > 1;
			}
			private boolean estaFilaEstaVacia(int indiceFila){
				for(int i = 0; i < table.getColumnCount();i++){
					if(!table.getValueAt(indiceFila, i).equals("")) return false;
				}
				return true;
			}
			
			private int getPrimerFilaVacia(){
				for(int i = 0; i < table.getRowCount();i++){
					if(estaFilaEstaVacia(i)) return i;
				}
				return 0;
			}
		});
		scrollPane.setViewportView(table);
		
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		
		JLabel lblDecimalesAUtilizar = new JLabel("Decimales a utilizar:");
		lblDecimalesAUtilizar.setBounds(10, 350, 300, 50);
		lblDecimalesAUtilizar.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(lblDecimalesAUtilizar);
		
		SpinnerNumberModel numberModel = new SpinnerNumberModel(2, 0, 9, 1);
		JSpinner spinner = new JSpinner(numberModel);
		spinner.setBounds(200, 360, 50, 30);
		spinner.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(spinner);
		
		JLabel lblSeleccioneMetodoDe = new JLabel("Seleccione un metodo de aproximación:");
		lblSeleccioneMetodoDe.setBounds(10, 390, 500, 50);
		lblSeleccioneMetodoDe.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(lblSeleccioneMetodoDe);
		
		JComboBox<MetodoMinimosCuadrados> comboBox = new JComboBox<MetodoMinimosCuadrados>();
		comboBox.addItem(new RectaMinimosCuadrados());
		comboBox.addItem(new ParabolaMinimosCuadrados());
		comboBox.addItem(new ExponencialMinimosCuadrados());
		comboBox.addItem(new PotencialMinimosCuadrados());
		comboBox.addItem(new HiperbolaMinimosCuadrados());
		comboBox.setBounds(10, 430, 600, 40);
		comboBox.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(comboBox);
		
		JButton btnAproximarMediante = new JButton("Aproximar");

		btnAproximarMediante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ingresarDatosController.setTableModel(model);
				ingresarDatosController.setCantidadDecimales((int) numberModel.getValue());
				ingresarDatosController.setMetodoMinimosCuadrados((MetodoMinimosCuadrados) comboBox.getSelectedItem());
				ingresarDatosController.generarCalculos();
				MenuResultadosView menuResultadosView = new MenuResultadosView();
				menuResultadosView.setVisible(true);
				setVisible(false);
			}
		});
		btnAproximarMediante.setBounds(100, 500, 400, 40);
		btnAproximarMediante.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnAproximarMediante);
		
		JButton btnCompararAproximaciones = new JButton("Comparar Aproximaciones");
		btnCompararAproximaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCompararAproximaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ingresarDatosController.setTableModel(model);
				ingresarDatosController.setCantidadDecimales((int) numberModel.getValue());
				TablaAproxView tablaAproxView = new TablaAproxView();
				tablaAproxView.setVisible(true);
				setVisible(false);
			}
		});
		btnCompararAproximaciones.setBounds(100, 551, 400, 40);
		contentPanel.add(btnCompararAproximaciones);
		
		/*JButton btnNewButton = new JButton("Comparar aproximaciones");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ingresarDatosController.setTableModel(model);
				ingresarDatosController.setCantidadDecimales((int) numberModel.getValue());
				TablaAproxView tablaAproxView = new TablaAproxView();
				tablaAproxView.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 297, 226, 25);
		contentPanel.add(btnNewButton);*/
	}
}
