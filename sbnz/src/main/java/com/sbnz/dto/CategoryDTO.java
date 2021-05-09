package com.sbnz.dto;

import com.sbnz.model.Category;

public class CategoryDTO {

	private Long id;
	private String name;
	private Boolean active;

	public CategoryDTO(Long id, String name, Boolean active) {
		super();
		this.name = name;
		this.active = active;
	}
	public Category toEntity() {
		return new Category(this.getId(), this.getName(), this.isActive());
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
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
