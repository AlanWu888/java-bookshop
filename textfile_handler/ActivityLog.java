package textfile_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ActivityLog {
	static String LOG = "ActivityLog.txt";
	
	public static List<textfile_handler.LogEntry> getLog() throws FileNotFoundException {
		/**
		 * 	Getter method which returns contents of the log file as a List of LogEntry objects
		 * 	
		 * 	@return List<textfile_handler.LogEntry
		 */
		List<textfile_handler.LogEntry> logContents = new ArrayList<textfile_handler.LogEntry>();
		try {
			List<textfile_handler.LogEntry> listContents = new ArrayList<textfile_handler.LogEntry>();
			File logFile = new File(LOG);
			Scanner logScanner = new Scanner(logFile);
			
			textfile_handler.LogEntry obj_log = null;
			
			while (logScanner.hasNextLine()) {
				String[] details = logScanner.nextLine().split(",");
				int userID = Integer.parseInt(details[0].trim());
				String postcode = details[1].trim();
				String ISBN = details[2].trim();
				double price = Double.parseDouble(details[3].trim());
				int quantity = Integer.parseInt(details[4].trim());
				textfile_handler.Status_e status = textfile_handler.Status_e.valueOf(details[5].trim().toUpperCase());
				String paymentMethod = details[6].trim();
				
				String strDate = textfile_handler.StringToDateFormatter.convStrToDate(details[7].trim());	// date in text file has to be reformatted to work with LocalDate type
				LocalDate logDate = LocalDate.parse(strDate);	// parse String to LocalDate type
				
				obj_log = new textfile_handler.LogEntry(userID, postcode, ISBN, price, quantity, status, paymentMethod, logDate);
				listContents.add(obj_log);
			}
			logScanner.close();
			logContents = listContents;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return logContents;
	}
	
	public static void writeToLog(LogEntry logEntry) {
		/**
		 * 	void method which writes to the log file
		 * 	@param logEntry object called logEntry
		 */
		System.out.println("Writing to log:");
		System.out.println(logEntry);
		try(FileWriter fw = new FileWriter(LOG, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    System.out.println(logEntry);	// show log being written into text file
			} catch (IOException e) {
			    e.printStackTrace();
			    System.out.println(e.getMessage());
			}
	}
}

