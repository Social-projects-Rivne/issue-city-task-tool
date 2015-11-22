package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="HISTORY")
public class HistoryModel {

    public static final Logger LOG=Logger.getLogger(HistoryModel.class);

    @Id
    @GeneratedValue
    @Column(unique=true, name="ID")
    private int id;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
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

    public HistoryModel withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public HistoryModel withStatus(IssueStatus status) {
        this.status = status;
        return this;
    }

    public HistoryModel withDate(Date date) {
        this.date = date;
        return this;
    }

    public HistoryModel withIssue(IssueModel issue) {
        this.issue = issue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryModel that = (HistoryModel) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(userId, that.userId) &&
                Objects.equal(issueId, that.issueId) &&
                Objects.equal(status, that.status) &&
                Objects.equal(date, that.date) &&
                Objects.equal(issue, that.issue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, userId, status, date, issue, issueId);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("status", status)
                .add("date", date)
                .add("issue", issue)
                .add("issueId", issueId)
                .toString();
    }
}
