package edu.com.softserveinc.main.models;

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
	@Column(name = "id")
	int id;

	@NotEmpty
	@Column(name = "name")
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
}
