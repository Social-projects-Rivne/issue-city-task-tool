package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.com.softserveinc.main.models.CategoryModel;
 
public interface CategoryDao extends JpaRepository<CategoryModel, Integer>{
	
	@Query("select c from CategoryModel c where c.name = :name")
	CategoryModel findByName(@Param("name") String name);
	
}