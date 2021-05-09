package com.sbnz.dto;

import java.util.List;

import com.sbnz.model.Category;
import com.sbnz.model.Destination;
import com.sbnz.model.Hotel;
import com.sbnz.model.LocalFood;
import com.sbnz.model.Transportation;

public class DestinationDTO {
	private Long id;

	private String name;

	private Double locationLat;

	private Double locationLon;

	private List<LocalFood> localFood;

	private List<Transportation> transportation;

	private Double score;

	private Boolean trending;

	private Boolean active;

	private List<Category> categories;

	private List<Hotel> hotels;

	public DestinationDTO() {
		super();
	}

	public DestinationDTO(Long id, String name, Double locationLat, Double locationLon, List<Category> categories,
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

	public Destination toEntity() {
		return new Destination(this.getId(), this.getName(), this.getLocationLat(), this.getLocationLon(),this.getCategories(), this.getHotels(),
				this.getLocalFood(), this.getTransportation(), this.getScore(), this.getTrending(), this.getActive());
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
	

}