package com.centime.database.model;

import java.util.List;

public class HierResponse {
	
	private int id;
	
	private int parentId;
	
	private String name;
	
	private String color;
	
	private List<HierResponse> hierResponse;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<HierResponse> getHierResponse() {
		return hierResponse;
	}

	public void setHierResponse(List<HierResponse> hierResponse) {
		this.hierResponse = hierResponse;
	}

	
}
