package book;

import java.text.NumberFormat;
import java.time.LocalDate;

public abstract class Book {
	private String ISBN, title;
	private Language_e language;
	private Genre_e genre;
	private int quantity;
	private double price;
	private LocalDate releaseDate;		// dd-MM-yyyy
	private BookTypes_e type;
	
	// constructor
	public Book (String ISBN, BookTypes_e type, String title, Language_e language, Genre_e genre, LocalDate releaseDate, double price, int quantity) {
		this.ISBN = ISBN;
		this.type = type;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.price = price;
		this.quantity = quantity;
	}
	
	public abstract String toString();
	public abstract String toStringStock();
	
	// setter methods
	public void setQuantity(int stock, boolean add) {
		if (add == false) {
			this.quantity = this.quantity - stock;
		} else {
			this.quantity = this.quantity + stock;
		}
	}
	
	// getter methods
	public String getISBN() {
		return this.ISBN;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Language_e getLanguage() {
		return this.language;
	}
	
	public Genre_e getGenre() {
		return this.genre;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getPriceAsDouble() {
		return this.price;
	}
	
	public String getPrice() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(this.price);
	}
	
	public String getReleaseDate() {
		String strDate = (this.releaseDate).toString();
		return textfile_handler.StringToDateFormatter.convDateToStr(strDate);
	}
	
	public BookTypes_e getBookType() {
		return this.type;
	}
}	
