package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dto.CategoryDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;

import java.util.List;

public interface CategoryService {


	CategoryModel addCategory(CategoryDTO category);

	CategoryModel addCategory(String categoryName);

	void deleteCategory(CategoryModel category);

	void editCategory(CategoryModel category);

	CategoryModel getCategoryByID(int id);
	
	List<CategoryDTO> loadCategoriesWithoutIssues();

	List<CategoryDTO> loadCategoriesListWithIssues();

	CategoryModel getCategoryByName(String name);
}
