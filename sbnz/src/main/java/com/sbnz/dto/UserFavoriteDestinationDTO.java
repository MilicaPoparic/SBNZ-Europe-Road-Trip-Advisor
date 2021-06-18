package com.sbnz.dto;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
public class UserFavoriteDestinationDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	private String destination;
	private Date executionTime;

	public UserFavoriteDestinationDTO() {
		super();
		this.executionTime = new Date();
	}

	public UserFavoriteDestinationDTO(String username, String destination) {
		super();
		this.executionTime = new Date();
		this.username = username;
		this.destination = destination;
	}
	

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
