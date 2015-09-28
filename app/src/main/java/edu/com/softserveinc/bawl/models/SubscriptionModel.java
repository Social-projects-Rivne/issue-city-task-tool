package edu.com.softserveinc.bawl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "subscriptions", uniqueConstraints=@UniqueConstraint(columnNames={"issueId", "email"}))
public class SubscriptionModel {
	
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

	public SubscriptionModel() {
	}

	public SubscriptionModel(int issueId, String email) {
		this.issueId = issueId;
		this.email = email;
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
		return "SubscriptionModel [id=" + id + ", issueId=" + issueId
				+ ", email=" + email + "]";
	}

}
