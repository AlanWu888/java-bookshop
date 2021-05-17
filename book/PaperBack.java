package book;

import java.time.LocalDate;

public class PaperBack extends Book {
	private int pageCount;
	private String condition;
	
	public PaperBack (String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String condition) {
		super(ISBN, type, title, language, genre, releaseDate, price, quantity);
		this.pageCount = pageCount;
		this.condition = condition;
	}
	
	
	// getter methods
	public int getPageCount() {
		return this.pageCount;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	/**
	 * 	toString methods
	 * 	<p>
	 * 	toString gets prints the String for the customer user to see when they are browsing books
	 * 	
	 * 	@return the customer-readable string
	 * 	<p>
	 * 	toStringStock gets the String which will be written to the stock file
	 *	
	 *	@return the String to be written to Stock file
	 */
	@Override
	public String toString() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPrice() + ", " + getQuantity() + ", " + getPageCount() + " pages, " + getCondition() + " condition";
	}
	
	@Override
	public String toStringStock() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPriceAsDouble() + ", " + getQuantity() + ", " + getPageCount() + ", " + getCondition();
	}
}
