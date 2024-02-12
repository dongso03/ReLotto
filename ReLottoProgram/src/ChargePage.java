import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargePage extends JFrame {
    private JTextField textField;
    private JButton btnNewButton_1;
    public static FirstPage firstPage;
    public ChargePage(FirstPage firstPage) {
        this.firstPage = firstPage;
        extracted();
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
        panel.setBounds(0, 0, 434, 261);
        getContentPane().add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(142, 150, 116, 21);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("충전하기");
        btnNewButton.setBounds(297, 149, 97, 23);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rechargeAmount = Integer.parseInt(textField.getText());

                    if (rechargeAmount > 0 && rechargeAmount <= 10000000) {
                        User.addAmount(rechargeAmount);
                        setVisible(false);
                        firstPage.setVisible(true);
                        SaveMethod.saveAmount();
                        System.out.println(User.getAmount());
                    } else {
                    	System.out.println("충전할 금액이 올바르지 않습니다.");
                    	setEnabled(false);
                    	setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("올바른 숫자를 입력하세요.");
                }
            }
        });

        JLabel lblNewLabel = new JLabel("충전할 금액");
        lblNewLabel.setBounds(43, 153, 87, 15);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("현재 금액: " + User.getAmount());
        lblNewLabel_1.setBounds(142, 198, 151, 15);
        panel.add(lblNewLabel_1);

        JButton btnNewButton_1 = new JButton("시작 화면으로");
        btnNewButton_1.setBounds(0, 0, 130, 23);
        panel.add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                firstPage.setVisible(true);
            }
        });
    }
}