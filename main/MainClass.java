package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import book.BookTypes_e;
import book.Genre_e;
import book.Language_e;
import user.Admin;

public class MainClass {
	
	public static void main(String[] args) {
		int s1 = 23;
		int s2 = 45;
		String double_asString = s1 + "." + s2;
		Double myDouble = Double.valueOf(double_asString);
		System.out.println(myDouble);
		
		user.Address addr = new user.Address("54", "SW9 7PU", "London");
		/*
		user.Customer first = new user.Customer(101, "j", "a", addr, null);
		first.addToBasket("11223344", 1, 17.5);
		first.addToBasket("11224455", 2, 30.25);
		System.out.println(first.getBasketSize());
		ArrayList<user.BasketItem> basket = new ArrayList<>(first.getBasket());
		System.out.println(first.getBasket().toString());
		first.payPortal("Credit Card", "123456", "123", "a@.c" , basket);
		// addPaperback(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String condition) {
		*/
		user.Admin admin = new user.Admin(102, "admin", "a", addr, null);
		LocalDate release = LocalDate.of(2020, 11, 17);
		admin.addNewPaperback("11223344", BookTypes_e.PAPERBACK, "A Promised Land", Language_e.ENGLISH, Genre_e.BIOGRAPHY, release, 17.5, 1, 768, "new");
		admin.addNewPaperback("12345678", BookTypes_e.PAPERBACK, "ALANS TEST", Language_e.ENGLISH, Genre_e.BIOGRAPHY, release, 17.5, 1, 768, "new");
	}
	
	/*
	public static void getStock() {	// load the contents of stock file
		try {
			List<book.Book> stockContents = new ArrayList<book.Book>(textfile_handler.Stock.getStock());
			for(book.Book book : stockContents) {
				System.out.println(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void getUsers() {	// load the contents of user file
		try {
			List<user.User> userContents = new ArrayList<user.User>(textfile_handler.UserAccounts.getUsers());
			for(user.User user : userContents) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void getLog() {	// load the contents of log file
		try {
			List<textfile_handler.LogEntry> logContents = new ArrayList<textfile_handler.LogEntry>(textfile_handler.ActivityLog.getLog());
			for(textfile_handler.LogEntry entry : logContents) {
				System.out.println(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}		
	}
	*/
}
