package edu.com.softserveinc.bawl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class for problem's category 
 * 
 * @author Nazar
 *
 */
@Entity
@Table(name = "categories")
public class CategoryModel {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(CategoryModel.class);


	//TODO: add annotation for connect this class to IssueModel
	@Id
	@GeneratedValue
	@Column(unique=true, name = "id")
	int id;

	@NotEmpty
	@Column(unique=true, name = "name")
	private String name;

	@NotEmpty
	@Column(unique=false, name="isdeleted")
	private int isdeleted;
	
	public CategoryModel() {}
	
	public CategoryModel(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsdeleted() { return isdeleted; }

	public void setIsdeleted(int isdeleted) { this.isdeleted = isdeleted; }

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
		CategoryModel other = (CategoryModel) obj;
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
		return "CategoryModel [id=" + id + ", name=" + name + "]";
	}

}
