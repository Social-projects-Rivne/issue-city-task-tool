package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.StatusModel;
 
public interface StatusDao extends JpaRepository<StatusModel, Integer>{
	
	StatusModel findByName(String name);
	
}