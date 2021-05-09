package com.sbnz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = false, nullable = false)
	private String name;
	
	@Column(unique = false, nullable = false)
	private int stars; // 1-2 low budget hotel, 3 medium, 4-5 luxury hotel
	
	@Column(unique = false, nullable = false)
	private Boolean active;
	
	public Hotel(Long id, String name, int stars, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.stars = stars;
		this.active = active;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Hotel() {
		super();
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
