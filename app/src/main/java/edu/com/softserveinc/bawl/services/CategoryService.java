package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.enums.CategoryState;

import java.util.List;

public interface CategoryService {

	 CategoryModel addCategory(CategoryModel category);

	 CategoryModel addCategory(String category);

	 void deleteCategory(CategoryModel category);

	 void updateCategory(int id, String name, CategoryState state);

	 CategoryModel getCategoryByID(int id);
	
	 List<CategoryModel> loadCategoriesList();
		
	 CategoryModel getCategoryByName(String name);

	 CategoryModel getCategoryByNameOrAddNew(String name);
}
