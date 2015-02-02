package edu.com.softserveinc.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "problems")
public class IssueModel {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "category_id")
	private int category_id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "description")
	private String description;

	@NotEmpty
	@Column(name = "mapPointer")
	private String mapPointer;

	@Column(name = "attachments")
	private String attachments;

	@Column(name = "priority_id")
	private int priorityId;

	/**
	 * Default constructor
	 */
	public IssueModel() {

	}

	public IssueModel(String name, String description, String mapPointer,
			String attachments, int priorityId) {
		this.name = name;
		this.description = description;
		this.mapPointer = mapPointer;
		this.attachments = attachments;
		this.priorityId = priorityId;

	}
	
	/**
	 * Constructor with field category_id
	 * 
	 * @param category_id
	 * @param name
	 * @param description
	 * @param mapPointer
	 * @param attachments
	 * @param priorityId
	 */
	public IssueModel(int category_id, String name, String description,
			String mapPointer, String attachments, int priorityId) {
		this.category_id = category_id;
		this.name = name;
		this.description = description;
		this.mapPointer = mapPointer;
		this.attachments = attachments;
		this.priorityId = priorityId;

	}

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return category id
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * Setup category id of problem
	 * 
	 * @param category_id
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * It returns name of problem
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * Setup name of problem
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * It returns description of problem
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setup description of problem
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * It returns map coordinates
	 * 
	 * @return map coordinates
	 */
	public String getMapPointer() {
		return mapPointer;
	}

	/**
	 * Setup map coordinates
	 * 
	 * @param mapPointer
	 */
	public void setMapPointer(String mapPointer) {
		this.mapPointer = mapPointer;
	}

	/**
	 * Setup attachment
	 * 
	 * @return
	 */
	public String getAttachments() {
		return attachments;
	}

	/**
	 * 
	 * 
	 * @param attachments
	 */
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	/**
	 * Return priority id
	 * 
	 * @return priority_id
	 */
	public int getPriorityId() {
		return priorityId;
	}

	/**
	 * Setup priority id
	 * 
	 * @param priority_id
	 */
	public void setPriorityId(int priority_id) {
		this.priorityId = priority_id;
	}

}
