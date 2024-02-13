import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class PurchasePage extends JFrame {
	private FirstPage firstPage;
	private static SaveMethod saveMethod;
	User user;
	private JToggleButton[] tbtn45;
	private JPanel pnlTogglebtn;

	private static List<Integer> selectedNumbers = new ArrayList<>();
	private int selectedCount = 0;
	int selectCount = 0;
	public Map<Integer, JLabel[]> map;
	int sumSelectedCombo = 0;
	int selectedCombo = 0;
	private int comboboxCount = 0;
	public JLabel[] lbl1;
	public JLabel[] lbl2;
	public JLabel[] lbl3;
	public JLabel[] lbl4;
	public JLabel[] lbl5;
	public JLabel currentAmount;
	private JButton btnNewButton_1;
	private JToggleButton btnAuto;
	private JButton btnOkay;
	private JLabel amountToPay;
	private String[] comboBoxLists;

	public PurchasePage(SaveMethod saveMethod) {
		this.saveMethod = saveMethod;
		extracted();
		showGUI();

	}

	public void setSaveMethod(SaveMethod saveMethod) {
		this.saveMethod = saveMethod;
	}

	private void showGUI() {
		setSize(845, 517);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void extracted() {
		getContentPane().setLayout(null);

		pnlTogglebtn = new JPanel(new GridLayout(0, 6, 5, 5)); // 6개씩, 간격 5
		pnlTogglebtn.setBounds(24, 22, 344, 333);
		getContentPane().add(pnlTogglebtn);

		tbtn45 = new JToggleButton[45]; // 배열로 변경

		for (int i = 1; i < 46; i++) {
			tbtn45[i - 1] = new JToggleButton(String.valueOf(i));
			tbtn45[i - 1].addActionListener(new ToggleButtonListener());
			pnlTogglebtn.add(tbtn45[i - 1]);
		}

		JPanel panel = new JPanel();
		panel.setBounds(24, 400, 344, 68);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("수량");
		lblNewLabel.setBounds(12, 31, 35, 15);
		panel.add(lblNewLabel);

		comboBoxLists = new String[] { "1", "2", "3", "4", "5" };
		JComboBox comboBox = new JComboBox(comboBoxLists);
		comboBox.setBounds(58, 28, 60, 21);
		panel.add(comboBox);

		btnAuto = new JToggleButton("자동");
		btnAuto.setBounds(131, 27, 92, 23);
		panel.add(btnAuto);
		btnAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnAuto.isSelected() && selectedCount == 6) {
					btnOkay.setEnabled(false);
					btnOkay.setEnabled(true);
				}
			}
		});

		map = new HashMap<>();
		map.put(1, lbl1);
		map.put(2, lbl2);
		map.put(3, lbl3);
		map.put(4, lbl4);
		map.put(5, lbl5);

		btnOkay = new JButton("확인");
		btnOkay.setBounds(235, 27, 97, 23);
		panel.add(btnOkay);
		btnOkay.addActionListener(new ActionListener() {

			int selectedCombo;
			public JLabel[] currentLabels;

			// private Map<Integer, JLabel[]> map = new HashMap<>();

			public void actionPerformed(ActionEvent e) {

				map.put(1, lbl1);
				map.put(2, lbl2);
				map.put(3, lbl3);
				map.put(4, lbl4);
				map.put(5, lbl5);

				selectedCombo = comboBox.getSelectedIndex() + 1;
				sumSelectedCombo += selectedCombo; // 5이 됨. => lbl4,5가 채워져야됨(map의 키가 4,5임)

				if (sumSelectedCombo < 6) {

					for (int i = sumSelectedCombo - selectedCombo; i < sumSelectedCombo; i++) { // lbl2,3,4에 추가되어야 됨
						currentLabels = map.get(i + 1);
						int currentLabelIndex = 0;

						if (!btnAuto.isSelected()) { // 수동 일때
							for (JToggleButton button : tbtn45) {
								if (button.isSelected()) {
									selectCount++;
								}
							}
							// if (selectCount == 0) {
							if (selectCount != 0 && selectCount == 6) {
								selectedNumbers.addAll(getSelectedNumbers());
								Collections.sort(selectedNumbers);

								for (int j = 2; j < 8; j++) {
									currentLabels[1].setText("수동");
									currentLabels[j].setText(String.valueOf(selectedNumbers.get(j - 2)));
								}
							} else {
								btnOkay.setEnabled(false);
								btnOkay.setEnabled(true);
								if (sumSelectedCombo > 0) {
									sumSelectedCombo -= selectedCombo;
								}
							}

							// }

						} else if (btnAuto.isSelected() && selectedCount == 6) {
							btnOkay.setEnabled(false);
							btnOkay.setEnabled(true);
							if (sumSelectedCombo > 0) {
								sumSelectedCombo -= selectedCombo;
							}
							System.out.println(selectedCount);
						} else { // 자동 버튼 눌렀을 때
							Set<Integer> nonDuplicateNumber = new HashSet<>();

							for (JToggleButton button : tbtn45) {
								if (button.isSelected()) {
									selectCount++;
								}
							}

							if (selectCount == 0 || selectCount == 6) {
								자동으로_숫자_생성(currentLabels, nonDuplicateNumber);
							} else if (0 < selectCount && selectCount < 6) {
								반자동으로_숫자_생성(currentLabels, nonDuplicateNumber);
							}
						}
						selectedNumbers.clear();
						selectCount = 0;
					}

				} else {
					btnOkay.setEnabled(false);
					btnOkay.setEnabled(true);
					if (sumSelectedCombo > 0) {
						sumSelectedCombo -= selectedCombo;
					}
				}

				selectedCombo = comboBox.getSelectedIndex() + 1;
				sumSelectedCombo += selectedCombo; // 누적값으로 설정

				comboboxCount = comboBox.getSelectedIndex() + 1;
				amountToPay.setText(String.valueOf(sumSelectedCombo * 1000));

				if (Integer.parseInt(currentAmount.getText()) < Integer.parseInt(amountToPay.getText())) {
					btnOkay.setEnabled(false);
					btnOkay.setEnabled(true);
					if (sumSelectedCombo > 0) {
						sumSelectedCombo -= selectedCombo;
					}
				}

				for (JToggleButton button : tbtn45) {
					button.setSelected(false);
				}
				for (JToggleButton button : tbtn45) {
					button.setSelected(false);
					// Explicitly handle each button to update the state
					handleToggleButton(button);
				}

			}

		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(380, 22, 437, 333);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel pnl1st = new JPanel();
		pnl1st.setBounds(12, 31, 299, 45);
		panel_1.add(pnl1st);
		lbl1 = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			lbl1[i] = new JLabel("");
			lbl1[0] = new JLabel("A");
			pnl1st.add(lbl1[i]);
		}

		JPanel pnl2nd = new JPanel();
		pnl2nd.setBounds(12, 86, 299, 45);
		panel_1.add(pnl2nd);
		lbl2 = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			lbl2[i] = new JLabel("");
			lbl2[0] = new JLabel("B");
			pnl2nd.add(lbl2[i]);
		}

		JPanel pnl3rd = new JPanel();
		pnl3rd.setBounds(12, 141, 299, 45);
		panel_1.add(pnl3rd);
		lbl3 = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			lbl3[i] = new JLabel("");
			lbl3[0] = new JLabel("C");
			pnl3rd.add(lbl3[i]);
		}

		JPanel pnl4th = new JPanel();
		pnl4th.setBounds(12, 206, 299, 45);
		panel_1.add(pnl4th);
		lbl4 = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			lbl4[i] = new JLabel("");
			lbl4[0] = new JLabel("D");
			pnl4th.add(lbl4[i]);
		}

		JPanel pnl5th = new JPanel();
		pnl5th.setBounds(12, 271, 299, 45);
		panel_1.add(pnl5th);
		lbl5 = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			lbl5[i] = new JLabel("");
			lbl5[0] = new JLabel("E");
			pnl5th.add(lbl5[i]);
		}

		JButton btnNewButton = new JButton("초기화");
		btnNewButton.setBounds(323, 41, 97, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < 8; i++) {
					lbl1[i].setText(lbl2[i].getText());
					lbl2[i].setText(lbl3[i].getText());
					lbl3[i].setText(lbl4[i].getText());
					lbl4[i].setText(lbl5[i].getText());
					lbl5[i].setText("");
				}
				if (sumSelectedCombo > 0) {
					sumSelectedCombo--;
				}
			}
		});

		JButton btnNewButton_2 = new JButton("초기화");
		btnNewButton_2.setBounds(323, 97, 97, 23);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 1; i < 8; i++) {
					lbl2[i].setText(lbl3[i].getText());
					lbl3[i].setText(lbl4[i].getText());
					lbl4[i].setText(lbl5[i].getText());
					lbl5[i].setText("");
				}

				if (sumSelectedCombo > 0) {
					sumSelectedCombo--;
				}
			}
		});
		JButton btnNewButton_3 = new JButton("초기화");
		btnNewButton_3.setBounds(323, 150, 97, 23);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < 8; i++) {
					lbl3[i].setText(lbl4[i].getText());
					lbl4[i].setText(lbl5[i].getText());
					lbl5[i].setText("");
				}
				if (sumSelectedCombo > 0) {
					sumSelectedCombo--;
				}
			}
		});

		JButton btnNewButton_4 = new JButton("초기화");
		btnNewButton_4.setBounds(323, 215, 97, 23);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < 8; i++) {
					lbl4[i].setText(lbl5[i].getText());
					lbl5[i].setText("");
				}
				if (sumSelectedCombo > 0) {
					sumSelectedCombo--;
				}
			}
		});

		JButton btnNewButton_5 = new JButton("초기화");
		btnNewButton_5.setBounds(323, 282, 97, 23);
		panel_1.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < 8; i++) {
					lbl5[i].setText("");
				}
				if (sumSelectedCombo > 0) {
					sumSelectedCombo--;
				}
			}
		});
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(380, 380, 437, 88);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		btnNewButton_1 = new JButton("구매");
		btnNewButton_1.setBounds(340, 26, 69, 23);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (lbl1[2].getText().isEmpty()) {
					btnNewButton_1.setEnabled(false);
					btnNewButton_1.setEnabled(true);
				} else {
					dispose();
					saveMethod.saveLottoNums();
					firstPage = new FirstPage();
					firstPage.setVisible(true);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("현재 잔액");
		lblNewLabel_1.setBounds(28, 10, 57, 15);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("결제할 금액");
		lblNewLabel_2.setBounds(160, 10, 74, 15);
		panel_2.add(lblNewLabel_2);

		currentAmount = new JLabel(String.valueOf(user.amount));
		currentAmount.setBounds(28, 50, 57, 15);
		panel_2.add(currentAmount);

		amountToPay = new JLabel("수정할것 2");
		amountToPay.setBounds(160, 50, 57, 15);
		panel_2.add(amountToPay);

		JButton btnReset = new JButton("초기화");
		btnReset.setBounds(144, 370, 97, 23);
		getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < pnlTogglebtn.getComponentCount(); i++) {
					if (pnlTogglebtn.getComponent(i) instanceof JToggleButton) {
						JToggleButton toggleButton = (JToggleButton) pnlTogglebtn.getComponent(i);
						toggleButton.setSelected(false);
					}
				}

				selectedCount = 0;

				for (int i = 0; i < pnlTogglebtn.getComponentCount(); i++) {
					if (pnlTogglebtn.getComponent(i) instanceof JToggleButton) {
						JToggleButton toggleButton = (JToggleButton) pnlTogglebtn.getComponent(i);
						toggleButton.setEnabled(true);
					}
				}
			}
		});

	}

	private void handleToggleButton(JToggleButton selectedButton) {
		// Count the number of selected toggle buttons
		selectedCount = 0;
		for (int i = 0; i < pnlTogglebtn.getComponentCount(); i++) {
			if (pnlTogglebtn.getComponent(i) instanceof JToggleButton) {
				JToggleButton toggleButton = (JToggleButton) pnlTogglebtn.getComponent(i);
				if (toggleButton.isSelected()) {
					selectedCount++;
				}
			}
		}

		// Disable all toggle buttons beyond the first 6
		for (int i = 0; i < pnlTogglebtn.getComponentCount(); i++) {
			if (pnlTogglebtn.getComponent(i) instanceof JToggleButton) {
				JToggleButton toggleButton = (JToggleButton) pnlTogglebtn.getComponent(i);
				toggleButton.setEnabled(selectedCount < 6 || toggleButton.isSelected());
			}
		}
	}

	private class ToggleButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JToggleButton selectedButton = (JToggleButton) e.getSource();
			handleToggleButton(selectedButton);
		}

		// New method to handle toggled button
	}

	private List<Integer> getSelectedNumbers() {
		List<Integer> selectedNumbers = new ArrayList<>();
		for (JToggleButton button : tbtn45) {
			if (button.isSelected()) {
				selectedNumbers.add(Integer.parseInt(button.getText()));
			}
		}
		return selectedNumbers;
	}

	private void 자동으로_숫자_생성(JLabel[] currentLabel, Set<Integer> nonDuplicateNumber) {
		Random r = new Random();
		while (nonDuplicateNumber.size() < 7) {
			int randomNumber = r.nextInt(45) + 1;
			nonDuplicateNumber.add(randomNumber);
		}

		List<Integer> lottoResult = new ArrayList<>(nonDuplicateNumber);
		Collections.sort(lottoResult);

		currentLabel[1].setText("자동");

		for (int i = 2; i < 8; i++) {
			currentLabel[i].setText(String.valueOf(lottoResult.get(i - 2)));
		}
	}

	// 반자동으로 숫자를 생성하는
	// 메소드--------------------------------------------------------------
	private void 반자동으로_숫자_생성(JLabel[] currentLabel, Set<Integer> nonDuplicateNumber) {
		selectedNumbers.addAll(getSelectedNumbers());
		nonDuplicateNumber.addAll(selectedNumbers);
		Random ran = new Random();

		while (nonDuplicateNumber.size() < 7) {
			int r = ran.nextInt(45) + 1;
			nonDuplicateNumber.add(r);
		}

		List<Integer> lottoResult = new ArrayList<>(nonDuplicateNumber);
		Collections.sort(lottoResult);

		currentLabel[1].setText("반자동");

		for (int i = 2; i < 8; i++) {
			currentLabel[i].setText(String.valueOf(lottoResult.get(i - 2)));
		}

		selectedNumbers.clear();
	}

	public static void main(String[] args) {
		PurchasePage purchasePage = new PurchasePage(saveMethod);
		SaveMethod saveMethod = new SaveMethod(purchasePage);
		purchasePage.setSaveMethod(saveMethod);
	}
}