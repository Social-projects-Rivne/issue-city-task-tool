package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

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

  public static final Logger LOG = Logger.getLogger(IssueModel.class);

  @Id
  @GeneratedValue
  @Column(unique = true, name = "ID")
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

  @OneToMany(mappedBy = "issue", fetch = FetchType.EAGER)
  private List<HistoryModel> histories;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private IssueStatus status;

  /**
   * Default constructor
   */
  public IssueModel() {
  }

  public IssueModel(String name, String description, String mapPointer, String attachments, CategoryModel category,
      int priorityId, IssueStatus status) {
    this.name = name;
    this.description = description;
    this.mapPointer = mapPointer;
    this.attachments = attachments;
    this.category = category;
    this.priorityId = priorityId;
    this.status = status;
  }

  /**
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
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

  public IssueModel withStatus(String status) {
    if (StringUtils.isEmpty(status)) {
      return this;
    }
    this.status = IssueStatus.valueOf(status.toUpperCase());
    return this;
  }

  public IssueModel withStatus(IssueStatus status) {
    this.status = status;
    return this;
  }

  public IssueModel withAttachments(String attachments) {
    if (StringUtils.isEmpty(attachments)) {
      return this;
    }
    this.attachments = attachments;
    return this;
  }

  public IssueModel withDescription(String description) {
    if (StringUtils.isEmpty(description)) {
      return this;
    }
    this.description = description;
    return this;
  }

  public IssueModel withName(String name) {
    if (StringUtils.isEmpty(name)) {
      return this;
    }
    this.name = name;
    return this;
  }

  public IssueModel withMapPointer(String mapPointer) {
    this.mapPointer = mapPointer;
    return this;
  }

  public IssueModel withCategory(CategoryModel category) {
    this.category = category;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    IssueModel that = (IssueModel) o;
    return Objects.equal(id, that.id) &&
        Objects.equal(priorityId, that.priorityId) &&
        Objects.equal(name, that.name) &&
        Objects.equal(description, that.description) &&
        Objects.equal(mapPointer, that.mapPointer) &&
        Objects.equal(attachments, that.attachments) &&
        Objects.equal(category, that.category) &&
        Objects.equal(histories, that.histories) &&
        Objects.equal(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, description, mapPointer, attachments, priorityId, category, histories, status);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("id", id).add("name", name).add("description", description)
        .add("mapPointer", mapPointer).add("attachments", attachments).add("priorityId", priorityId)
        .add("category", category).add("histories", histories).add("status", status).toString();
  }
}
