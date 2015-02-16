package edu.com.softserveinc.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.dao.CategoryDao;
import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Override
	public void addCategory(CategoryModel category) {
		categoryDao.saveAndFlush(category);
	}

	@Override
	public void deleteCategory(CategoryModel category) {
		categoryDao.delete(category);
	}

	@Override
	public void editCategory(CategoryModel category) {
		categoryDao.saveAndFlush(category);
	}

	@Override
	public CategoryModel getCategoryByID(int id) {
		return categoryDao.findOne(id);
	}

	@Override
	public List<CategoryModel> loadCategoriesList() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel getCategoryByName(String name){
		return categoryDao.findByName(name);
	}
}
