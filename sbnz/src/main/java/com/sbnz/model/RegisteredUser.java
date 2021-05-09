package com.sbnz.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("registered_user")
public class RegisteredUser extends User {
	@Column(nullable = false)
	private Date dateOfBirth;
	
	@Column(nullable = false)
	private String profession;
	
	@Column(nullable = false)
	private double locationLat;
	
	@Column(nullable = false)
	private double locationLon;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Category> interests;
	
	public RegisteredUser(Long id, String email, String password, String firstName, String lastName, Date dateOfBirth,
			String profession, double locationLat, double locationLon, List<Category> interests, Boolean active, Boolean verified) {
		super(id, email, password, firstName, lastName, active, verified);
		this.dateOfBirth = dateOfBirth;
		this.profession = profession;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.interests = interests;
	}
	public RegisteredUser() {
		super();
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public double getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}
	public double getLocationLon() {
		return locationLon;
	}
	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}
	public List<Category> getInterests() {
		return interests;
	}
	public void setInterests(List<Category> interests) {
		this.interests = interests;
	}	
}
