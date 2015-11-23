package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.IssueModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
 
public interface IssueDao extends JpaRepository<IssueModel, Integer>{

    List <IssueModel> findByCategoryId(int categoryId);
}