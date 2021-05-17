package book;

import java.time.LocalDate;

public class Ebook extends Book{
	private int pageCount;
	private String format;
	
	public Ebook(String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity, int pageCount, String format) {
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
	
	@Override
	public String toString() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPrice() + ", " + getQuantity() + ", " + getPageCount() + " pages, " + getFormat() + " format";
	}
	
	@Override
	public String toStringStock() {
		return getISBN() + ", " + getBookType() + ", " + getTitle() + ", " + getLanguage() + ", " + getGenre() + ", " + getReleaseDate() + ", " + getPriceAsDouble() + ", " + getQuantity() + ", " + getPageCount() + ", " + getFormat();
	}
}
