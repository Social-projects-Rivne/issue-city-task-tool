package edu.com.softserveinc.bawl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import org.apache.log4j.Logger;

@Entity
@Table(name = "statuses")
public class StatusModel {
	
	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(StatusModel.class);

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusModel other = (StatusModel) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusModel [id=" + id + ", name=" + name + "]";
	}
	
}
