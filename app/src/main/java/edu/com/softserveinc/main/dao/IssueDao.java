package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.com.softserveinc.main.models.IssueModel;
 
public interface IssueDao extends JpaRepository<IssueModel, Integer>{
}