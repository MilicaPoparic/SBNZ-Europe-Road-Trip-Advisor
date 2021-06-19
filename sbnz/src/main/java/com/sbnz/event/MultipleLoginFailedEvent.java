package com.sbnz.event;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class MultipleLoginFailedEvent {

    private String username;
    private String description;
    
	public MultipleLoginFailedEvent() {
		super();
	}
	public MultipleLoginFailedEvent(String username, String description) {
		super();
		this.username = username;
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
    
}
