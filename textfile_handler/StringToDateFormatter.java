package textfile_handler;

import java.util.Arrays;

public class StringToDateFormatter {
	
	// dd-MM-yyyy to yyyy-MM-dd
	public static String convStrToDate(String in) {
		String[] inComponents = in.split("-");
		String dd, MM, yyyy;
		dd = inComponents[0];
		MM = inComponents[1];
		yyyy = inComponents[2];
		
		String out = yyyy + "-" + MM + "-" + dd;
		
		return out;
	}
	
	// yyyy-MM-dd to dd-MM-yyyy
	public static String convDateToStr(String in) {
		String[] inComponents = in.split("-");
		
		String dd, MM, yyyy;
		yyyy = inComponents[0];
		MM = inComponents[1];
		dd = inComponents[2];
		
		String out = dd + "-" + MM + "-" + yyyy;
		
		return out;
	}
}
