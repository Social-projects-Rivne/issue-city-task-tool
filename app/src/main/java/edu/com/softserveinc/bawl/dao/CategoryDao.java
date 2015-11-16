package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<CategoryModel, Integer>{
	
	CategoryModel findByName(String name);

}