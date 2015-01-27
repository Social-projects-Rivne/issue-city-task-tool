package edu.com.softserveinc.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="comments")
public class CommentModel {

	@Id
	//@GeneratedValue
	@Column(name="id")
	private int id;
	
	//@NotNull
	//@NotEmpty
	@Column(name="comment")
	private String comment;
	
	//@Size(min=2, max=30)
	//@NotNull
	//@NotEmpty
	@Column(name="user_name")
	private String userName;
	
	//@Email
	//@NotNull
	//@NotEmpty
	@Column(name="email")
	private String email;
	
	@Column(name="issue_id")
	private int issueId;

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

}
