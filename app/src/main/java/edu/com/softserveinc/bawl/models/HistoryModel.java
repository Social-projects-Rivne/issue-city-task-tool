package edu.com.softserveinc.bawl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import java.util.Date;


@Entity
@Table(name="history")
public class HistoryModel {

    /**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(HistoryModel.class);

    @Id
    @GeneratedValue
    @Column(unique=true, name="id")
    private int id;

    @Column(name="issue_id")
    private int issueId;

    @Column(name="user_id")
    private int userId;

    @Column(name="status_id")
    private int statusId;

    @Column(name="date")
    private Date date;




    public HistoryModel(){};

    public HistoryModel(int issueId, int userId, int statusId) {
        this.issueId = issueId;
        this.userId = userId;
        this.statusId = statusId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + issueId;
        result = prime * result + statusId;
        result = prime * result + userId;
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
        HistoryModel other = (HistoryModel) obj;

        if (id != other.id)
            return false;
        if (issueId != other.issueId)
            return false;
        if (userId != other.userId)
            return false;
        if (statusId != other.statusId)
            return false;

        if (!date.equals(other.date))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "HistoryModel [id=" + id + ", userId=" + userId
                + ", issueId=" + issueId + ", statusId=" + statusId +"]";
    }


}
