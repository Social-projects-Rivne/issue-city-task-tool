package edu.com.softserveinc.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="comments")
public class CommentModel {

	@Id
	@GeneratedValue
	@Column(unique=true, name="id")
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

	public CommentModel(){};
	
	public CommentModel(String comment, String userName, String email, int issueId) {
		this.comment = comment;
		this.userName = userName;
		this.email = email;
		this.issueId = issueId;
	}
	
	public CommentModel(String comment, String userName, String email, String issueI) {
		this.comment = comment;
		this.userName = userName;
		this.email = email;
		this.issueId = (int)issueId;
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
	public String toString() {
		return "CommentModel [id=" + id + ", comment=" + comment
				+ ", userName=" + userName + ", email=" + email + ", issueId="
				+ issueId + "]";
	}

}
