package video.rental.demo.util;

import java.util.Calendar;
import java.util.Date;

public class VideoRentalUtil {
	public static Calendar parseDate(Date date) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		return calDate;
	}
}
