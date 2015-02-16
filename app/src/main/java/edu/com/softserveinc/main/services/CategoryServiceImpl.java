package edu.com.softserveinc.main.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import edu.com.softserveinc.main.dao.CategoryDao;
import edu.com.softserveinc.main.models.CategoryModel;

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

	@SuppressWarnings("rawtypes")
	@Override
	public List loadCategoriesList() {
		return categoryDao.findAll();
	}

	public CategoryModel getCategoryByName(String name){
		return categoryDao.findByName(name);
	}
}
