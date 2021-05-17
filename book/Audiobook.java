package book;

import java.time.LocalDate;

public class Audiobook extends Book {
	private double listeningLength;
	private String format;
	
	public Audiobook (String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, double listeningLength, String format) {
		super(ISBN, type, title, language, genre, releaseDate, price, quantity);
		this.listeningLength = listeningLength;
		this.format = format;
	}
	
	
	// getter methods
	public double getLength() {
		return this.listeningLength;
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
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPrice() + ", " + getQuantity() + ", " + getLength() + " hours, " + getFormat() + " format";
	}
	
	@Override
	public String toStringStock() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPriceAsDouble() + ", " + getQuantity() + ", " + getLength() + ", " + getFormat();
	}
}
