package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="COMMENTS")
public class CommentModel {

    public static final Logger LOG = Logger.getLogger(CommentModel.class);

	@Id
	@GeneratedValue
	@Column(unique=true, name="ID")
	private int id;
	
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ISSUE_ID")
	private int issueId;

	public CommentModel(){};
	
	public CommentModel(String comment, String userName, String email, int issueId) {
		this.comment = comment;
		this.userName = userName;
		this.email = email;
		this.issueId = issueId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CommentModel that = (CommentModel) o;
		return Objects.equal(id, that.id) &&
				Objects.equal(issueId, that.issueId) &&
				Objects.equal(comment, that.comment) &&
				Objects.equal(userName, that.userName) &&
				Objects.equal(email, that.email);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, comment, userName, email, issueId);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("comment", comment)
				.add("userName", userName)
				.add("email", email)
				.add("issueId", issueId)
				.toString();
	}
}
