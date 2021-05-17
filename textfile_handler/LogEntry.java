package textfile_handler;

import java.time.LocalDate;

public class LogEntry {
	private int userID, quantity;
	private String postcode, ISBN, paymentMethod, storedDate;
	private Status_e status;
	private double price;
	private LocalDate logDate;
	
	// constructor
	public LogEntry(int userID, String postcode, String ISBN, double price, int quantity, Status_e status, String paymentMethod, LocalDate logDate) {
		this.userID = userID;
		this.postcode = postcode;
		this.ISBN = ISBN;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.logDate = logDate;
		this.storedDate = textfile_handler.StringToDateFormatter.convDateToStr(logDate.toString());	// converts from LocalDate format into format stored in the log file
	}
	
	// toString Method
	public String toString() {
		return getUserID() + ", " + getPostcode() + ", " + getISBN() + ", " + getPrice() + ", " + getQuantity() + ", " + getStatus() + ", " + getPaymentMethod() + ", " + getLogDate();
	}
	
	// getter methods
	public int getUserID() {
		return this.userID;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public Status_e getStatus() {
		return this.status;
	}
	
	public String getPaymentMethod() {
		return this.paymentMethod;
	}
	
	public String getLogDate() {
		return this.storedDate;
	}

}
