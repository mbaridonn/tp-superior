package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuResultadosView extends JDialog{

	public MenuResultadosView() {
		setTitle("Menu Resultados");
		setBounds(500, 350, 650, 300);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnMostrarFuncion = new JButton("Mostrar funcion");
		btnMostrarFuncion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MostrarFuncionView mostrarFuncionView = new MostrarFuncionView();
				mostrarFuncionView.setVisible(true);
			}
		});
		btnMostrarFuncion.setBounds(10, 10, 600, 50);
		btnMostrarFuncion.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnMostrarFuncion);
		
		JButton btnObtenerCalculo = new JButton("Obtener calculo");
		btnObtenerCalculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CalculoView calculoView = new CalculoView();
				calculoView.setVisible(true);
			}
		});
		btnObtenerCalculo.setBounds(10, 80, 600, 50);
		btnObtenerCalculo.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnObtenerCalculo);
		
		JButton btnGraficar = new JButton("Graficar");
		btnGraficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraficoView graficoView = new GraficoView();
				graficoView.setVisible(true);
			}
		});
		btnGraficar.setBounds(10, 150, 600, 50);
		btnGraficar.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnGraficar);
		
	}

}
