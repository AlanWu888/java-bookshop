package user;

public abstract class User {
	private UserType_e userType;	// enum used to represent the available user types
	private Address addr;			// Address class used to represent the user's address
	private String surname, username;
	private int userID;
	
	// constructor
	public User(int userID, String username, String surname, Address addr, UserType_e userType) {
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.addr = addr;
		this.userType = userType;
	}
	
	public abstract String toString();
	
	// getter methods
	public String getPostcode() {
		return addr.postcode;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public Address getAddr() {
		return this.addr;
	}
	
	public UserType_e getUserType() {
		return this.userType;
	}

	
	// methods
	public void viewBooks() {
		// allows user to view books, sorted by price (low -> high)
		System.out.println("view books");
	}
	

}
