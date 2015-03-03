package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.main.models.StatusModel;
 
public interface StatusDao extends JpaRepository<StatusModel, Integer>{
	
	StatusModel findByName(String name);
	
}