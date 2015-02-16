package edu.com.softserveinc.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.com.softserveinc.main.models.CommentModel;
 
public interface CommentDao extends JpaRepository<CommentModel, Integer>{
	
	@Query("select c from CommentModel c where c.issueId = :issueId")
	List<CommentModel> findByIssueId(@Param("issueId") int issueId);
}