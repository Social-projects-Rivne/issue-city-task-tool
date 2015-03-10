package edu.com.softserveinc.bawl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "statuses")
public class StatusModel {
	@Id
	@GeneratedValue
	@Column(unique=true, name = "id")
	int id;

	@NotEmpty
	@Column(unique=true, name = "name")
	private String name;
	
	public StatusModel() {}
	
	public StatusModel(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatusModel [id=" + id + ", name=" + name + "]";
	}
	
}
