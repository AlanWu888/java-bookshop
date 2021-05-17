package user;

import java.time.LocalDate;

import book.BookTypes_e;
import book.Genre_e;
import book.Language_e;
import textfile_handler.Stock;

public class Admin extends User {
	// Constructor
	public Admin(int userID, String username, String surname, Address addr, UserType_e userType) {
		super(userID, username, surname, addr, userType);
	}
	
	// toString
	// toString
	public String toString() {
		return getUserID() + ", " + getUsername() + ", " + getSurname() + ", " + getAddr() + ", " + getUserType();
	}
	
	// Methods
	public void addNewBook(BookTypes_e type) {
		/* 	parameters:
		 *		String ISBN, String title, Language_e language, genre_e genre, Date releaseDate, double RRP, int quantity
		 *	>> Make call to book package?
		 */	

		// String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, int quantity, double price, LocalDate releaseDate
		if (type == BookTypes_e.PAPERBACK) {
			System.out.println("Adding a PAPERBACK");
		}
	}
	
	public void addNewPaperback(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String condition) {
		System.out.println("Adding a PAPERBACK");
		book.PaperBack obj_paperback = new book.PaperBack(ISBN, type, title, language, genre, releaseDate, price, quantity, pageCount, condition);
		Stock.addBook(obj_paperback);
	}
	
	public static void addAudioBook(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, double listeningLength, String format) {
		System.out.println("Adding an AUDIOBOOK");
		book.Audiobook obj_audiobook = new book.Audiobook(ISBN, type, title, language, genre, releaseDate, price, quantity, listeningLength, format);
		Stock.addBook(obj_audiobook);
	}
	
	public static void addEbook(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String format) {
		System.out.println("Adding an EBOOK");
		book.Ebook obj_ebook = new book.Ebook(ISBN, type, title, language, genre, releaseDate, price, quantity, pageCount, format);
		Stock.addBook(obj_ebook);
	}
}
