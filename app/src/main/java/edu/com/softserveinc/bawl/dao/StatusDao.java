package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.IssueStatus;
 
public interface StatusDao extends JpaRepository<IssueStatus, Integer>{
	
	IssueStatus findByName(String name);
	
}