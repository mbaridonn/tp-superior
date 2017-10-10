package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IngresarDatosView extends JDialog {

	private JPanel contentPanel;

	/**
	 * Create the dialog.
	 */
	public IngresarDatosView() {
		setTitle("Ingresar Datos");
		setBounds(100, 100, 266, 179);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JButton btnNewButton = new JButton("Aproximar mediante");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(10, 11, 230, 23);
		contentPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Graficar nube de puntos");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 45, 230, 23);
		contentPanel.add(btnNewButton_1);
	}

}
