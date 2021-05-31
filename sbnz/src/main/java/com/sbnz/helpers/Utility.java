package com.sbnz.helpers;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.sbnz.model.Transportation;

public class Utility {
	
	public static double multiply(long distance, Transportation t, int numberOfPeople) {
		return distance * getTransportationWeight(t) * numberOfPeople;
	}
	
	public static double getTransportationWeight(Transportation t) {
		if (t == Transportation.train)
			return 1.5;
		else if (t == Transportation.bus)
			return 2.5;
		else if (t == Transportation.car)
			return 3.0;
		else if (t == Transportation.ship)
			return 3.5;
		else if (t == Transportation.plane)
			return 4.0;
		return 1.0; // if transport is not chosen	
	}

	public static long getYears(Date date) {
		long diffInMillies = Math.abs(new Date().getTime() - date.getTime());
	    long diff = (long) (TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365.25);
	    return diff;
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  dist = dist * 1.609344;
		  
		  return (dist);
	}

	private static double deg2rad(double deg) {
	  return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
	  return (rad * 180.0 / Math.PI);
	}
	
	public static Date workWithDates(Date date, String operator, int entity, int number) {
	    if (date != null) {
	        java.util.Calendar newCal = java.util.Calendar.getInstance();
	        newCal.setTime(date);
	        if (operator.equals("-")) { //normally we would add, except if we want to subtract
	            number = -number;
	        }
	        if (entity == 1) { //years
	            newCal.add(java.util.Calendar.YEAR, number);
	        } else if (entity== 2) { //months
	            newCal.add(java.util.Calendar.MONTH, number);       
	        } else if (entity== 3) { //days
	            newCal.add(java.util.Calendar.DATE, number);        
	        } else if (entity== 4) { //hours
	            newCal.add(java.util.Calendar.HOUR, number);        
	        } else if (entity== 5) { //minutes
	            newCal.add(java.util.Calendar.MINUTE, number);      
	        } else if (entity== 6) { //seconds
	            newCal.add(java.util.Calendar.SECOND, number);      
	        }
	        return newCal.getTime();    
	    } else {
	        return date;
	    }
	}
}
