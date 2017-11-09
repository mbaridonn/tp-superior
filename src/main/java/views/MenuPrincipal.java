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
		setBounds(300, 200, 650, 185);
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
		btnIngresarDatos.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnIngresarDatos);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFinalizar.setBounds(10, 71, 600, 50);
		btnFinalizar.setFont(new java.awt.Font("Tahoma", 0, 20));
		contentPanel.add(btnFinalizar);
	}

}
