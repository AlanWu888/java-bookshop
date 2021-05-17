package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import book.BookTypes_e;
import book.Genre_e;
import book.Language_e;
import textfile_handler.SearchFilters_e;
import textfile_handler.Status_e;
import user.Address;
import user.UserType_e;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerWindow {

	private JFrame frmBookshopManagement;
	private JTextField txt_ISBN;
	private JTable tbl_books;
	private JTextField txt_qty;
	private JTable tbl_basket;
	private String[] columnHeaders_books = {"ISBN", "Type", "Title", "Language", "Genre", "Release", "Price", "Qty. in Stock"," ", " "};
	private DefaultTableModel tableModel_books = new DefaultTableModel(columnHeaders_books, 0);
	private String[] columnHeaders_basket = {"ISBN" , "QTY", "Price"};
	private DefaultTableModel tableModel_basket = new DefaultTableModel(columnHeaders_basket, 0);
	private user.Customer customer = null;
	private JTextField txt_contents;

	
	public CustomerWindow(Integer userID) {
		initialize(userID);	
	}
	
	/**
	 * 	Initialize the contents of the frame.
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
					customer = new user.Customer(userID, username, surname, addr, userType);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
		// create the user's basket (will be a list of object)
		ArrayList<user.BasketItem> basket = new ArrayList<>(customer.getBasket());	
		basket = null;		
		
		List<book.Book> stock = new ArrayList<book.Book>(); 
		try {
			stock = textfile_handler.Stock.getStock();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
		frmBookshopManagement = new JFrame();
		frmBookshopManagement.setTitle("Bookshop Management");
		frmBookshopManagement.setResizable(false);
		frmBookshopManagement.setBounds(100, 100, 1080, 800);
		frmBookshopManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookshopManagement.getContentPane().setLayout(null);
		frmBookshopManagement.setVisible(true);
		
		JLabel lbl_displayBooks_1 = new JLabel("Displaying books:");
		lbl_displayBooks_1.setBounds(10, 112, 731, 15);
		frmBookshopManagement.getContentPane().add(lbl_displayBooks_1);
		lbl_displayBooks_1.setBackground(new Color(0, 0, 0));
		lbl_displayBooks_1.setForeground(Color.WHITE);
		lbl_displayBooks_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel pnl_title = new JPanel();
		pnl_title.setBackground(new Color(0, 0, 139));
		pnl_title.setForeground(Color.LIGHT_GRAY);
		pnl_title.setBounds(0, 0, 1074, 60);
		frmBookshopManagement.getContentPane().add(pnl_title);
		pnl_title.setLayout(null);
		
		JLabel lbl_WelcomeUser = new JLabel("Welcome User " + userID);
		lbl_WelcomeUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_WelcomeUser.setForeground(new Color(255, 255, 255));
		lbl_WelcomeUser.setBounds(10, 35, 280, 14);
		pnl_title.add(lbl_WelcomeUser);
		
		JLabel lbl_title = new JLabel("Bookshop Management System");
		lbl_title.setBounds(404, 5, 316, 44);
		pnl_title.add(lbl_title);
		lbl_title.setBackground(new Color(0, 204, 255));
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btn_logout = new JButton("Log out");
		btn_logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("logging out");
				Login_screen login = new Login_screen();
				frmBookshopManagement.dispose();
			}
		});
		btn_logout.setBounds(964, 10, 90, 23);
		pnl_title.add(btn_logout);
		
		JPanel pnl_searchBar = new JPanel();
		pnl_searchBar.setBackground(new Color(255, 0, 0));
		pnl_searchBar.setForeground(new Color(153, 0, 51));
		pnl_searchBar.setBounds(0, 60, 1074, 41);
		frmBookshopManagement.getContentPane().add(pnl_searchBar);
		pnl_searchBar.setLayout(null);
				
		JComboBox cmb_keyword = new JComboBox(new String[] {"SHOWING ALL"});
		cmb_keyword.setBounds(610, 6, 170, 20);
		cmb_keyword.setEditable(true);
		pnl_searchBar.add(cmb_keyword);
		
		JComboBox cmb_filter = new JComboBox(getNames(SearchFilters_e.class));
		cmb_filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] keywords = null;				
				DefaultComboBoxModel model = (DefaultComboBoxModel) cmb_keyword.getModel();			
				model.removeAllElements();
				if (cmb_filter.getSelectedItem().toString().toUpperCase().equals("LANGUAGE")) {
					keywords = getNames(Language_e.class);
				}
				if (cmb_filter.getSelectedItem().toString().toUpperCase().equals("GENRE")) {
					keywords = getNames(Genre_e.class);
				}
				if (cmb_filter.getSelectedItem().toString().toUpperCase().equals("TYPE")) {
					keywords = getNames(BookTypes_e.class);
				}
				if (cmb_filter.getSelectedItem().toString().toUpperCase().equals("ALL")) {
					keywords = new String[] {"SHOWING ALL"};
				}
				for (String keyword : keywords) {
					model.addElement(keyword);
				}
				cmb_keyword.setModel(model);
			}
		});
		cmb_filter.setBounds(430, 6, 170, 20);
		cmb_filter.setEditable(true);
		pnl_searchBar.add(cmb_filter);
		
		JPanel pnl_basket = new JPanel();
		pnl_basket.setBackground(new Color(0, 0, 0));
		pnl_basket.setBounds(751, 112, 303, 497);
		frmBookshopManagement.getContentPane().add(pnl_basket);
		pnl_basket.setLayout(new BorderLayout(0, 0));
		
		tbl_basket = new JTable(tableModel_basket) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }			
		};
		pnl_basket.add(tbl_basket, BorderLayout.CENTER);

		JLabel lbl_basketTitle = new JLabel("Your Basket:");
		lbl_basketTitle.setLabelFor(tbl_basket);
		lbl_basketTitle.setForeground(new Color(255, 255, 255));
		lbl_basketTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_basket.add(lbl_basketTitle, BorderLayout.NORTH);
		
		txt_ISBN = new JTextField();
		txt_ISBN.setEditable(false);
		txt_ISBN.setBounds(191, 686, 180, 20);
		frmBookshopManagement.getContentPane().add(txt_ISBN);
		txt_ISBN.setColumns(10);
		
		JPanel pnl_books = new JPanel();
		pnl_books.setBackground(new Color(0, 0, 0));
		pnl_books.setForeground(new Color(0, 0, 0));
		pnl_books.setBounds(10, 127, 731, 482);
		frmBookshopManagement.getContentPane().add(pnl_books);
		pnl_books.setLayout(new BorderLayout(0, 0));
		
		tbl_books = new JTable(tableModel_books) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }			
		};
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel_books);
		tbl_books.setRowSorter(sorter);
		tbl_books.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl_books.setCellSelectionEnabled(true);
		tbl_books.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
			        txt_qty.setText("1");
					List<String> myContents = new ArrayList<String>();   
					int row = tbl_books.rowAtPoint(evt.getPoint());
					for (int i = 0; i<columnHeaders_books.length; i++) {
						myContents.add(tbl_books.getModel().getValueAt(row, i).toString());
					}
					txt_contents.setText(String.join(", ", myContents));
					txt_ISBN.setText(tbl_books.getModel().getValueAt(row, 0).toString());
				} catch(ArrayIndexOutOfBoundsException ex) {
				    ex.getStackTrace();
				    System.out.println(ex.getMessage());
				}
		    }
		});
		for (int i = 0; i<10; i++) {
			tbl_books.getColumnModel().getColumn(i).setPreferredWidth(150);		
		}
	
		for (book.Book book : stock ) {
			String[] data = book.toString().split(",");
			tableModel_books.addRow(data);
		}
		pnl_books.add(tbl_books, BorderLayout.CENTER);
	
		JButton btn_search = new JButton("Search");
		btn_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String chosenFilter = cmb_filter.getSelectedItem().toString().toUpperCase();
				String chosenKeyword = cmb_keyword.getSelectedItem().toString().toUpperCase();
				if (chosenFilter.equals("LANGUAGE")) {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(chosenKeyword));
					} catch (PatternSyntaxException pse) {
	                     System.out.println("Bad regex pattern");
					}
				}
				if (chosenFilter.equals("GENRE")) {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(chosenKeyword));
					} catch (PatternSyntaxException pse) {
	                     System.out.println("Bad regex pattern");
					}
				} 
				if (chosenFilter.equals("TYPE")) {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(chosenKeyword));
					} catch (PatternSyntaxException pse) {
	                     System.out.println("Bad regex pattern");
					}
				} 
				if (chosenFilter.equals("ALL")) {
					try {
						sorter.setRowFilter(RowFilter.regexFilter("ENGLISH|FRENCH"));
						/*	Seeing there is only two languages, a regex OR clause is used to return all books that are
						 * 	either French or English. This isn't the most acceptable way to go about getting all books but it was the
						 * 	first solution I came to -- will come back and find a better way to do this if I have time.
						 */
					} catch (PatternSyntaxException pse) {
	                     System.out.println("Bad regex pattern");
					}
				}
			}
		});
		btn_search.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btn_search.setBounds(350, 6, 70, 20);
		pnl_searchBar.add(btn_search);	
		
		JLabel lbl_displayBooks = new JLabel("ISBN :: BookType :: Title:: Language :: Genre :: Release Date :: Price :: Qty. in stock :: Length :: format/condition");
		lbl_displayBooks.setLabelFor(tbl_books);
		lbl_displayBooks.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_displayBooks.setForeground(new Color(255, 255, 255));
		pnl_books.add(lbl_displayBooks, BorderLayout.NORTH);
		
		JLabel lbl_ISBNprompt = new JLabel("ISBN:");
		lbl_ISBNprompt.setLabelFor(txt_ISBN);
		lbl_ISBNprompt.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ISBNprompt.setBounds(132, 689, 46, 14);
		frmBookshopManagement.getContentPane().add(lbl_ISBNprompt);
		
		JLabel lbl_qtyPrompt = new JLabel("Quanity:");
		lbl_qtyPrompt.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_qtyPrompt.setBounds(122, 658, 59, 14);
		frmBookshopManagement.getContentPane().add(lbl_qtyPrompt);
		
		txt_qty = new JTextField();
		lbl_qtyPrompt.setLabelFor(txt_qty);
		txt_qty.setColumns(10);
		txt_qty.setBounds(251, 655, 60, 20);
		txt_qty.setText("1");
		frmBookshopManagement.getContentPane().add(txt_qty);
		
		JButton btn_addQty = new JButton("+");
		btn_addQty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// increment quantity value
				int qty = Integer.parseInt(txt_qty.getText());
				qty++;
				txt_qty.setText(String.valueOf(qty));
				System.out.println("Incrementing quantity of chosen book");
			}
		});
		btn_addQty.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btn_addQty.setBounds(321, 653, 50, 25);
		frmBookshopManagement.getContentPane().add(btn_addQty);
		
		JButton btn_minusQty = new JButton("-");
		btn_minusQty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// decrease quantity value
				int qty = Integer.parseInt(txt_qty.getText());
				if (qty > 1) {
					qty--;
					txt_qty.setText(String.valueOf(qty));
					System.out.println("Decreasing quantity of chosen book");
				}
			}
		});
		btn_minusQty.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btn_minusQty.setBounds(191, 653, 50, 25);
		frmBookshopManagement.getContentPane().add(btn_minusQty);
		
		JButton btn_addToBasket = new JButton("Add to basket");
		btn_addToBasket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// add item to basket
				System.out.println("Adding item to basket");
				String ISBN = txt_ISBN.getText();
				int qty = 0;
				try {
					qty = Integer.parseInt(txt_qty.getText());
				} catch (NumberFormatException  ex) {
					ex.getStackTrace();
					System.out.println(ex.getMessage());
				}
				List<book.Book> stock = new ArrayList<book.Book>(); 
				try {
					stock = textfile_handler.Stock.getStock();
					for (book.Book book : stock) {
						if (ISBN.equals(book.getISBN())) {
							if (book.getQuantity() >= qty) {
								double price = book.getPriceAsDouble();
								System.out.println("add check");
								customer.addToBasket(ISBN, qty, price);
								System.out.println(customer.getBasket().toString());
								ArrayList<user.BasketItem> basket = new ArrayList<>(customer.getBasket());	
								if (basket.size() > 0) {
									tableModel_basket.setRowCount(0);
									for (user.BasketItem item : basket) {
										tableModel_basket.addRow(new String [] {item.getISBN(), Integer.toString(item.getQuantity()), Double.toString(item.getPrice())});
									}
								}
							}
						}
					}
				} catch (Exception ex) {
					ex.getStackTrace();
					System.out.println(ex.getMessage());
				}
			}
		});


		btn_addToBasket.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_addToBasket.setBounds(393, 655, 114, 51);
		frmBookshopManagement.getContentPane().add(btn_addToBasket);
		
		JButton btn_paypal = new JButton("PayPal");
		btn_paypal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (customer.getBasketSize() > 0) {
					tableModel_basket.setRowCount(0);
					tableModel_books.setRowCount(0);
					System.out.println("Paying by paypal");	
					PayPalFrame payPalTransaction = new PayPalFrame(customer.getTotal());
					customer.transaction("PayPal");
					System.out.println(customer.getBasket());
					List<book.Book> stock = new ArrayList<book.Book>(); 
					try {
						stock = textfile_handler.Stock.getStock();		
						for (book.Book book : stock ) {
							String[] data = book.toString().split(",");
							tableModel_books.addRow(data);
						}
					} catch (Exception ex) {
						ex.getStackTrace();
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("Basket is empty, unable to process request - PAYPAL TRANSACTION");
				}
			}
		});
		
		btn_paypal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_paypal.setBounds(751, 650, 114, 50);
		frmBookshopManagement.getContentPane().add(btn_paypal);
		
		JButton btn_card = new JButton("Credit card");
		btn_card.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (customer.getBasketSize() > 0) {
					System.out.println("Paying by card");
					tableModel_basket.setRowCount(0);
					tableModel_books.setRowCount(0);
					System.out.println("Paying Card");	
					CardFrame cardTransaction = new CardFrame(customer.getTotal());
					customer.transaction("Credit Card");
					System.out.println(customer.getBasket());
					List<book.Book> stock = new ArrayList<book.Book>(); 
					try {
						stock = textfile_handler.Stock.getStock();		
						for (book.Book book : stock ) {
							String[] data = book.toString().split(",");
							tableModel_books.addRow(data);
						}
					} catch (Exception ex) {
						ex.getStackTrace();
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("Basket is empty, unable to process request - CARD PAYMENT");
				}
			}
		});
		btn_card.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_card.setBounds(875, 650, 114, 50);
		frmBookshopManagement.getContentPane().add(btn_card);
		
		JLabel lbl_payPrompt = new JLabel("Choose payment option:");
		lbl_payPrompt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_payPrompt.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_payPrompt.setBounds(761, 623, 155, 14);
		frmBookshopManagement.getContentPane().add(lbl_payPrompt);
		
		JButton btn_cancel = new JButton("Cancel basket");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (customer.getBasketSize() > 0) {
					tableModel_basket.setRowCount(0);
					customer.updateLog(Status_e.CANCELLED, null);
					customer.cancelBasket();
				} else {
					System.out.println("Basket is empty, unable to process request - CANCEL BASKET");
				}
			}
		});

		btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_cancel.setBounds(751, 711, 114, 25);
		frmBookshopManagement.getContentPane().add(btn_cancel);
		
		txt_contents = new JTextField();
		txt_contents.setBounds(10, 620, 731, 20);
		frmBookshopManagement.getContentPane().add(txt_contents);
		txt_contents.setColumns(10);
		
		JPanel pnl_titleBooks = new JPanel();
		pnl_titleBooks.setBackground(new Color(0, 0, 0));
		pnl_titleBooks.setBounds(10, 112, 731, 15);
		frmBookshopManagement.getContentPane().add(pnl_titleBooks);
		pnl_titleBooks.setLayout(null);
	}
	
	private static String[] getNames(Class<? extends Enum<?>> e) {
		/*	function which gets all names in an enum as a String[]
		 * 		https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as)-a-string
		*/
	    return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
	}
}
