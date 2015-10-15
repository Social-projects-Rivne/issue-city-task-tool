package edu.com.softserveinc.bawl.dto;

/**
 * Created by lubko on 15.10.15.
 */
public class IssueDto {

    private String name;

    private String description;

    private String mapPointer;

    private String attachments;

    private int categoryId;

    private int priorityId;

    private int statusId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapPointer() {
        return mapPointer;
    }

    public void setMapPointer(String mapPointer) {
        this.mapPointer = mapPointer;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
