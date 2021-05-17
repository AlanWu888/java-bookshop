package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class CardFrame {

	private JFrame frm_Card;
	private JTextField txt_cardNo;
	private JTextField txt_secNo;
	private JLabel lbl_CVVerror = new JLabel("Your security number is invalid");
	private JLabel lbl_cardError = new JLabel("Your card number is invalid");
	/**
	 * Create the application.
	 */
	public CardFrame(double total) {
		initialize(total);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(double total) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String totalPrompt = "Total: " + formatter.format(total);
		
		frm_Card = new JFrame();
		frm_Card.setTitle("Credit Card Transaction");
		frm_Card.setBounds(100, 100, 350, 460);
		frm_Card.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_Card.getContentPane().setLayout(null);
		
		JLabel lbl_totalToPay = new JLabel(totalPrompt);
		lbl_totalToPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_totalToPay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_totalToPay.setBounds(183, 11, 141, 14);
		frm_Card.getContentPane().add(lbl_totalToPay);
		
		JLabel lbl_purchasePrompt = new JLabel("Purchase Amount");
		lbl_purchasePrompt.setBounds(10, 12, 128, 14);
		frm_Card.getContentPane().add(lbl_purchasePrompt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 314, 14);
		frm_Card.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 96, 314, 14);
		frm_Card.getContentPane().add(separator_1);
		
		JLabel lbl_Title = new JLabel("Make a payment");
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_Title.setBounds(10, 44, 314, 27);
		frm_Card.getContentPane().add(lbl_Title);
		
		JLabel lbl_subText = new JLabel("Please make a payment of " + totalPrompt);
		lbl_subText.setBounds(10, 71, 314, 14);
		frm_Card.getContentPane().add(lbl_subText);
		
		txt_cardNo = new JTextField();
		txt_cardNo.setBounds(40, 135, 284, 30);
		frm_Card.getContentPane().add(txt_cardNo);
		txt_cardNo.setColumns(10);
		
		txt_secNo = new JTextField();
		txt_secNo.setColumns(10);
		txt_secNo.setBounds(40, 199, 284, 30);
		frm_Card.getContentPane().add(txt_secNo);
		
		JButton btn_pay = new JButton("Pay Now");
		btn_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkFields()==true) {
					frm_Card.dispose();
					System.out.println("card payment gone through!");
				} else {
					if (checkCard() == false) {
						lbl_cardError.setVisible(true);
					}
					if (checkCard() == true) {
						lbl_cardError.setVisible(false);
					}
					if (checkCVV() == false) {
						lbl_CVVerror.setVisible(true);
					}
					if (checkCVV() == true) {
						lbl_CVVerror.setVisible(false);
					}
				}
			}
		});
		btn_pay.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_pay.setBounds(204, 380, 120, 30);
		frm_Card.getContentPane().add(btn_pay);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 324, 314, 14);
		frm_Card.getContentPane().add(separator_1_1);
		
		JLabel lbl_subSecurity = new JLabel("Your CVV code is a three digit code on the back of your credit card");
		lbl_subSecurity.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_subSecurity.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbl_subSecurity.setBounds(10, 230, 314, 14);
		frm_Card.getContentPane().add(lbl_subSecurity);
		
		JLabel lbl_cardNoPic = new JLabel("");
		lbl_cardNoPic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Alan Wu\\OneDrive\\Desktop\\Semester 2\\OOP\\!cw\\bookshop\\images\\credit-card-icon-png-4401.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		// lbl_payPal.setIcon(new ImageIcon(new ImageIcon("C:\\\\Users\\\\Alan Wu\\\\OneDrive\\\\Desktop\\\\Semester 2\\\\OOP\\\\!cw\\\\bookshop\\\\images\\\\PayPal_logo_logotype_emblem.png").getImage().getScaledInstance(141, 37, Image.SCALE_DEFAULT)));
		lbl_cardNoPic.setBounds(10, 135, 30, 30);
		frm_Card.getContentPane().add(lbl_cardNoPic);
		
		JLabel lbl_secNoPic = new JLabel("");
		lbl_secNoPic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Alan Wu\\OneDrive\\Desktop\\Semester 2\\OOP\\!cw\\bookshop\\images\\credit-card-with-cvv-code.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		lbl_secNoPic.setBounds(10, 199, 30, 30);
		frm_Card.getContentPane().add(lbl_secNoPic);
		
		JLabel lbl_subText_1 = new JLabel("Credit card number:");
		lbl_subText_1.setBounds(10, 110, 314, 14);
		frm_Card.getContentPane().add(lbl_subText_1);
		
		lbl_cardError.setVisible(false);
		lbl_cardError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_cardError.setForeground(Color.RED);
		lbl_cardError.setBounds(10, 174, 314, 14);
		frm_Card.getContentPane().add(lbl_cardError);
		
		lbl_CVVerror.setVisible(false);
		lbl_CVVerror.setForeground(Color.RED);
		lbl_CVVerror.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_CVVerror.setBounds(10, 245, 314, 14);
		frm_Card.getContentPane().add(lbl_CVVerror);
		
		frm_Card.setVisible(true);
	}

	private boolean checkFields() {
		boolean card = checkCard(), cvv = checkCVV(), valid = false;
		if ((cvv == true) && (card == true)) {
			valid = true;
		}
		return valid;
	}
	
	private boolean checkCard() {
		boolean valid = false;
		String cardNo = txt_cardNo.getText();
		if (cardNo.matches("[0-9]+") && cardNo.length() == 6) {
			valid = true;
		}
		return valid;
	}
	
	private boolean checkCVV() {
		boolean valid = false;
		String cvvNo = txt_secNo.getText();
		if (cvvNo.matches("[0-9]+") && cvvNo.length() == 3) {
			valid = true;
		}
		return valid;
	}
}
