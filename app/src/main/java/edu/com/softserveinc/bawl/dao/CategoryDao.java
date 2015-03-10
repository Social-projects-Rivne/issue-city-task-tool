package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.CategoryModel;
 
public interface CategoryDao extends JpaRepository<CategoryModel, Integer>{
	
	CategoryModel findByName(String name);
	
}