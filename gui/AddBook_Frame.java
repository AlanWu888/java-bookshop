package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import book.BookTypes_e;
import book.Language_e;
import user.Address;
import user.UserType_e;
import book.Genre_e;

public class AddBook_Frame {

	private JFrame frm_addBook;
	
	private final String[] paperback_conditions = new String[] {"used", "new"};
	private final String[] ebook_format = new String[] {"EPUB", "MOBI", "AZW3", "PDF"};
	private final String[] audiobook_format = new String[] {"MP3", "WMA", "AAC"};
	
	private JTextField txt_ISBN;
	private JTextField txt_title;
	private JTextField txt_length;
	private JTextField txt_dd;
	private JTextField txt_pounds;
	private JTextField txt_MM;
	private JTextField txt_yyyy;
	private JTextField txt_qty;
	private JTextField txt_pence;
	
	private JComboBox cmb_bookType = new JComboBox(getNames(BookTypes_e.class));
	private JComboBox cmb_language = new JComboBox(getNames(Language_e.class));
	private JComboBox cmb_genre = new JComboBox(getNames(Genre_e.class));
	private JComboBox cmb_cond_form = new JComboBox(paperback_conditions);
	
	private JLabel lbl_titleError = new JLabel("A book title is required");
	private JLabel lbl_ISBNerror = new JLabel("Invalid ISBN");
	private JLabel lbl_lengthError = new JLabel("Invalid length");
	private JLabel lbl_dateError = new JLabel("Invalid Date");
	private JLabel lbl_priceError = new JLabel("Invalid Price");
	private JLabel lbl_qtyError = new JLabel("Quantity to add is required");
	
	private user.Admin admin = null;
	/**
	 * Create the application.
	 */
	public AddBook_Frame(Integer userID) {
		initialize(userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer userID) {
		// create the user object
		try {
			List<user.User> users = new ArrayList<user.User>(textfile_handler.UserAccounts.getUsers());
			for (user.User user : users) {
				if (user.getUserID() == userID) {
					String username = user.getUsername();
					String surname = user.getSurname();
					Address addr = user.getAddr();
					UserType_e userType = user.getUserType();
					admin = new user.Admin(userID, username, surname, addr, userType);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}

		frm_addBook = new JFrame();
		frm_addBook.setTitle("Add to Stock");
		frm_addBook.setBounds(100, 100, 460, 500);
		frm_addBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_addBook.getContentPane().setLayout(null);
		
		txt_ISBN = new JTextField();
		txt_ISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_ISBN.setBounds(10, 45, 200, 30);
		frm_addBook.getContentPane().add(txt_ISBN);
		txt_ISBN.setColumns(10);
		
		txt_title = new JTextField();
		txt_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_title.setColumns(10);
		txt_title.setBounds(10, 110, 420, 30);
		frm_addBook.getContentPane().add(txt_title);
		
		JLabel lbl_ISBN = new JLabel("ISBN:");
		lbl_ISBN.setLabelFor(txt_ISBN);
		lbl_ISBN.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_ISBN.setBounds(10, 30, 105, 15);
		frm_addBook.getContentPane().add(lbl_ISBN);
		
		JLabel lbl_title = new JLabel("Title:");
		lbl_title.setLabelFor(txt_title);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_title.setBounds(10, 95, 105, 15);
		frm_addBook.getContentPane().add(lbl_title);
		
		cmb_language.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmb_language.setBounds(10, 175, 200, 30);
		frm_addBook.getContentPane().add(cmb_language);
		
		cmb_genre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmb_genre.setBounds(230, 175, 200, 30);
		frm_addBook.getContentPane().add(cmb_genre);
		
		JLabel lbl_language = new JLabel("Language:");
		lbl_language.setLabelFor(cmb_language);
		lbl_language.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_language.setBounds(10, 160, 105, 15);
		frm_addBook.getContentPane().add(lbl_language);
		
		JLabel lbl_genre = new JLabel("Genre:");
		lbl_genre.setLabelFor(cmb_genre);
		lbl_genre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_genre.setBounds(230, 160, 105, 15);
		frm_addBook.getContentPane().add(lbl_genre);
		
		JLabel lbl_length = new JLabel("Length - page count");
		lbl_length.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_length.setBounds(10, 225, 200, 15);
		frm_addBook.getContentPane().add(lbl_length);
		
		txt_length = new JTextField();
		txt_length.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_length.setLabelFor(txt_length);
		txt_length.setColumns(10);
		txt_length.setBounds(10, 240, 200, 30);
		frm_addBook.getContentPane().add(txt_length);
		
		cmb_cond_form.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmb_cond_form.setBounds(230, 240, 200, 30);
		frm_addBook.getContentPane().add(cmb_cond_form);	
		
		JLabel lbl_cond_form = new JLabel("Condition");
		lbl_cond_form.setLabelFor(cmb_cond_form);
		lbl_cond_form.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_cond_form.setBounds(230, 225, 105, 15);
		frm_addBook.getContentPane().add(lbl_cond_form);
			
		cmb_bookType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmb_bookType.setBounds(230, 45, 200, 30);
		cmb_bookType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] cond_form = null;
				DefaultComboBoxModel model = (DefaultComboBoxModel) cmb_cond_form.getModel();
				model.removeAllElements();
				if (cmb_bookType.getSelectedItem().toString().toUpperCase().equals("PAPERBACK")) {
					cond_form = paperback_conditions;
					System.out.println("paperback match");
					lbl_cond_form.setText("Condition:");
					lbl_length.setText("Length - page count");
				}
				if (cmb_bookType.getSelectedItem().toString().toUpperCase().equals("AUDIOBOOK")) {
					cond_form = audiobook_format;
					System.out.println("audiobook match");
					lbl_cond_form.setText("Format:");
					lbl_length.setText("Length - page count");
				}
				if (cmb_bookType.getSelectedItem().toString().toUpperCase().equals("EBOOK")) {
					cond_form = ebook_format;
					System.out.println("ebook match");
					lbl_cond_form.setText("Format:");
					lbl_length.setText("Length - page count");
				}
				for (String components : cond_form) {
					model.addElement(components);
				}
				cmb_cond_form.setModel(model);
			}
		});
		
		frm_addBook.getContentPane().add(cmb_bookType);
		
		JLabel lbl_bookType = new JLabel("Type:");
		lbl_bookType.setLabelFor(cmb_bookType);
		lbl_bookType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_bookType.setBounds(230, 30, 105, 15);
		frm_addBook.getContentPane().add(lbl_bookType);
		
		txt_dd = new JTextField();
		txt_dd.setHorizontalAlignment(SwingConstants.CENTER);
		txt_dd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_dd.setColumns(10);
		txt_dd.setBounds(10, 305, 40, 30);
		frm_addBook.getContentPane().add(txt_dd);
		
		txt_pounds = new JTextField();
		txt_pounds.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_pounds.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_pounds.setColumns(10);
		txt_pounds.setBounds(260, 305, 105, 30);
		frm_addBook.getContentPane().add(txt_pounds);
		
		txt_MM = new JTextField();
		txt_MM.setHorizontalAlignment(SwingConstants.CENTER);
		txt_MM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_MM.setColumns(10);
		txt_MM.setBounds(80, 305, 40, 30);
		frm_addBook.getContentPane().add(txt_MM);
		
		txt_yyyy = new JTextField();
		txt_yyyy.setHorizontalAlignment(SwingConstants.CENTER);
		txt_yyyy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_yyyy.setColumns(10);
		txt_yyyy.setBounds(150, 305, 60, 30);
		frm_addBook.getContentPane().add(txt_yyyy);
		
		JLabel lbl_dateSep = new JLabel("-");
		lbl_dateSep.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbl_dateSep.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dateSep.setBounds(50, 303, 30, 30);
		frm_addBook.getContentPane().add(lbl_dateSep);
		
		JLabel lbl_dateSep1 = new JLabel("-");
		lbl_dateSep1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dateSep1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbl_dateSep1.setBounds(120, 303, 30, 30);
		frm_addBook.getContentPane().add(lbl_dateSep1);
		
		JLabel lbl_releaseDate = new JLabel("Release Date:");
		lbl_releaseDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_releaseDate.setBounds(10, 290, 110, 15);
		frm_addBook.getContentPane().add(lbl_releaseDate);
		
		JLabel lbl_dateFormat = new JLabel("[ dd - MM - yyyy ]");
		lbl_dateFormat.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_dateFormat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_dateFormat.setBounds(90, 290, 120, 15);
		frm_addBook.getContentPane().add(lbl_dateFormat);
		
		JLabel lbl_price = new JLabel("Price:");
		lbl_price.setLabelFor(txt_pounds);
		lbl_price.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_price.setBounds(230, 290, 200, 15);
		frm_addBook.getContentPane().add(lbl_price);
		
		JLabel lbl_poundSign = new JLabel("\u00A3");
		lbl_poundSign.setBackground(Color.WHITE);
		lbl_poundSign.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_poundSign.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 29));
		lbl_poundSign.setBounds(230, 303, 30, 30);
		frm_addBook.getContentPane().add(lbl_poundSign);
		
		txt_qty = new JTextField("1");
		txt_qty.setHorizontalAlignment(SwingConstants.CENTER);
		txt_qty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_qty.setColumns(10);
		txt_qty.setBounds(80, 380, 40, 30);
		frm_addBook.getContentPane().add(txt_qty);
		
		JButton btn_less = new JButton("-");
		btn_less.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// decrease quantity value
				int qty = Integer.parseInt(txt_qty.getText());
				if (qty > 1) {
					qty--;
					txt_qty.setText(String.valueOf(qty));
					System.out.println("Decreasing quantity of chosen book");
				}
			}
		});
		btn_less.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_less.setBounds(20, 370, 50, 50);
		frm_addBook.getContentPane().add(btn_less);
		
		JButton btn_more = new JButton("+");
		btn_more.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// increment quantity value
				int qty = Integer.parseInt(txt_qty.getText());
				qty++;
				txt_qty.setText(String.valueOf(qty));
				System.out.println("Incrementing quantity of chosen book");
			}
		});
		
		btn_more.setBounds(130, 370, 50, 50);
		frm_addBook.getContentPane().add(btn_more);
		
		JButton btn_submit = new JButton("Add to Stock");
		btn_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkInputs() == true) {
					System.out.println("book being added");
					writeToStock();
					frm_addBook.dispose();
				} else {
					System.out.println("=================");
					System.out.println("check your inputs");
					System.out.println("=================");
				}
			}
		});
		btn_submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_submit.setBounds(230, 370, 200, 50);
		frm_addBook.getContentPane().add(btn_submit);
		
		JLabel lbl_qty = new JLabel("Quantity to add:");
		lbl_qty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_qty.setBounds(10, 355, 200, 15);
		frm_addBook.getContentPane().add(lbl_qty);
		
		txt_pence = new JTextField();
		txt_pence.setHorizontalAlignment(SwingConstants.CENTER);
		txt_pence.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_pence.setColumns(10);
		txt_pence.setBounds(390, 305, 40, 30);
		frm_addBook.getContentPane().add(txt_pence);
		
		JLabel lbl_decimal = new JLabel(".");
		lbl_decimal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_decimal.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbl_decimal.setBounds(365, 305, 25, 30);
		frm_addBook.getContentPane().add(lbl_decimal);

		lbl_ISBNerror.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ISBNerror.setForeground(Color.RED);
		lbl_ISBNerror.setBounds(10, 75, 200, 14);
		lbl_ISBNerror.setVisible(false);
		frm_addBook.getContentPane().add(lbl_ISBNerror);
		
		lbl_titleError.setForeground(Color.RED);
		lbl_titleError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_titleError.setBounds(10, 140, 420, 14);
		lbl_titleError.setVisible(false);
		frm_addBook.getContentPane().add(lbl_titleError);
		
		lbl_lengthError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_lengthError.setForeground(Color.RED);
		lbl_lengthError.setBounds(10, 270, 200, 14);
		lbl_lengthError.setVisible(false);
		frm_addBook.getContentPane().add(lbl_lengthError);
		
		lbl_dateError.setForeground(Color.RED);
		lbl_dateError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_dateError.setBounds(10, 335, 200, 14);
		lbl_dateError.setVisible(false);
		frm_addBook.getContentPane().add(lbl_dateError);
		
		lbl_priceError.setForeground(Color.RED);
		lbl_priceError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_priceError.setBounds(230, 335, 200, 14);
		lbl_priceError.setVisible(false);
		frm_addBook.getContentPane().add(lbl_priceError);
		
		lbl_qtyError.setForeground(Color.RED);
		lbl_qtyError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_qtyError.setBounds(10, 418, 200, 14);
		lbl_qtyError.setVisible(false);
		frm_addBook.getContentPane().add(lbl_qtyError);
		
		frm_addBook.setVisible(true);
	}
	
	// methods
	private void writeToStock() {
		String ISBN = txt_ISBN.getText();
		BookTypes_e type = Enum.valueOf(BookTypes_e.class, cmb_bookType.getSelectedItem().toString());
		String title = txt_title.getText();
		Language_e language = Enum.valueOf(Language_e.class, cmb_language.getSelectedItem().toString());
		Genre_e genre = Enum.valueOf(Genre_e.class, cmb_genre.getSelectedItem().toString());
		// Strings to LocalDate
		int years = Integer.parseInt(txt_yyyy.getText());
		int months = Integer.parseInt(txt_MM.getText());
		int days = Integer.parseInt(txt_dd.getText());
		LocalDate release = LocalDate.of(years, months, days);
		String price_AsString = Integer.parseInt(txt_pounds.getText()) + "." + Integer.parseInt(txt_pence.getText());
		Double price = Double.valueOf(price_AsString);
		int qty = Integer.parseInt(txt_qty.getText());
		
		if (type == BookTypes_e.AUDIOBOOK) {
			double length = Double.parseDouble(txt_length.getText());
			String format = cmb_cond_form.getSelectedItem().toString();
			admin.addAudioBook(ISBN, type, title, language, genre, release, price, qty, length, format);
		}
		if (type == BookTypes_e.EBOOK) {
			int pageCount = Integer.parseInt(txt_length.getText());
			String format = cmb_cond_form.getSelectedItem().toString();
			admin.addEbook(ISBN, type, title, language, genre, release, price, qty, pageCount, format);
		}
		if (type == BookTypes_e.PAPERBACK) {
			int pageCount = Integer.parseInt(txt_length.getText());
			String condition = cmb_cond_form.getSelectedItem().toString();
			admin.addNewPaperback(ISBN, type, title, language, genre, release, price, qty, pageCount, condition);
		}
	}
	
	// validating methods
	private boolean checkInputs() {
		boolean valid = false;
		
		checkISBN();
		checkDate();
		checkTitle();
		checkLength();
		checkPrice();
		checkQty();
		
		if (checkISBN() && checkDate() && checkTitle() && checkLength() && checkPrice() && checkQty() == true) {
			valid = true;
		}
		return valid;
	}
	
	private boolean checkISBN() {
		boolean valid = false;
		String ISBN = txt_ISBN.getText();
		if (ISBN.matches("[0-9]+") && ISBN.length() == 8) {
			valid = true;
			if (lbl_ISBNerror.isVisible()==true) {
				lbl_ISBNerror.setVisible(false);
			}
		}
		else {
			System.out.println("ISBN in incorrect format");
			lbl_ISBNerror.setVisible(true);
		}
		return valid;
	}
	
	private boolean checkTitle() {
		boolean valid = false;
		String title = txt_title.getText();
		if (title.isEmpty()==false) {
			valid = true;
			if (lbl_titleError.isVisible()==true) {
				lbl_titleError.setVisible(false);
			}
		} else {
			System.out.println("No title provided");
			lbl_titleError.setVisible(true);
		}
		return valid;
	}
	
	private boolean checkLength() {
		boolean valid = false;
		String bookLength = txt_length.getText();
		if (cmb_bookType.getSelectedItem().toString().toUpperCase().equals("AUDIOBOOK")) {
			try {
				Double listeningLength = new Double(bookLength);
				valid = true;
				if (lbl_lengthError.isVisible()==true) {
					lbl_lengthError.setVisible(false);
				}
			} catch (Exception ex) {
				System.out.println("Invalid length");
				lbl_lengthError.setVisible(true);
			}
		} else {
			if (bookLength.matches("[0-9]+") && bookLength.length() > 0) {
				valid = true;
				if (lbl_lengthError.isVisible()==true) {
					lbl_lengthError.setVisible(false);
				}
			}
			else {
				System.out.println("Invalid length");
				lbl_lengthError.setVisible(true);
			}
		}
		return valid;
	}
	
	private boolean checkQty() {
		boolean valid = false;
		String qty = txt_qty.getText();
		if (qty.matches("[0-9]+") && qty.length() > 0) {
			valid = true;
			if (lbl_qtyError.isVisible()==true) {
				lbl_qtyError.setVisible(false);
			}
		}
		else {
			System.out.println("Invalid quantity");
			lbl_qtyError.setVisible(true);
		}
		return valid;
	}
	
	private boolean checkPrice() {
		boolean valid = false;
		if (checkPounds() && checkPence() == true) {
			valid = true;
			if (lbl_priceError.isVisible()==true) {
				lbl_priceError.setVisible(false);
			}
		}
		else {
			System.out.println("price error");
			lbl_priceError.setVisible(true);
		}
		return valid;
	}		
	private boolean checkPounds() {
		boolean valid = false;
		String pounds = txt_pounds.getText();
		if (pounds.matches("[0-9]+") && pounds.length() > 0) {
			valid = true;
		}
		return valid;
	}
	private boolean checkPence() {
		boolean valid = false;
		String pence = txt_pence.getText();
		if (pence.matches("[0-9]+") && pence.length() == 2) {
			valid = true;
		}
		return valid;
	}
	
	private boolean checkDate() {
		boolean valid = false;
		int counterValid = 0;
		if (checkDays()==true) {
			counterValid++;
		}
		if (checkMonths()==true) {
			counterValid++;
		}
		if (checkYears()==true) {
			counterValid++;
		}
		if (counterValid != 3) {
			System.out.println("Invalid date");
			lbl_dateError.setVisible(true);
		}
		else {
			valid = true;
			if (lbl_dateError.isVisible()==true) {
				lbl_dateError.setVisible(false);
			}
		}
		// System.out.println("Date points: " + counterValid);
		return valid;
	}	
	private boolean checkDays() {
		boolean valid = false;

		String days = txt_dd.getText();
		if (days.matches("[0-9]+") && days.length() == 2) {
			int days_asInt;
			try {
				days_asInt = Integer.parseInt(days);
				if ((days_asInt >= 1) && (days_asInt <= 31)) {
					valid = true;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}	
		return valid;
	}
	private boolean checkMonths() {
		boolean valid = false;
		String months = txt_MM.getText();
		if (months.matches("[0-9]+") && months.length() == 2) {
			int months_asInt;
			try {
				months_asInt = Integer.parseInt(months);
				if ((months_asInt >= 1) && (months_asInt <= 12)) {
					valid = true;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return valid;
	}	
	private boolean checkYears() {
		boolean valid = false;
		String years = txt_yyyy.getText();
		if (years.matches("[0-9]+") && years.length() == 4) {
			valid = true;
		}
		return valid;
	}
	
	// getter methods
	private static String[] getNames(Class<? extends Enum<?>> e) {
		/*	function which gets all names in an enum as a String[]
		 * 		https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as)-a-string
		*/
	    return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
	}


}



