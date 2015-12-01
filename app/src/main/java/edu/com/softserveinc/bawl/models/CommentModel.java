package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import org.apache.log4j.Logger;

import javax.persistence.*;


@Entity
@Table(name = "COMMENTS")
public class CommentModel {

  public static final Logger LOG = Logger.getLogger(CommentModel.class);

  @Id
  @GeneratedValue
  @Column(unique = true, name = "ID")
  private int id;

  @Column(name = "COMMENT")
  private String comment;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "ISSUE_ID")
  private int issueId;

  @Transient
  private String avatar;

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

  public String getAvatar() {   return avatar;}

  public void setAvatar(String avatar) { this.avatar = avatar;}

  public CommentModel withEmail(String email) {
    this.email = email;
    return this;
  }

  public CommentModel withIssueId(int issueId) {
    this.issueId = issueId;
    return this;
  }

  public CommentModel withComment(String comment) {
    this.comment = comment;
    return this;
  }

  public CommentModel withUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public CommentModel withAvatar(String avatar){
    this.avatar = avatar;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
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
    return Objects.toStringHelper(this).add("id", id).add("comment", comment).add("userName", userName)
        .add("email", email).add("issueId", issueId).toString();
  }
}
