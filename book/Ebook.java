package book;

import java.time.LocalDate;

public class Ebook extends Book{
	private int pageCount;
	private String format;
	
	public Ebook(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String format) {
		/**
		 * 	Constructor metod to make Ebook oject
		 */
		super(ISBN, type, title, language, genre, releaseDate, price, quantity);
		this.pageCount = pageCount;
		this.format = format;
	}
	
	// getter methods
	public int getPageCount() {
		return this.pageCount;
	}
	
	public String getFormat() {
		return this.format;
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
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPrice() + ", " + getQuantity() + ", " + getPageCount() + " pages, " + getFormat() + " format";
	}
	
	@Override
	public String toStringStock() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPriceAsDouble() + ", " + getQuantity() + ", " + getPageCount() + ", " + getFormat();
	}
}
