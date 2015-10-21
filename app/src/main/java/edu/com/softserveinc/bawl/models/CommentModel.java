package edu.com.softserveinc.bawl.models;

import org.apache.log4j.Logger;

import javax.persistence.*;


@Entity
@Table(name="comments")
public class CommentModel {

    public static final Logger LOG=Logger.getLogger(CommentModel.class);

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + issueId;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		CommentModel other = (CommentModel) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (issueId != other.issueId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", comment=" + comment
				+ ", userName=" + userName + ", email=" + email + ", issueId="
				+ issueId + "]";
	}

}
