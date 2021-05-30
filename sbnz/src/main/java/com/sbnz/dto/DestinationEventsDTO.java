package com.sbnz.dto;

import java.util.ArrayList;

import com.sbnz.model.Destination;

public class DestinationEventsDTO {
	private Destination destination;
	private ArrayList<EventDTO> events;
	public DestinationEventsDTO(Destination destination, ArrayList<EventDTO> events) {
		super();
		this.destination = destination;
		this.events = events;
	}
	public DestinationEventsDTO() {
		super();
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public ArrayList<EventDTO> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<EventDTO> events) {
		this.events = events;
	}
	
}
