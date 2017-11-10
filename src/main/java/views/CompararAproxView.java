package views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CompararAproxView extends JDialog {

	private final JPanel contentPanel;

	public CompararAproxView() {
		setTitle("Comparar Aproximaciones");
		setBounds(300, 200, 650, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAproximar = new JLabel("Aproximar");
		lblAproximar.setBounds(94, 43, 64, 14);
		contentPanel.add(lblAproximar);
		
		
	}
}
