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
	
	// toString method
	@Override
	public String toString() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPrice() + ", " + getQuantity() + ", " + getLength() + " hours, " + getFormat() + " format";
	}
	
	@Override
	public String toStringStock() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPriceAsDouble() + ", " + getQuantity() + ", " + getLength() + ", " + getFormat();
	}
}
