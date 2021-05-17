package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test_ScrollBookDisplay implements ActionListener {
	private static JButton[] btn = null;	
	HashMap<String,JButton> buttonCache = new HashMap<String,JButton>();
	
	public Test_ScrollBookDisplay() {
		List<book.Book> stock = new ArrayList<book.Book>(); 
		try {
			stock = textfile_handler.Stock.getStock();		
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
		JFrame frame = new JFrame();

        final int FRAME_WIDTH = 1000;
        final int FRAME_HEIGHT = 1000;
        final int COMPONENT_WIDTH = 900;
        final int COMPONENT_HEIGHT = 200;
        final int COMPONENT_SPACING = 5;
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Home Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Top Panel
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBackground(Color.LIGHT_GRAY);
        p1.setPreferredSize(new Dimension(950, 100));

        JLabel l1 = new JLabel("All Books");
        l1.setForeground(Color.BLACK);
        l1.setPreferredSize(new Dimension(900, 50));
        l1.setFont(l1.getFont().deriveFont(30.0f));

        p1.add(l1);

        // Content Panel
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(-1, 1));
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setPreferredSize(new Dimension(950, stock.size()*(COMPONENT_HEIGHT + COMPONENT_SPACING)));
        p2.setAutoscrolls(true);

        JScrollPane scrollPane = new JScrollPane(p2);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 950, 800);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(950, 800));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btn = new JButton[stock.size()];
        int counter = 0;
        for (book.Book book : stock) {
        	JPanel sp1 = new JPanel();
            sp1.setLayout(null);
            sp1.setBackground(Color.WHITE);
            sp1.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));
            sp1.setBorder(new LineBorder(Color.RED));
            
            JLabel title = new JLabel(book.getTitle(), SwingConstants.LEFT);
            title.setBounds(30, 10, 400, 50);
            title.setFont(new Font(null, Font.PLAIN, 20));
            sp1.add(title);
            
            JLabel type = new JLabel(book.getBookType().toString() + " VERSION", SwingConstants.LEFT);
            type.setBounds(30, 30, 400, 50);
            type.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(type);
            
            JLabel ISBN = new JLabel("ISBN: " + book.getISBN(), SwingConstants.CENTER);
            ISBN.setBounds(720, 140, 150, 50);
            ISBN.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(ISBN);
            
            String strGenre = book.getGenre().toString().toLowerCase();
            strGenre = strGenre.substring(0, 1).toUpperCase() + strGenre.substring(1);
            JLabel genre = new JLabel(strGenre, SwingConstants.LEFT);
            genre.setBounds(30, 140, 400, 50);
            genre.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(genre);
            
            String strLang = book.getLanguage().toString().toLowerCase();
            strLang = strLang.substring(0, 1).toUpperCase() + strLang.substring(1) + " edition";
            JLabel language = new JLabel(strLang, SwingConstants.LEFT);
            language.setBounds(200, 140, 400, 50);
            language.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(language);
            
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JLabel price = new JLabel(formatter.format(book.getPriceAsDouble()));
            price.setBounds(750, 20, 400, 50);
            price.setFont(new Font(null, Font.PLAIN, 30));
            sp1.add(price);
           
            JLabel quantity = new JLabel(String.valueOf(book.getQuantity()) + " in Stock", SwingConstants.CENTER);
            quantity.setBounds(720, 95, 150, 20);
            quantity.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(quantity);

            JLabel release = new JLabel("Released: " + String.valueOf(book.getReleaseDate()), SwingConstants.LEFT);
            release.setBounds(370, 140, 400, 50);
            release.setFont(new Font(null, Font.PLAIN, 12));
            sp1.add(release);
            
            btn[counter] = new JButton("Add to basket [" + counter + "]");
            btn[counter].addActionListener(this);
            btn[counter].setFont(new Font(null, Font.PLAIN, 12));
            btn[counter].setBounds(720, 75, 150, 20);
            String contents = book.getISBN() + "||" + String.valueOf(book.getPrice()) + "||" + String.valueOf(book.getQuantity());
            btn[counter].setActionCommand(contents);
            buttonCache.put(contents, btn[counter]);	// https://stackoverflow.com/questions/7867834/get-button-name-from-actionlistener
            sp1.add(btn[counter]);
            counter++;
            
            p2.add(sp1);
        }

        // contentPane.add(p2);
        contentPane.add(p1, BorderLayout.NORTH);
        // frame.add(p2);
        // frame.setContentPane(contentPane);
        frame.add(contentPane);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    String contents = ((JButton) e.getSource()).getActionCommand();
	    JButton button = buttonCache.get(contents);
	    if (null != button) {
	        System.out.println(contents);
	        // add the split result of contents to basket, also need to remove from stock
	    }
	}
		
}
