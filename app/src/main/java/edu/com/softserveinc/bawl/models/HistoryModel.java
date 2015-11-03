package edu.com.softserveinc.bawl.models;

import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.TemporalType.*;


@Entity
@Table(name="HISTORY")
public class HistoryModel {

    /**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(HistoryModel.class);

    @Id
    @GeneratedValue
    @Column(unique=true, name="ID")
    private int id;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="STATUS")
    @Enumerated(EnumType.ORDINAL)
    private IssueStatus status;

    @Column(name="DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ISSUE_ID")
    private IssueModel issue;

    private int issueId;

    public HistoryModel(){}


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

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setStatusId(int status) {
        this.status = IssueStatus.get(status);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IssueModel getIssue() {
        return issue;
    }

    public void setIssue(IssueModel issue) {
        this.issue = issue;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + issue.getId();
        result = prime * result + status.ordinal();
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
        if (issue == null || (issue.getId() != other.issue.getId()))
            return false;
        if (userId != other.userId)
            return false;
        if (status.ordinal() != other.status.ordinal())
            return false;

        if (!date.equals(other.date))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "HistoryModel [id=" + id + ", userId=" + userId
                + ", issueId=" + issue.getId() + ", statusId=" + status +"]";
    }


}
