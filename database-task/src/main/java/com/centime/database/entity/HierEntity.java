package com.centime.database.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author harish
 *
 */
@Entity
public class HierEntity {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private Integer parentId;
	
	private String name;
	
	private String color;
	
	@Transient
	private List<HierEntity> subClass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<HierEntity> getSubClass() {
		return subClass;
	}

	public void setSubClass(List<HierEntity> subClass) {
		this.subClass = subClass;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
	
	
}
