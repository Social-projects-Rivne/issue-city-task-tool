package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.models.CategoryModel;

public interface CategoryService {
	/**
	 * Add new category in table categories
	 * 
	 * @param Category
	 */
	public void addCategory(CategoryModel category);

	/**
	 * Delete Category from table
	 * 
	 * @param CategoryId
	 */
	public void deleteCategory(CategoryModel category);

	/**
	 * Change existing Category's fields in Data Base
	 * 
	 * @param Category
	 */
	public void editCategory(CategoryModel category);

	/**
	 * 
	 * Get Category's fields from Data Base by Category_id
	 *
	 */
	public CategoryModel getCategoryByID(int id);
	
	/**
	 * 
	 * Get all categories from Data Base 
	 *
	 */
	public List<CategoryModel> loadCategoriesList();
		
	
}
