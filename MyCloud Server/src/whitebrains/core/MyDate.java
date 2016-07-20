package whitebrains.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	private static Date date = new Date();
	private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");

	public static String getData() {
		return format.format(date);
	}

}
