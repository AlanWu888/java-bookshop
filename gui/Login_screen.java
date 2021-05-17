package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_screen {

	private JFrame frm_login;
	/**
	 * Create the application.
	 */
	public Login_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_login = new JFrame();
		frm_login.setTitle("Book Management System");
		frm_login.setBounds(100, 100, 337, 435);
		frm_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_login.getContentPane().setLayout(null);
		
		JLabel lbl_title = new JLabel("Bookshop login");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(0, 20, 321, 20);
		lbl_title.setVerticalAlignment(SwingConstants.TOP);
		lbl_title.setEnabled(true);
		lbl_title.setFont(new Font("Dialog", Font.BOLD, 15));
		frm_login.getContentPane().add(lbl_title);
		
		Integer[] users = getUserIDList();
		JComboBox cmb_users = new JComboBox(users);
		cmb_users.setBounds(80, 157, 160, 25);
		frm_login.getContentPane().add(cmb_users);
		
		JLabel lbl_cmbPrompt = new JLabel("choose your user:");
		lbl_cmbPrompt.setLabelFor(cmb_users);
		lbl_cmbPrompt.setBounds(80, 137, 128, 14);
		frm_login.getContentPane().add(lbl_cmbPrompt);
		
		JButton btn_login = new JButton("Login");
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cmb_users.getSelectedItem().toString().toUpperCase().contains("101")) {
					AdminWindow adminView = new AdminWindow(Integer.parseInt(cmb_users.getSelectedItem().toString()));
				} else {
					CustomerWindow customerView = new CustomerWindow(Integer.parseInt(cmb_users.getSelectedItem().toString()));
				}
				frm_login.dispose();
			}
		});
		btn_login.setBounds(119, 270, 89, 37);
		frm_login.getContentPane().add(btn_login);
		
		JLabel lbl_myWork = new JLabel("F027425 :: Alan Wu");
		lbl_myWork.setBounds(10, 371, 178, 14);
		frm_login.getContentPane().add(lbl_myWork);
		frm_login.setVisible(true);
	}

	// getter methods
	private Integer[] getUserIDList() {
		Integer[] availableUsers = new Integer[4];	// set to 4 because there are only 4 users.
		try {
			List<user.User> userContents = new ArrayList<user.User>(textfile_handler.UserAccounts.getUsers());
			int index = 0;
			for(user.User user : userContents) {
				availableUsers[index] = user.getUserID();				
				index++;
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return availableUsers;
	}
	
}
