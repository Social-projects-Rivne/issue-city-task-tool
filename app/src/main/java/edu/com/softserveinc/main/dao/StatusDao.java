package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.com.softserveinc.main.models.StatusModel;
 
public interface StatusDao extends JpaRepository<StatusModel, Integer>{
	
	@Query("select st from StatusModel st where st.name = :name")
	StatusModel findByName(@Param("name") String name);
	
}