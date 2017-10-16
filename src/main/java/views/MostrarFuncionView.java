package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;
import javax.swing.JLabel;

public class MostrarFuncionView extends JDialog{
	
	private IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
	
	public MostrarFuncionView() {
		setTitle("Funcion obtenida");
		setBounds(100, 100, 266, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblLaFuncionObtenida = new JLabel("La funcion obtenida es:");
		lblLaFuncionObtenida.setBounds(68, 11, 134, 14);
		contentPanel.add(lblLaFuncionObtenida);

		String resultado = resolverSistemaEcuaciones(ingresarDatosController.getTableModel());
		
		JLabel label = new JLabel("");
		label.setText(resultado);
		label.setBounds(102, 36, 46, 14);
		contentPanel.add(label);
	}
	
	private String resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		int sum_xCuadrado = (int) tableModel.getValueAt(3,2);
		int sum_x = (int) tableModel.getValueAt(3,0);
		int sum_xPorY = (int) tableModel.getValueAt(3,3);
		int cantPuntos = 3;
		int sum_y = (int) tableModel.getValueAt(3,1);
		
		double[][] coeficientes = {{sum_xCuadrado, sum_x}, {sum_x, cantPuntos}};
		double[] terminosIndep = {sum_xPorY, sum_y};
		
		Matrix lhs = new Matrix(coeficientes);
	    Matrix rhs = new Matrix(terminosIndep, 2);
	    
	    Matrix ans = lhs.solve(rhs);
	    ingresarDatosController.setMatrizResultados(ans);
	    
        String resultado = Math.round(ans.get(0, 0)) + "*x + " + Math.round(ans.get(1, 0));
        
		return resultado;		
	}
}
