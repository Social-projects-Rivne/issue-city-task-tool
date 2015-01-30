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
	@Column(name = "id")
	int id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	/*@Column(name = "count_Of_problems")
	private int countOfProblems;

	@Column(name = "cont_Of_Resolved_problems")
	private int countOfResolvedProblems;*/
	
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

	/*public int getCountOfProblems() {
		return countOfProblems;
	}

	public void setCountOfProblems(int countOfProblems) {
		this.countOfProblems = countOfProblems;
	}

	public int getCountOfResolvedProblems() {
		return countOfResolvedProblems;
	}

	public void setCountOfResolvedProblems(int countOfResolvedProblems) {
		this.countOfResolvedProblems = countOfResolvedProblems;
	}*/

}
