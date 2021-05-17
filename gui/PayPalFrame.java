package gui;

import java.text.NumberFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PayPalFrame {

	private JFrame frm_payPal;
	private JTextField txt_email;
	private JTextField textField;
	private JLabel lbl_email;
	private JLabel lbl_error = new JLabel("! Enter a valid email address");

	/**
	 * Create the application.
	 */
	public PayPalFrame(double total) {
		initialize(total);
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	private void initialize(double total) {				
		frm_payPal = new JFrame();
		frm_payPal.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alan Wu\\OneDrive\\Desktop\\Semester 2\\OOP\\!cw\\bookshop\\images\\paypal-3384015_960_720.png"));
		frm_payPal.setTitle("PayPal, Inc. [US]");
		frm_payPal.setBounds(100, 100, 350, 460);
		frm_payPal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_payPal.getContentPane().setLayout(null);
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		JLabel lbl_totalToPay = new JLabel("Total: " + formatter.format(total));
		lbl_totalToPay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_totalToPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_totalToPay.setBounds(183, 11, 141, 14);
		frm_payPal.getContentPane().add(lbl_totalToPay);
		
		txt_email = new JTextField();
		txt_email.setBounds(25, 154, 285, 30);
		frm_payPal.getContentPane().add(txt_email);
		txt_email.setColumns(10);
		
		lbl_email = new JLabel("email Address:");
		lbl_email.setLabelFor(txt_email);
		lbl_email.setBounds(25, 132, 116, 14);
		frm_payPal.getContentPane().add(lbl_email);
		
		JButton btn_pay = new JButton("Continue");
		btn_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email_addr = txt_email.getText();
				System.out.println("PayPal transaction");
				// check for valid email:
				if (email_addr.contains("@")) {
					if (email_addr.contains(".")) {
						frm_payPal.dispose();
					}
				} else {
					lbl_error.setVisible(true);
				}
			}
		});
		btn_pay.setBackground(new Color(51, 153, 204));
		btn_pay.setOpaque(true);
		btn_pay.setForeground(Color.WHITE);
		btn_pay.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_pay.setBounds(25, 246, 285, 30);
		frm_payPal.getContentPane().add(btn_pay);
		
		// ImageIcon paypalLogo = new ImageIcon("C:\\\\Users\\\\Alan Wu\\\\OneDrive\\\\Desktop\\\\Semester 2\\\\OOP\\\\!cw\\\\bookshop\\\\images\\\\PayPal_logo_logotype_emblem.png");
		JLabel lbl_payPal = new JLabel();		
		lbl_payPal.setIcon(new ImageIcon(new ImageIcon("C:\\\\Users\\\\Alan Wu\\\\OneDrive\\\\Desktop\\\\Semester 2\\\\OOP\\\\!cw\\\\bookshop\\\\images\\\\PayPal_logo_logotype_emblem.png").getImage().getScaledInstance(141, 37, Image.SCALE_DEFAULT)));
		lbl_payPal.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 33));
		lbl_payPal.setBounds(25, 11, 141, 37);
		frm_payPal.getContentPane().add(lbl_payPal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 59, 285, 37);
		frm_payPal.getContentPane().add(separator);
		
		JLabel lbl_paypal_info = new JLabel("Log into PayPal");
		lbl_paypal_info.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_paypal_info.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_paypal_info.setBounds(25, 74, 285, 30);
		frm_payPal.getContentPane().add(lbl_paypal_info);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 217, 285, 37);
		frm_payPal.getContentPane().add(separator_1);
		
		lbl_error.setVisible(false);
		lbl_error.setForeground(Color.RED);
		lbl_error.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_error.setBounds(25, 192, 285, 14);
		frm_payPal.getContentPane().add(lbl_error);
		
		frm_payPal.setVisible(true);
	}
}
