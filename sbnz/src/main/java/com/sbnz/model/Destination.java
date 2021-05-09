package com.sbnz.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(unique = false, nullable = false)
	private Double locationLat;
	
	@Column(unique = false, nullable = false)
	private Double locationLon;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Category> categories;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_id", nullable = true)
	private List<Hotel> hotels;
	
	@Column
	@Enumerated
	@ElementCollection(targetClass = LocalFood.class)
	private List<LocalFood> localFood;
	
	@Column
	@Enumerated
	@ElementCollection(targetClass = Transportation.class)
	private List<Transportation> transportation;
	
	@Column(unique = false, nullable = false)
	private Double score;
	
	@Column(unique = false, nullable = false)
	private Boolean trending;
	
	@Column(unique = false, nullable = false)
	private Boolean active;
	
	public Destination(Long id, String name, Double locationLat, Double locationLon, List<Category> categories,
			List<Hotel> hotels, List<LocalFood> localFood, List<Transportation> transportation, Double score,
			Boolean trending, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.categories = categories;
		this.hotels = hotels;
		this.localFood = localFood;
		this.transportation = transportation;
		this.score = score;
		this.trending = trending;
		this.active = active;
	}
	public Destination() {
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
	public Double getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}
	public Double getLocationLon() {
		return locationLon;
	}
	public void setLocationLon(Double locationLon) {
		this.locationLon = locationLon;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public List<LocalFood> getLocalFood() {
		return localFood;
	}
	public void setLocalFood(List<LocalFood> localFood) {
		this.localFood = localFood;
	}
	public List<Transportation> getTransportation() {
		return transportation;
	}
	public void setTransportation(List<Transportation> transportation) {
		this.transportation = transportation;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getTrending() {
		return trending;
	}
	public void setTrending(Boolean trending) {
		this.trending = trending;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
