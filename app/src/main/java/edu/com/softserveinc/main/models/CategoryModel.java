package edu.com.softserveinc.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for problem's category 
 * 
 * @author Nazar
 *
 */
@Entity
@Table(name = "categories")
public class CategoryModel {


	//TODO: add annotation for connect this class to IssueModel
	@Id
	@GeneratedValue
	@Column(unique=true, name = "id")
	int id;

	@NotEmpty
	@Column(unique=true, name = "name")
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
