package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.IngresarDatosController;

public class RectaMinimosCuadradosView extends JDialog{
	
	private JPanel contentPanel;
	private JTable table;
	private JScrollPane scrollPane;
	private IngresarDatosController ingresarDatosController = new IngresarDatosController();

	public RectaMinimosCuadradosView() {
		setTitle("Recta Minimos Cuadrados");
		setBounds(100, 100, 266, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 230, 144);
		contentPanel.add(scrollPane);

		table = ingresarDatosController.obtenerTabla();
		scrollPane.setViewportView(table);
	}
}
