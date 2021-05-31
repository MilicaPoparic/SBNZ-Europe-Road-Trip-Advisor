package com.sbnz.dto;

import java.util.Date;

public class EventDTO {
	/***
	 * API event categories: 
	 * academic school-holidays public-holidays observances
	 * politics conferences expos concerts festivals performing-arts sports
	 * community daylight-savings airport-delays severe-weather disasters terror
	 * health-warnings
	 * 
	 */
	public String title;
	public String category;
	public Date start;
	public Date endD;
	public Double lat;
	public Double lon;

	public EventDTO(String title, String category, Date start, Date end, Double lat, Double lon) {
		super();
		this.title = title;
		this.category = category;
		this.start = start;
		this.endD = end;
		this.lat = lat;
		this.lon = lon;
	}

	public EventDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEndD() {
		return endD;
	}

	public void setEndD(Date end) {
		this.endD = end;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}
