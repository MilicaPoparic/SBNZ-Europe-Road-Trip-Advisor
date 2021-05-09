package com.sbnz.dto;

import com.sbnz.model.User;

public class UserDTO {

	protected Long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected Boolean active;
	protected Boolean verified;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.id = id;
	}

	public UserDTO(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public UserDTO(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.verified = verified;
	}

	public User toEntity() {
		return new User(this.getId(), this.getFirstName(), this.getLastName(), this.getEmail(), this.getPassword(), this.getActive(),
				this.getVerified());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", verified=" + verified + ", idImageDTO=" + "]";
	}

}
