package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Jama.Matrix;
import controllers.IngresarDatosController;

public class MostrarFuncionView extends JDialog{
	
	public MostrarFuncionView() {
		setTitle("Funcion obtenida");
		setBounds(100, 100, 266, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		IngresarDatosController ingresarDatosController = IngresarDatosController.getInstance();
		resolverSistemaEcuaciones(ingresarDatosController.getTableModel());
	}
	
	private void resolverSistemaEcuaciones(DefaultTableModel tableModel) {
		//Ejemplo para resolver sistema de ecuaciones
		//Borrar lo de abajo y sacar los valores del tableModel
		double[][] lhsArray = {{3, 2, -1}, {2, -2, 4}, {-1, 0.5, -1}};
        double[] rhsArray = {1, -2, 0};
        //Creating Matrix Objects with arrays
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 3);
        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
        //Printing Answers
        System.out.println("x = " + Math.round(ans.get(0, 0)));
        System.out.println("y = " + Math.round(ans.get(1, 0)));
        System.out.println("z = " + Math.round(ans.get(2, 0)));
	}

}
