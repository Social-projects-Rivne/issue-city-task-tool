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
	@Column(unique=true, name = "id")
	private int id;

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
	
	@NotNull
	@Column(name = "category_id")
	private int categoryId;
	
	@NotNull
	@Column(name = "priority_id")
	private int priorityId;
	
	@NotNull
	@Column(name = "status_id")
	private int statusId;

	/**
	 * Default constructor
	 */
	public IssueModel() {}

	public IssueModel(String name, String description, String mapPointer,
			String attachments, int categoryId, int priorityId, int statusId) {
		this.name = name;
		this.description = description;
		this.mapPointer = mapPointer;
		this.attachments = attachments;
		this.categoryId = categoryId;
		this.priorityId = priorityId;
		this.statusId = statusId;
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
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Setup category id of problem
	 * 
	 * @param category_id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	 * Return priorityId
	 * 
	 * @return priorityId
	 */
	public int getPriorityId() {
		return priorityId;
	}

	/**
	 * Setup priorityId
	 * 
	 * @param priorityId
	 */
	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}
	
	/**
	 * Return statusId
	 * 
	 * @return statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * Setup statusId
	 * 
	 * @param statusId
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "IssueModel [id=" + id + ", name=" + name + ", description="
				+ description + ", mapPointer=" + mapPointer + ", categoryId="
				+ categoryId + ", priorityId=" + priorityId + ", statusId="
				+ statusId + "]";
	}

}
