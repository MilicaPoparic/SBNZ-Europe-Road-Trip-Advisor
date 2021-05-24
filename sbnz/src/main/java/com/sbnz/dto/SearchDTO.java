package com.sbnz.dto;

import java.util.ArrayList;
import java.util.Date;

import com.sbnz.model.LocalFood;
import com.sbnz.model.Transportation;

//treba budzet da se sredi u smislu da ima od do!
//ostale parametre odraditi
//za children dodati flag u hotelu 1. GOTOVO
//grupisati pravila
//srediti keySession da bude jedna
//srediti frontend, konkretno ovaj zahtev
//dodati slike kod onih kartica, tj u destinations
//registracija

// promeniti na frontu accommodation polje za unos


public class SearchDTO {
	public ArrayList<LocalFood> localFood; //done
	public Transportation transportation; //done
	public String budget; // done osnovno
	public Boolean children; // done
	public Integer accommodation; // done
	public Integer numberOfPeople; // for budget
	public Date startDate;
	public Date endDate;
	public Integer maxDistance;

	public SearchDTO() {
		super();
	}

	public SearchDTO(ArrayList<LocalFood> localFood, Transportation transportation, String budget, Boolean children,
			Integer accommodation, Integer numberOfPeople, Date startDate, Date endDate, Integer maxDistance) {
		super();
		this.localFood = localFood;
		this.transportation = transportation;
		this.budget = budget;
		this.children = children;
		this.accommodation = accommodation;
		this.numberOfPeople = numberOfPeople;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxDistance = maxDistance;
	}

	public ArrayList<LocalFood> getLocalFood() {
		return localFood;
	}

	public void setLocalFood(ArrayList<LocalFood> localFood) {
		this.localFood = localFood;
	}

	public Transportation getTransportation() {
		return transportation;
	}

	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public Integer getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Integer accommodation) {
		this.accommodation = accommodation;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Integer maxDistance) {
		this.maxDistance = maxDistance;
	}

}
