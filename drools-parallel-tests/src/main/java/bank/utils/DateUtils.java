package bank.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static Date getDateMinusMonts(int months)
	{
		Calendar calendar= new GregorianCalendar();
		calendar.add(Calendar.MONTH, -months);
		return calendar.getTime();
	}
}
