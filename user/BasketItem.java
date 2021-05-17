package user;

public class BasketItem {
	/**
	 * 	Each item in the customer's basket is an object belonging to the BasketItem class
	 * 	<p>
	 * 	This approach has been used as it means less information is moved around the system for
	 * 	the adding to basket mechanism
	 */
	private String ISBN;
	private Integer quantity;
	private double price;
	
	// constructor
	public BasketItem(String ISBN, Integer quantity, double price) {
		this.ISBN = ISBN;
		this.quantity = quantity;
		this.price = price;
	}
	
	// toString
	public String toString() {
		return this.ISBN + " | " + this.quantity + " | " + this.price;
	}
	
	// getter methods
	public String getISBN() {
		return this.ISBN;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getPrice() {
		return this.price;
	}
}
