package edu.com.softserveinc.bawl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.CommentModel;
 
public interface CommentDao extends JpaRepository<CommentModel, Integer>{
	
	List<CommentModel> findByIssueId(int issueId);
}