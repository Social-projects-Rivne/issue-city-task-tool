package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.IssueModel;
 
public interface IssueDao extends JpaRepository<IssueModel, Integer>{
}