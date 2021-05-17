package textfile_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UserAccounts {
	public static List<user.User> getUsers() throws FileNotFoundException {
		List<user.User> allUsers = new ArrayList<user.User>();
		String USER = "UserAccounts.txt";
		try {
			List<user.User> listUsers = new ArrayList<user.User>();
			File userFile = new File(USER);
			Scanner userScanner = new Scanner(userFile);
			
			user.Admin obj_admin = null;
			user.Customer obj_customer = null;
			
			while (userScanner.hasNextLine()) {
				String[] details = userScanner.nextLine().split(",");
				// userID, userame, surname, addr.houseno, addr.postcode, addr.city, role
				int userID = Integer.parseInt(details[0].trim());
				String username = details[1].trim();
				String surname = details[2].trim();
				// fill address object for each instance of user:
				String doorno = details[3].trim();
				String postcode = details[4].trim();
				String city = details[5].trim();
				user.Address addr = new user.Address(doorno, postcode, city);
				user.UserType_e userType = user.UserType_e.valueOf(details[6].trim().toUpperCase());	// parse String to enum type
				
				// conditions based off user's type
				if (userType == user.UserType_e.ADMIN) {
					obj_admin = new user.Admin(userID, username, surname, addr, userType);
					listUsers.add(obj_admin);
				}
				if (userType == user.UserType_e.CUSTOMER) {
					obj_customer = new user.Customer(userID, username, surname, addr, userType);
					listUsers.add(obj_customer);
				}
			}
			userScanner.close();
			allUsers = listUsers;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return allUsers;
	}
}
