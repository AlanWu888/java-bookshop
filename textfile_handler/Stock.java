package textfile_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Stock {
	static String STOCK = "Stock.txt";	// const String for defining text file which holds stock data
	
	public static List<book.Book> getStock() throws FileNotFoundException {
		/**
		 * 	Getter method which returns all books in the stock text file as a list of Book objects
		 * 	@return List of Book objects (from the book package)
		 */
		List<book.Book> stockContents = new ArrayList<book.Book>();
		try {
			List<book.Book> listStock = new ArrayList<book.Book>();
			File stockFile = new File(STOCK);
			Scanner stockScanner = new Scanner(stockFile);
			
			book.Ebook obj_eBook = null;
			book.Audiobook obj_audioBook = null;
			book.PaperBack obj_paperBack = null;
			
			while (stockScanner.hasNextLine()) {
				String[] details = stockScanner.nextLine().split(",");
				String ISBN = details[0].trim();
				book.BookTypes_e type = book.BookTypes_e.valueOf((details[1].trim()).toUpperCase());	// get the type of book via enum
				String title = details[2].trim();
				book.Language_e language = book.Language_e.valueOf((details[3].trim()).toUpperCase());	// parse String to enum type
				String strGenre = (details[4].trim().replaceAll(" ", "_")).toUpperCase();	// changes spaces to underscore so textfile contents can be evaluated to enum type
				book.Genre_e genre = book.Genre_e.valueOf(strGenre);	// parse String to enum type
				
				String strDate = textfile_handler.StringToDateFormatter.convStrToDate(details[5].trim());	// date in text file has to be reformatted to work with LocalDate type
				LocalDate releaseDate = LocalDate.parse(strDate);	// parse String to LocalDate type
				
				double RRP = Double.parseDouble(details[6].trim());
				int quantity = Integer.parseInt(details[7].trim());
				// conditions based off book's type
				if (type == book.BookTypes_e.PAPERBACK) {
					int pageCount = Integer.parseInt(details[8].trim());
					String condition = details[9].trim();
					obj_paperBack = new book.PaperBack(ISBN, type, title, language, genre, releaseDate, RRP, quantity, pageCount, condition);	// create paperBack object
					listStock.add(obj_paperBack);	// add paperBack object to arr list of books
				}
				if (type == book.BookTypes_e.AUDIOBOOK) {
					double listeningLength = Double.parseDouble(details[8].trim());
					String format = details[9].trim();
					obj_audioBook = new book.Audiobook(ISBN, type, title, language, genre, releaseDate, RRP, quantity, listeningLength, format);	// create audioBook object
					listStock.add(obj_audioBook);	// add audioBook object to arr list of books
				}
				if (type == book.BookTypes_e.EBOOK) {
					int pageCount = Integer.parseInt(details[8].trim());
					String format = details[9].trim();
					obj_eBook = new book.Ebook(ISBN, type, title, language, genre, releaseDate, RRP, quantity, pageCount, format);	// create eBook object
					listStock.add(obj_eBook);	// add ebook object to arr list of books
				}	
			}
			stockScanner.close();
			stockContents = listStock; // allows us to take listStock out of try clause
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return stockContents;	// return List<book.Book> :: List of book objects
	}
	
	public static void removeFromStock(String ISBN, int quantity) {
		/**
		 * 	void method which removes books from the stock text file one at a time
		 * 	<p>
		 * 	Books are removed by matching ISBN (assuming that every book is supposed to have unique ISBNs)
		 * 	and then depending on the quantity, the stock data will be removed
		 * 
		 * 	@param ISBN: String, quantity: int
		 */
		try {
			List<book.Book> stock = new ArrayList<book.Book>(getStock());
			for(book.Book book : stock) {
				if (book.getISBN().equals(ISBN)) {
					if (book.getQuantity() >= quantity) {
						if (book.getQuantity() == quantity) {
							stock.remove(book);
							System.out.println("Removal : " + book);
						} else {
							book.setQuantity(quantity, false);	// removes from the stock level
						}
						System.out.println("Books successfully removed from stock");
						writeStock(stock);
					} else {
						System.out.println("Book out of stock");
					}
				} 
				/*else {
					System.out.println("Book not found");
				}*/
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void addBook(book.Book newBook) {
		/**
		 * 	void method which allows Admin users to add books to the stock text file
		 * 	@param newBook: Book object
		 */
		try {
			List<book.Book> currentStock = new ArrayList<book.Book>(getStock());
			for (book.Book book : currentStock) {
				if (book.getISBN().equals(newBook.getISBN())) {		// check if book already exists on the system
					System.out.println("This book already exists in the system, adding quantity");
					book.setQuantity(newBook.getQuantity(), true);
					break;
				} else {	// if book isn't already in the system
					System.out.println("This book is currently not in the system");
					currentStock.add(newBook);
				}
				writeStock(currentStock);
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}		
	}
	
	public static void writeStock(List<book.Book> newStock) {
		/**
		 * 	void method which takes a list of book objects as a parameter to write to the stock text file.
		 * 	The newStock is a combination of old stock and new stock added to the system
		 * 	@param newStock: List<book.Book>
		 */
		try {
			FileWriter updateStock = new FileWriter(STOCK);
			for (book.Book book : newStock) {
				updateStock.write(book.toStringStock() + "\n");
			}
			updateStock.close();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
