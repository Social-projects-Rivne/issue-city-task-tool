package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;

import java.util.List;

public interface CategoryService {
	
	
	public CategoryModel addCategory(CategoryModel category);

	public void deleteCategory(CategoryModel category);

	public void editCategory(CategoryModel category);

	public CategoryModel getCategoryByID(int id);
	
	public List<CategoryModel> loadCategoriesList();
		
	public CategoryModel getCategoryByName(String name);
}
