package edu.tju.ina.seeworld.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getFormattedDateString(Timestamp ts) {
		return df.format(new Date(ts.getTime()));
	}

	/**
	 * A date util to get the current date and set the time to 00:00:00.000,
	 * used in the compare of addTime
	 * 
	 * @return A date object whose date is today and whose time is 00:00:00.000
	 */
	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
