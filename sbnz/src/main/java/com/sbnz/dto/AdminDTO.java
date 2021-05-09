package com.sbnz.dto;

public class AdminDTO extends UserDTO{
	
	public AdminDTO() {
		super();
	}
	public AdminDTO(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
	}
		
	public AdminDTO(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.verified = verified;

	}
	
}
