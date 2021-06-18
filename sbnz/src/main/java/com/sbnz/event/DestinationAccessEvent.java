package com.sbnz.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15m")
public class DestinationAccessEvent {

    private String username;
    private String destination;
	public DestinationAccessEvent() {
		super();
	}
	public DestinationAccessEvent(String username, String destination) {
		super();
		this.username = username;
		this.destination = destination;
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
