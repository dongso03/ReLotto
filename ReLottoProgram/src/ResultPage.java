import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ResultPage extends JFrame{
	public ResultPage() {
		extracted();
		showGUI();
	}

	private void showGUI() {
		setSize(441, 555);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void extracted() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 42, 358, 48);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(34, 116, 358, 31);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(34, 166, 269, 48);
		getContentPane().add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(34, 224, 269, 48);
		getContentPane().add(panel_2_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBounds(34, 282, 269, 48);
		getContentPane().add(panel_2_2);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBounds(34, 340, 269, 48);
		getContentPane().add(panel_2_3);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setBounds(34, 398, 269, 48);
		getContentPane().add(panel_2_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(315, 166, 76, 48);
		getContentPane().add(panel_3);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(316, 224, 76, 48);
		getContentPane().add(panel_3_1);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBounds(316, 282, 76, 48);
		getContentPane().add(panel_3_2);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setBounds(316, 340, 76, 48);
		getContentPane().add(panel_3_3);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.setBounds(316, 398, 76, 48);
		getContentPane().add(panel_3_4);
		
		JButton btnNewButton = new JButton("시작화면으로");
		btnNewButton.setBounds(268, 481, 124, 23);
		getContentPane().add(btnNewButton);
	}

}
