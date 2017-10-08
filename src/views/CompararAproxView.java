package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CompararAproxView extends JDialog {

	private final JPanel contentPanel;

	public CompararAproxView() {
		setTitle("Comparar Aproximaciones");
		setBounds(100, 100, 266, 179);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAproximar = new JLabel("Aproximar");
		lblAproximar.setBounds(94, 43, 64, 14);
		contentPanel.add(lblAproximar);
		
		
	}
}
