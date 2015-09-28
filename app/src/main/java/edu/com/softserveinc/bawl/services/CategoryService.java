package edu.com.softserveinc.bawl.services;

import java.util.List;

import edu.com.softserveinc.bawl.models.CategoryModel;

public interface CategoryService {
	
	
	public void addCategory(CategoryModel category);

	public void deleteCategory(CategoryModel category);

	public void editCategory(CategoryModel category);

	public CategoryModel getCategoryByID(int id);
	
	public List<CategoryModel> loadCategoriesList();
		
	public CategoryModel getCategoryByName(String name);
}
