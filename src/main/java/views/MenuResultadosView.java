package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuResultadosView extends JDialog{
	
	private JPanel contentPanel;

	public MenuResultadosView() {
		setTitle("Menu Resultados");
		setBounds(100, 100, 266, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Mostrar funcion");
		btnNewButton.setBounds(12, 13, 224, 25);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obtener calculo");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CalculoView calculoView = new CalculoView();
				calculoView.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(12, 51, 224, 25);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Graficar");
		btnNewButton_2.setBounds(12, 89, 224, 25);
		contentPanel.add(btnNewButton_2);
		
	}

}
