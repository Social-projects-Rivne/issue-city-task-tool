package edu.com.softserveinc.bawl.models;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subscriptions", uniqueConstraints=@UniqueConstraint(columnNames={"issueId", "email"}))

public class SubscriptionModel {

    public static final Logger LOG = Logger.getLogger(SubscriptionModel.class);
	
	@Id
	@GeneratedValue
	@Column(unique=true, name = "id")
	int id;

	@NotNull
	@Column(name = "issueId")
	private int issueId;

	@Email
	@Column(name = "email")
	private String email;

	@Email
	@Column(name = "userId")
	private int userId;

	@NotNull
	@Column(name = "isValid")
	private boolean isValid;

	public SubscriptionModel() {}

	public SubscriptionModel(int issueId, String email) {
		this.issueId = issueId;
		this.email = email;
	}

	public SubscriptionModel(int issueId, String email, int userId, boolean isValid) {
		this.issueId = issueId;
		this.email = email;
		this.userId = userId;
		this.isValid = isValid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + issueId;
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
		SubscriptionModel other = (SubscriptionModel) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (issueId != other.issueId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionModel [id=" + id + ", issueId=" + issueId + ", email=" + email + "]";
	}

}
