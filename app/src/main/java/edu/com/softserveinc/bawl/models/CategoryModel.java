package edu.com.softserveinc.bawl.models;

import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class for problem's category 
 * 
 * @author Nazar
 *
 */
@Entity
@Table(name = "CATEGORIES")
public class CategoryModel {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(CategoryModel.class);

    public enum CategoryModelState {
        NEW, DELETED
    }

	//TODO: add annotation for connect this class to IssueModel
	@Id
	@GeneratedValue
	@Column(unique=true, name = "ID")
	int id;

    @NotNull
	@Column(unique=true, name = "NAME")
	private String name;

	@NotNull
	@Column(unique=false, name="STATE")
    @Enumerated(EnumType.ORDINAL)
	private CategoryModelState state;
	
	public CategoryModel() {
        setState(CategoryModelState.NEW);
    }
	
	public CategoryModel(String name) {
		this.name = name;
        setState(CategoryModelState.NEW);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoryModelState getState() { return state; }

	public void setState(CategoryModelState state) { this.state = state; }

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
