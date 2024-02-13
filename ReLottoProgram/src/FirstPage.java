import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstPage extends JFrame {
	private JButton btnPurchase;
	private JButton btncharge;
	private JButton btnResult;
	private ChargePage chargePage;
	private PurchasePage purchasePage;
	private ResultPage resultPage;
	private static SaveMethod saveMethod;
	
	public FirstPage() {
		User user = new User();
		purchasePage = new PurchasePage(saveMethod);
		SaveMethod saveMethod = new SaveMethod(purchasePage);
		purchasePage.setSaveMethod(saveMethod);
		chargePage = new ChargePage(this);
		resultPage = new ResultPage();
		resultPage.setVisible(false);
		chargePage.setVisible(false);
		purchasePage.setVisible(false);
		
		extracted();
		btnPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				purchasePage.setVisible(true);
				purchasePage.currentAmount.setText(String.valueOf(User.amount));
			}
		});
		btncharge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				chargePage.setVisible(true);
			}
		});
		btnResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				resultPage.setVisible(true);
			}
		});

		showGUI();
	}
	

	private void showGUI() {
		setSize(451, 310);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	private void extracted() {
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 443, 279);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("로또 프로그램");
		lblNewLabel.setBounds(174, 27, 140, 25);
		panel.add(lblNewLabel);

		btnPurchase = new JButton("구매하기");
		btnPurchase.setBounds(24, 162, 97, 23);
		panel.add(btnPurchase);

		btncharge = new JButton("충전하기");
		btncharge.setBounds(160, 162, 97, 23);
		panel.add(btncharge);

		btnResult = new JButton("결과확인");
		btnResult.setBounds(292, 162, 97, 23);
		panel.add(btnResult);
	}

	public static void main(String[] args) {
		new FirstPage();
	}
}
