package user;

public class Address {
	/**
	 * Each user's address is stored as an object of the Address class
	 */
	protected String doorNo, postcode, city;
	// doorNo is a string because sometimes Door Numbers contain letters in them, i.e 14A/ 14B
	
	public Address(String doorNo, String postcode, String city) {
		this.doorNo = doorNo;
		this.postcode = postcode;
		this.city = city;
	}
	
	// getter methods
	private String getDoorNo() {
		return doorNo;
	}
	
	private String getPostcode() {
		return postcode;
	}
	
	private String getCity() {
		return city;
	}
	
	// toString method
	public String toString() {
		return getDoorNo() + ", " + getCity() + ", " + getPostcode();
	}
}
