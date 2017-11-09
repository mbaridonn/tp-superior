package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

	private JPanel contentPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TP Superior");
		setBounds(500, 300, 650, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JButton btnIngresarDatos = new JButton("Ingresar Datos");
		btnIngresarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IngresarDatosView ingresarDatosView = new IngresarDatosView();
				ingresarDatosView.setVisible(true);
			}
		});
		btnIngresarDatos.setBounds(10, 10, 600, 50);
		contentPanel.add(btnIngresarDatos);

		JButton btnNewButton_1 = new JButton("Comparar Aproximaciones");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompararAproxView compararAproxView = new CompararAproxView();
				compararAproxView.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 80, 600, 50);
		contentPanel.add(btnNewButton_1);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFinalizar.setBounds(10, 150, 600, 50);
		contentPanel.add(btnFinalizar);
	}

}
