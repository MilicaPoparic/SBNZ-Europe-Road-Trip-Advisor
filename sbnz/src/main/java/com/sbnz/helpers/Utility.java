package com.sbnz.helpers;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utility {

	public static long getYears(Date date) {
		long diffInMillies = Math.abs(new Date().getTime() - date.getTime());
	    long diff = (long) (TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365.25);
	    return diff;
	}
	
}
