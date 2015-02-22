package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.main.models.CategoryModel;
 
public interface CategoryDao extends JpaRepository<CategoryModel, Integer>{
	
	CategoryModel findByName(String name);
	
}