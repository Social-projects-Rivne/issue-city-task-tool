package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SUBSCRIPTIONS", uniqueConstraints=@UniqueConstraint(columnNames={"ISSUEID","USERID","ISVALID"}))
public class SubscriptionModel {

    public static final Logger LOG = Logger.getLogger(SubscriptionModel.class);
	
	@Id
	@GeneratedValue
	@Column(unique=true, name = "ID")
	private int id;

	@NotNull
	@Column(name = "ISSUEID")
	private int issueId;

	@NotNull
	@Column(name = "USERID")
	private int userId;

	@Column(name = "ISVALID")
	private boolean isValid;

	public SubscriptionModel() {}
	

	public SubscriptionModel(int issueId, String email, int userId, boolean isValid) {
		this.issueId = issueId;
		this.userId = userId;
		this.isValid = isValid;
	}

	public SubscriptionModel(int issueId, int userId) {
		this.issueId = issueId;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isValid() {
		return isValid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SubscriptionModel that = (SubscriptionModel) o;
		return Objects.equal(id, that.id) &&
				Objects.equal(issueId, that.issueId) &&
				Objects.equal(userId, that.userId) &&
				Objects.equal(isValid, that.isValid);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, issueId, userId, isValid);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("issueId", issueId)
				.add("userId", userId)
				.add("isValid", isValid)
				.toString();
	}
}
