package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	
	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	
	public static String getCurrentDateForFileName(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(new Date());
	}
	
}
