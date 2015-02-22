package edu.com.softserveinc.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.main.models.CommentModel;
 
public interface CommentDao extends JpaRepository<CommentModel, Integer>{
	
	List<CommentModel> findByIssueId(int issueId);
}