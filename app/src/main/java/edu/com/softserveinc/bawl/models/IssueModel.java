package edu.com.softserveinc.bawl.models;

import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "ISSUE")
public class IssueModel {

    public static final Logger LOG=Logger.getLogger(IssueModel.class);

	@Id
	@GeneratedValue
	@Column(unique=true, name = "ID")
	private int id;

	@NotNull
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Column(name = "MAP_POINTER")
	private String mapPointer;

	@Column(name = "ATTACHMENTS")
	private String attachments;

	@NotNull
	@Column(name = "PRIORITY_ID")
	private int priorityId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryModel category;

    @OneToMany(mappedBy="issue", fetch = FetchType.EAGER)
    private List<HistoryModel> histories;

    @Column(name="STATUS")
    @Enumerated(EnumType.ORDINAL)
    private IssueStatus status;

	/**
	 * Default constructor
	 */
	public IssueModel() {}

	public IssueModel(String name, String description, String mapPointer,
			String attachments, CategoryModel category, int priorityId, IssueStatus status) {
		this.name = name;
		this.description = description;
		this.mapPointer = mapPointer;
		this.attachments = attachments;
		this.category = category;
		this.priorityId = priorityId;
		this.status = status;
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
	 * @return category
	 */
	public CategoryModel getCategory() {
		return category;
	}

	/**
	 * Setup category
	 * 
	 * @param category
	 */
	public void setCategory(CategoryModel category) {
		this.category = category;
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

    public List<HistoryModel> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryModel> histories) {
        this.histories = histories;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((mapPointer == null) ? 0 : mapPointer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priorityId;
		result = prime * result + status.ordinal();
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
		IssueModel other = (IssueModel) obj;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (category!= null && !category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (mapPointer == null) {
			if (other.mapPointer != null)
				return false;
		} else if (!mapPointer.equals(other.mapPointer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priorityId != other.priorityId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IssueModel [id=" + id + ", name=" + name + ", description="
				+ description + ", mapPointer=" + mapPointer + ", category="
				+ category + ", priorityId=" + priorityId + ", statusId="
				+ status + "]";
	}

}
