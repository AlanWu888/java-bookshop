package user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import textfile_handler.Status_e;

public class Customer extends User {
	private ArrayList<BasketItem> basket = new ArrayList<>();	/// ArrayList of String to represent the customer's shopping basket
	
	// constructor
	public Customer(int userID, String username, String surname, Address addr, UserType_e userType) {
		super(userID, username, surname, addr, userType);
		this.basket = new ArrayList<BasketItem>();
	}
	
	// toString
	public String toString() {
		return getUserID() + ", " + getUsername() + ", " + getSurname() + ", " + getAddr() + ", " + getUserType() + ", " + getBasket();
	}
	
	// getter methods
	public ArrayList<BasketItem> getBasket() {
		return this.basket;
	}
	
	public int getBasketSize() {
		return this.basket.size();
	}
	
	public double getTotal() {
		double myTotal = 0;
		for (BasketItem item : basket) {
			myTotal += item.getPrice();
		}
		return myTotal;
	}
	// Methods
	public void addToBasket(String ISBN, int quantity, double price) {
		System.out.println("Adding to Basket");
		BasketItem newItem = new BasketItem(ISBN, quantity, price);
		basket.add(newItem);
	}
	
	public void cancelBasket() {
		System.out.println("Removing all from basket");
		basket.removeAll(basket);
	}
	
	public void transaction(String payment) {
		for (int i = 0; i<basket.size(); i++) {
			String ISBN = basket.get(i).getISBN();
			int quantity = basket.get(i).getQuantity();
			double price = basket.get(i).getPrice();
			textfile_handler.Stock.removeFromStock(ISBN, quantity, price);
		}
		updateLog(Status_e.PURCHASED, payment);
		basket.removeAll(basket);
	}
	
	/*	separate sub for each filter of search as it makes program more efficient - in my opinion
	 * 		alternative way would have been to use a lot of if statements in for clause in try clause
	 */
	public List<book.Book> getResLangSearch(String keyword) {
		 // keyword	- keyword to search by ( check book package enum types for constants )
		List<book.Book> filteredStock = new ArrayList<book.Book>();
		try {
			List<book.Book> stockContents = new ArrayList<book.Book>(textfile_handler.Stock.getStock()); 
			for (book.Book book : stockContents) {
				String currentKeyword = book.getLanguage().toString(); 
				if (currentKeyword == keyword) {
					filteredStock.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return filteredStock;
	}
	
	public List<book.Book> getResGenreSearch(String keyword) {
		 // keyword	- keyword to search by ( check book package enum types for constants )
		List<book.Book> filteredStock = new ArrayList<book.Book>();
		try {
			List<book.Book> stockContents = new ArrayList<book.Book>(textfile_handler.Stock.getStock()); 
			for (book.Book book : stockContents) {
				String currentKeyword = book.getGenre().toString(); 
				if (currentKeyword == keyword) {
					filteredStock.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return filteredStock;
	}
	
	public List<book.Book> getResTypeSearch(String keyword) {
		 // keyword	- keyword to search by ( check book package enum types for constants )
		List<book.Book> filteredStock = new ArrayList<book.Book>();
		try {
			List<book.Book> stockContents = new ArrayList<book.Book>(textfile_handler.Stock.getStock()); 
			for (book.Book book : stockContents) {
				String currentKeyword = book.getBookType().toString(); 
				if (currentKeyword == keyword) {
					filteredStock.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return filteredStock;
	}
	
	public void updateLog(Status_e status, String paymentMethod) {
		// updates the system log based off user actions
		// userID, postcode, ISBN, price, quantity bought, payment method, status, log date
		// Payment status: purchased/ cancelled
		int userID = this.getUserID();
		String postcode = this.getPostcode();
		LocalDate logDate = LocalDate.now();
		System.out.println("updating log");
		System.out.println(basket);
		for (int i = 0; i<basket.size(); i++) {
			String ISBN = basket.get(i).getISBN();
			int quantity = basket.get(i).getQuantity();
			double price = basket.get(i).getPrice();
			
			// create log object
			textfile_handler.LogEntry logEntry = new textfile_handler.LogEntry(userID, postcode, ISBN, price, quantity, status, paymentMethod, logDate);
			// write log object to text file
			textfile_handler.ActivityLog.writeToLog(logEntry);
		}
		
		// public LogEntry(int userID, String postcode, String ISBN, double price, int quantity, Status_e status, String paymentMethod, LocalDate logDate) 
	}
}
	

