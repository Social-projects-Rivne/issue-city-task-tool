package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;

import java.util.List;

public interface CategoryService {

	 CategoryModel addCategory(CategoryModel category);

	 CategoryModel addCategory(String category);

	 void deleteCategory(CategoryModel category);

	 void editCategory(CategoryModel category);

	 CategoryModel getCategoryByID(int id);
	
	 List<CategoryModel> loadCategoriesList();
		
	 CategoryModel getCategoryByName(String name);

	 CategoryModel getCategoryByNameOrAddNew(String name);
}
