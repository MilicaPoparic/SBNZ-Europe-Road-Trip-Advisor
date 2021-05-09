package com.sbnz.dto;

import com.sbnz.model.Hotel;

public class HotelDTO {
	private Long id;
	
	private String name;
	
	private int stars; // 1-2 low budget hotel, 3 medium, 4-5 luxury hotel
	
	private Boolean active;
	
	public HotelDTO(Long id, String name, int stars, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.stars = stars;
		this.active = active;
	}
	
	public HotelDTO() {
		super();
	}
	
	public Hotel toEntity() {
		return new Hotel(this.getId(), this.getName(), this.getStars(), this.getActive());
	}
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
}
