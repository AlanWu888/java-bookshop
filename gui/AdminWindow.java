package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWindow {
	private JTable tbl_books;
	private String[] columnHeaders_books = {"ISBN", "Type", "Title", "Language", "Genre", "Release", "Price", "Qty. in Stock"," ", " "};
	private DefaultTableModel tableModel_books = new DefaultTableModel(columnHeaders_books, 0);
	
	private JLabel lbl_ISBN = new JLabel();
	private JLabel lbl_type = new JLabel();
	private JLabel lbl_bookTitle = new JLabel();
	private JLabel lbl_language = new JLabel();
	private JLabel lbl_genre = new JLabel();	
	private JLabel lbl_release = new JLabel();
	private JLabel lbl_price = new JLabel();
	private JLabel lbl_qty = new JLabel();
	private JLabel lbl_length = new JLabel();
	private JLabel lbl_cFormat = new JLabel();

	private JFrame frame;
	private final JLabel lbl_ISBN_header = new JLabel("ISBN:");

	/**
	 * Create the application.
	 */
	public AdminWindow(Integer userID) {
		initialize(userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer userID) {
		List<book.Book> stock = new ArrayList<book.Book>(); 
		try {
			stock = textfile_handler.Stock.getStock();		
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
		frame = new JFrame();
		frame.setTitle("Bookshop Management");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1080, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lbl_displayBooks_1 = new JLabel("Displaying books:");
		lbl_displayBooks_1.setBounds(10, 112, 731, 15);
		frame.getContentPane().add(lbl_displayBooks_1);
		lbl_displayBooks_1.setBackground(new Color(0, 0, 0));
		lbl_displayBooks_1.setForeground(Color.WHITE);
		lbl_displayBooks_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel pnl_titleBooks = new JPanel();
		pnl_titleBooks.setBackground(new Color(0, 0, 0));
		pnl_titleBooks.setBounds(10, 112, 697, 15);
		frame.getContentPane().add(pnl_titleBooks);
		pnl_titleBooks.setLayout(null);
		
		JPanel pnl_title = new JPanel();
		pnl_title.setBackground(new Color(0, 0, 139));
		pnl_title.setForeground(Color.LIGHT_GRAY);
		pnl_title.setBounds(0, 0, 1074, 60);
		frame.getContentPane().add(pnl_title);
		pnl_title.setLayout(null);
		
		JLabel lbl_WelcomeUser = new JLabel("Welcome Admin user");
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
				frame.dispose();
			}
		});
		btn_logout.setBounds(962, 10, 90, 31);
		pnl_title.add(btn_logout);
		
		JPanel pnl_searchBar = new JPanel();
		pnl_searchBar.setLayout(null);
		pnl_searchBar.setForeground(new Color(153, 0, 51));
		pnl_searchBar.setBackground(Color.RED);
		pnl_searchBar.setBounds(0, 60, 1074, 41);
		frame.getContentPane().add(pnl_searchBar);
		
		JButton btn_addBook = new JButton("Add to stock");
		btn_addBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddBook_Frame frm_addBook = new AddBook_Frame(userID);
			}
		});
		btn_addBook.setToolTipText("");
		btn_addBook.setBounds(932, 5, 120, 31);
		pnl_searchBar.add(btn_addBook);
		
		JButton btn_refreshStock = new JButton("Refresh stock");
		btn_refreshStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("refreshing stock");
				tableModel_books.setRowCount(0);
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
			}
		});
		btn_refreshStock.setToolTipText("");
		btn_refreshStock.setBounds(801, 5, 120, 31);
		pnl_searchBar.add(btn_refreshStock);
		
		JPanel pnl_books = new JPanel();
		pnl_books.setBackground(new Color(0, 0, 0));
		pnl_books.setForeground(new Color(0, 0, 0));
		pnl_books.setBounds(10, 127, 697, 622);
		frame.getContentPane().add(pnl_books);
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
					List<String> myContents = new ArrayList<String>();   
					int row = tbl_books.rowAtPoint(evt.getPoint());
					for (int i = 0; i<columnHeaders_books.length; i++) {
						myContents.add(tbl_books.getModel().getValueAt(row, i).toString());
					}
					lbl_ISBN.setText(myContents.get(0));
					lbl_type.setText(myContents.get(1));
					lbl_bookTitle.setText(myContents.get(2));
					lbl_language.setText(myContents.get(3));
					lbl_genre.setText(myContents.get(4));
					lbl_release.setText(myContents.get(5));
					lbl_price.setText(myContents.get(6));
					lbl_qty.setText(myContents.get(7));
					lbl_length.setText(myContents.get(8));
					lbl_cFormat.setText(myContents.get(9));
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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		pnl_books.add(panel, BorderLayout.NORTH);
		
		JLabel lbl_displayBooks = new JLabel("ISBN :: BookType :: Title:: Language :: Genre :: Release Date :: Price :: Qty. in stock :: Length :: format/condition");
		lbl_displayBooks.setLabelFor(tbl_books);
		lbl_displayBooks.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_displayBooks.setForeground(new Color(255, 255, 255));
		pnl_books.add(lbl_displayBooks, BorderLayout.NORTH);
		
		JPanel pnl_expandedView = new JPanel();
		pnl_expandedView.setBackground(Color.LIGHT_GRAY);
		pnl_expandedView.setBounds(717, 112, 336, 637);
		frame.getContentPane().add(pnl_expandedView);
		pnl_expandedView.setLayout(null);
		
		JPanel pbl_expandedHeader = new JPanel();
		pbl_expandedHeader.setBounds(0, 0, 336, 24);
		pbl_expandedHeader.setBackground(Color.BLACK);
		pnl_expandedView.add(pbl_expandedHeader);
		
		JLabel lbl_expandedView = new JLabel("Book information");
		lbl_expandedView.setForeground(Color.WHITE);
		lbl_expandedView.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_expandedView.setHorizontalAlignment(SwingConstants.LEFT);
		pbl_expandedHeader.add(lbl_expandedView);
		lbl_ISBN_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ISBN_header.setBounds(10, 35, 316, 14);
		pnl_expandedView.add(lbl_ISBN_header);
		
		JLabel lbl_type_header = new JLabel("Type:");
		lbl_type_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_type_header.setBounds(10, 80, 316, 14);
		pnl_expandedView.add(lbl_type_header);
		
		lbl_ISBN.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ISBN.setBounds(10, 50, 316, 14);
		pnl_expandedView.add(lbl_ISBN);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 316, 10);
		pnl_expandedView.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 115, 316, 10);
		pnl_expandedView.add(separator_1);
		
		lbl_type.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_type.setBounds(10, 95, 316, 14);
		pnl_expandedView.add(lbl_type);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 160, 316, 10);
		pnl_expandedView.add(separator_1_1);
		
		JLabel lbl_bookTitle_header = new JLabel("Title:");
		lbl_bookTitle_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_bookTitle_header.setBounds(10, 125, 316, 14);
		pnl_expandedView.add(lbl_bookTitle_header);
		
		lbl_bookTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_bookTitle.setBounds(10, 140, 316, 14);
		pnl_expandedView.add(lbl_bookTitle);
		
		JLabel lbl_language_header = new JLabel("Language:");
		lbl_language_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_language_header.setBounds(10, 170, 316, 14);
		pnl_expandedView.add(lbl_language_header);
		
		lbl_language.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_language.setBounds(10, 185, 316, 14);
		pnl_expandedView.add(lbl_language);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(10, 205, 316, 10);
		pnl_expandedView.add(separator_1_1_1);
		
		JLabel lbl_genre_header = new JLabel("Genre:");
		lbl_genre_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_genre_header.setBounds(10, 215, 316, 14);
		pnl_expandedView.add(lbl_genre_header);
		
		lbl_genre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_genre.setBounds(10, 230, 316, 14);
		pnl_expandedView.add(lbl_genre);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setBounds(10, 250, 316, 10);
		pnl_expandedView.add(separator_1_1_1_1);
		
		JLabel lbl_release_header = new JLabel("Release date:");
		lbl_release_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_release_header.setBounds(10, 260, 316, 14);
		pnl_expandedView.add(lbl_release_header);
		
		lbl_release.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_release.setBounds(10, 275, 316, 14);
		pnl_expandedView.add(lbl_release);
		
		JSeparator separator_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1.setBounds(10, 295, 316, 10);
		pnl_expandedView.add(separator_1_1_1_1_1);
		
		JLabel lbl_price_header = new JLabel("Price:");
		lbl_price_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_price_header.setBounds(10, 305, 316, 14);
		pnl_expandedView.add(lbl_price_header);
		
		lbl_price.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_price.setBounds(10, 320, 316, 14);
		pnl_expandedView.add(lbl_price);
		
		JSeparator separator_1_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1_1.setBounds(10, 340, 316, 10);
		pnl_expandedView.add(separator_1_1_1_1_1_1);
		
		JLabel lbl_qty_header = new JLabel("Quantity in stock:");
		lbl_qty_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_qty_header.setBounds(10, 350, 316, 14);
		pnl_expandedView.add(lbl_qty_header);
		
		lbl_qty.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_qty.setBounds(10, 365, 316, 14);
		pnl_expandedView.add(lbl_qty);
		
		JSeparator separator_1_1_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1_1_1.setBounds(10, 385, 316, 10);
		pnl_expandedView.add(separator_1_1_1_1_1_1_1);
		
		JLabel lbl_length_header = new JLabel("Length of book:");
		lbl_length_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_length_header.setBounds(10, 395, 316, 14);
		pnl_expandedView.add(lbl_length_header);
		
		lbl_length.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_length.setBounds(10, 410, 316, 14);
		pnl_expandedView.add(lbl_length);
		
		JSeparator separator_1_1_1_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1_1_1_1.setBounds(10, 430, 316, 10);
		pnl_expandedView.add(separator_1_1_1_1_1_1_1_1);
		
		JLabel lbl_cFormat_header = new JLabel("Condition / Format:");
		lbl_cFormat_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_cFormat_header.setBounds(10, 440, 316, 14);
		pnl_expandedView.add(lbl_cFormat_header);	

		lbl_cFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_cFormat.setBounds(10, 455, 316, 14);
		pnl_expandedView.add(lbl_cFormat);
	}
}
