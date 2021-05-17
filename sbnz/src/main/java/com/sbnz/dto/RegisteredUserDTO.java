package com.sbnz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbnz.model.Category;
import com.sbnz.model.RegisteredUser;

public class RegisteredUserDTO extends UserDTO {
	private Date dateOfBirth;

	private String profession;

	private double locationLat;

	private double locationLon;

	private List<CategoryDTO> interests;

	public RegisteredUserDTO() {
		super();
	}

	public RegisteredUserDTO(String firstName, String lastName, String email, String password, Date dateOfBirth,
			String profession, Double lat, Double lon) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.profession = profession;
		this.locationLat = lat;
		this.locationLon = lon;
	}

	public RegisteredUserDTO(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified, Date dateOfBirth, String profession, Double lat, Double lon, List<Category> interests) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.verified = verified;
		this.dateOfBirth = dateOfBirth;
		this.profession = profession;
		this.locationLat = lat;
		this.locationLon = lon;
		this.interests = new ArrayList<CategoryDTO>();
		for (Category c : interests) {
			this.interests.add(c.toDTO());
		}

	}

	public RegisteredUser toEntity() {
		return new RegisteredUser(this.getId(), this.getEmail(), this.getPassword(), this.getFirstName(),
				this.getLastName(), this.getDateOfBirth(), this.getProfession(), this.getLocationLat(),
				this.getLocationLon(), this.getInterests(), this.getActive(), this.getVerified());
	}

	public List<CategoryDTO> getInterests() {
		return interests;
	}

	public void setInterests(List<CategoryDTO> interests) {
		this.interests = interests;
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

	@Override
	public String toString() {
		return "RegisteredUserDTO [dateOfBirth=" + dateOfBirth + ", profession=" + profession + ", locationLat="
				+ locationLat + ", locationLon=" + locationLon + ", interests=" + interests + ", id=" + id
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", active=" + active + ", verified=" + verified + "]";
	}

}
